package interpreter.runtime.expression_eval;


import error_handling.ErrorHandler;
import error_handling.Fatal;
import interpreter.Symbol;
import interpreter.ast_visitor.CommonTypedVisitor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.expression.*;
import language_elements.type_system.Type;

public class StringEvaluator extends CommonTypedVisitor<String, Object> {
    RuntimeEnvironment runtime;

    public StringEvaluator(RuntimeEnvironment runtime) {
        this.runtime = runtime;
    }

    @Override
    public String visit(ArithmeticBinExpression e, Object obj) {
        if (e.getOperation() != ArithmeticBinExpression.ArithmeticBinOperation.PLUS)
            ErrorHandler.raise(new Fatal(null));

        Type.Primitive leftType = e.getLeft().getType().getType();
        Type.Primitive rightType = e.getRight().getType().getType();

        if (leftType == Type.Primitive.FLOAT) {
            String l = e.getLeft().accept(new FloatEvaluator(runtime), obj).toString();
            String r = e.getRight().accept(this, obj);
            return l + r;
        } else if (rightType == Type.Primitive.FLOAT) {
            String r = e.getRight().accept(new FloatEvaluator(runtime), obj).toString();
            String l = e.getLeft().accept(this, obj);
            return l + r;
        } else if (leftType == Type.Primitive.INT) {
            String l = e.getLeft().accept(new IntegerEvaluator(runtime), obj).toString();
            String r = e.getRight().accept(this, obj);
            return l + r;
        } else if (rightType == Type.Primitive.INT) {
            String r = e.getRight().accept(new IntegerEvaluator(runtime), obj).toString();
            String l = e.getLeft().accept(this, obj);
            return l + r;
        } else if (leftType == Type.Primitive.BOOL) {
            String l = e.getLeft().accept(new BoolEvaluator(runtime), obj).toString();
            String r = e.getRight().accept(this, obj);
            return l + r;
        } else if (rightType == Type.Primitive.BOOL) {
            String l = e.getLeft().accept(this, obj);
            String r = e.getRight().accept(new BoolEvaluator(runtime), obj).toString();
            return l + r;
        }

        return e.getLeft().accept(this, obj) + e.getRight().accept(this, obj);
    }

    @Override
    public String visit(VarLiteral v, Object obj) {
        Symbol variable = runtime.accessLocalStorage().get(v.getId().getVal());
        return (String) variable.getValue();


    }

    @Override
    public String visit(ArrayAccess a, Object obj) {
        ErrorHandler.raise(new Fatal("Sting type array access not implemented"));
        return null;
    }

    @Override
    public String visit(StringLiteral s, Object obj) {
        return s.getValue();
    }


    @Override
    public String visit(FuncCallExpression f, Object obj) {
        ErrorHandler.raise(new Fatal("Sting type func call not implemented"));
        return null;
    }

}