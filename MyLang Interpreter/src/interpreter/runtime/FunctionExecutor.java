package interpreter.runtime;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.ast_visitor.CommonVoidVisitor;
import interpreter.control_flow.Break;
import interpreter.control_flow.Continue;
import interpreter.control_flow.Return;
import interpreter.runtime.expression_eval.BoolEvaluator;
import interpreter.runtime.expression_eval.FloatEvaluator;
import interpreter.runtime.expression_eval.IntegerEvaluator;
import interpreter.runtime.expression_eval.StringEvaluator;
import language_elements.FunctionDeclaration;
import language_elements.expression.AssignExpression;
import language_elements.expression.Expression;
import language_elements.expression.FuncCallExpression;
import language_elements.expression.PostIncrementOperation;
import language_elements.statement.*;
import language_elements.type_system.Type;
import language_elements.type_system.TypeChecker;

import java.util.LinkedList;
import java.util.Map;

public class FunctionExecutor extends CommonVoidVisitor<Object> {
    private RuntimeEnvironment runtime;
    private TypeChecker typeChecker;
    private boolean isDeclaration;
    Map<String, FunctionDeclaration> functions;

    public FunctionExecutor(Map<String, FunctionDeclaration> functions, RuntimeEnvironment runtime) {
        this.functions = functions;
        this.runtime = runtime;
        this.typeChecker = new TypeChecker(runtime);
        FunctionDeclaration func = functions.get("main");
        execute(func);

    }

    public void execute(FunctionDeclaration function) {
        for (Statement st : function.getBody().getStatements()) {
            st.accept(this, null);
        }
    }

    @Override
    public void visit(PrintStatement v, Object obj) {
        if (v.isNewLine())
            System.out.println(interpretExpression(v.getExpression()));
        else
            System.out.print(interpretExpression(v.getExpression()));
    }

    @Override
    public void visit(VarDecl v, Object obj) {
        isDeclaration = true;
        v.getInitializer().accept(this, v.getType());
        isDeclaration = false;
    }

    @Override
    public void visit(AssignExpression e, Object identifierType) {
        String varName = e.getLvalue().toString();
        Object assignValue = interpretExpression(e.getExpression());
        Type type = typeChecker.evaluate(e.getExpression());
        if (isDeclaration) {
            if (!type.equals(identifierType))
                ErrorHandler.raise(new Fatal(MessageTemplater.TypeMismatch));
            runtime.accessLocalStorage().add(varName, assignValue, type);
        } else {
            if (!runtime.isSymbolDefined(varName))
                ErrorHandler.raise(new Fatal(MessageTemplater.UndeclaredVar, varName));
            runtime.updateSymbol(varName, assignValue, type);
        }
    }

    private Object interpretExpression(Expression e) {

        Type type = typeChecker.evaluate(e);
        switch (type.getType()) {
            case STRING:
                return e.accept(new StringEvaluator(runtime), null);
            case INT:
                return e.accept(new IntegerEvaluator(runtime), null);
            case FLOAT:
                return e.accept(new FloatEvaluator(runtime), null);
            case BOOL:
                return e.accept(new BoolEvaluator(runtime), null);
            default:
                ErrorHandler.raise(new Fatal(MessageTemplater.UnknownType));
                return null;
        }
    }

    @Override
    public void visit(IfStatement v, Object obj) {
        typeChecker.evaluate(v.getCondition());
        if (v.getCondition().accept(new BoolEvaluator(runtime), obj)) {
            v.getThenStatement().accept(this, obj);
        } else {
            if (v.getElseStatement() != null)
                v.getElseStatement().accept(this, obj);
        }
    }

    @Override
    public void visit(WhileStatement w, Object obj) {
        try {

            typeChecker.evaluate(w.getCondition());
            while (w.getCondition().accept(new BoolEvaluator(runtime), null)) {
                try {
                    w.getBody().accept(this, null);
                } catch (Continue c) {

                }
            }
        } catch (Break b) {

        }
    }

    @Override
    public void visit(BreakStatement b, Object obj) {
        throw Break.instance();
    }

    @Override
    public void visit(ContinueStatement c, Object obj) {
        throw Continue.instance();
    }

    @Override
    public void visit(ReturnStatement r, Object obj) {
        Object ret = null;
        if (r.getExpression() != null) {
            ret = interpretExpression(r.getExpression());
        }
        throw Return.instance(ret);
    }

    @Override
    public void visit(ForStatement f, Object obj) {

        if (f.getInit() != null)
            f.getInit().accept(this, obj);
        typeChecker.evaluate(f.getCond());
        Expression cond = f.getCond();
        try {
            while (cond == null || cond.accept(new BoolEvaluator(runtime), null)) {
                try {
                    f.getBody().accept(this, null);
                } catch (Continue c) {
                }
                if (f.getIncr() != null)
                    f.getIncr().accept(this, null);
            }
        } catch (Break b) {
        }
    }

    @Override
    public void visit(PostIncrementOperation p, Object obj) {
        Type type = typeChecker.evaluate(p.getExpression());
        switch (type.getType()) {
            case INT:
                Integer i = (Integer) interpretExpression(p.getExpression()) + 1;
                runtime.updateSymbol(p.getExpression().toString(), i, p.getExpression().getType());
            case FLOAT:
                Float f = (Float) interpretExpression(p.getExpression()) + 1.0f;
                runtime.updateSymbol(p.getExpression().toString(), f, p.getExpression().getType());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type.getType());
        }

    }

    @Override
    public void visit(FuncCallExpression p, Object obj) {
        try {
            callFunction(p);
        } catch (Return r) {
            System.out.println("Return value: " + r.val);
            //Expression e = interpretExpression(r.val))
        }
    }

    public void callFunction(FuncCallExpression funcCall) {
        System.out.println(funcCall.getArgs());
        Expression[] args = funcCall.getArgs();

        //compute function argument expressions
        Object[] evaluatedArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            evaluatedArgs[i] = interpretExpression(args[i]);
        }
        for (Object c : evaluatedArgs) System.out.println(c);
        call(funcCall.getFuncName().getVal(), evaluatedArgs);
    }

    private void call(String funcName, Object[] args) {
        FunctionDeclaration func = functions.get(funcName);
            execute(func);
    }

    @Override
    public void visit(ArrayDeclaration a, Object obj) {
        LinkedList<Integer> computedDim = new LinkedList<>();
        for(Expression e : a.getDimensions()) {
            computedDim.add(e.accept(new IntegerEvaluator(runtime),null));
        }
        for (Integer i : computedDim)
        System.out.println(computedDim);
        }
}
