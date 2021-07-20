package language_elements.type_system;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.Symbol;
import interpreter.ast_visitor.CommonTypedVisitor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.FunctionDeclaration;
import language_elements.expression.*;
import language_elements.statement.ForStatement;
import language_elements.statement.VarDecl;

public class TypeChecker extends CommonTypedVisitor<Type, FunctionDeclaration> {
    private RuntimeEnvironment runtime;

    public TypeChecker(RuntimeEnvironment runtime) {
        this.runtime = runtime;
    }

    public Type evaluate(Expression e) {
        return e.accept(this, null);
    }

    @Override
    public Type visit(ArithmeticBinExpression e, FunctionDeclaration obj) {
        Type left = e.getLeft().accept(this, null);
        Type right = e.getRight().accept(this, null);
        Type res = null;
        switch (e.getOperation()) {
            case DIV:
                res = left.division(right);
                break;
            case MINUS:
                res = left.minus(right);
                break;
            case MULT:
                res = left.multiplication(right);
                break;
            case PLUS:
                res = left.plus(right);
                break;
        }

        if (res == null) {
            ErrorHandler.raise(new Fatal(MessageTemplater.UnknownType));
        }
        e.setType(res);
        return res;
    }

    @Override
    public Type visit(IntegerLiteral e, FunctionDeclaration argument) {
        return e.getType();
    }

    @Override
    public Type visit(FloatLiteral e, FunctionDeclaration argument) {
        return e.getType();
    }

    @Override
    public Type visit(BoolLiteral b, FunctionDeclaration argument) {
        return b.getType();
    }

    @Override
    public Type visit(StringLiteral s, FunctionDeclaration argument) {
        return s.getType();
    }

    @Override
    public Type visit(VarLiteral v, FunctionDeclaration currentFunc) {
        Symbol variable = runtime.accessLocalStorage().get(v.getId().getVal());
        v.setType(variable.getType());
        return v.getType();
    }

    @Override
    public Type visit(AssignExpression b, FunctionDeclaration argument) {

        return b.getExpression().accept(this, null);
    }

    @Override
    public Type visit(PostIncrementOperation p, FunctionDeclaration argument) {
        return p.accept(this, null);
    }

    @Override
    public Type visit(LogicalExpression l, FunctionDeclaration argument) {
        Type left = l.getLeft().accept(this, argument);
        Type right = l.getRight().accept(this, argument);
        Type res = left.logicalOperation(right);

        if (res == null) {
            ErrorHandler.raise(new Fatal("Undefined logical operation"));

        }

        l.setType(res);
        return res;
    }

    @Override
    public Type visit(EqualityExpression e, FunctionDeclaration currentFunc) {
        Type left = e.getLeft().accept(this, currentFunc);
        Type right = e.getRight().accept(this, currentFunc);
        Type res = left.equalityOperation(right);

        if (res == null) {
            ErrorHandler.raise(new Fatal("Undefined equalitiy operation"));
        }

        e.setType(res);
        return res;
    }

    @Override
    public Type visit(ForStatement f, FunctionDeclaration obj) {
        boolean forDecl = f.getInit() instanceof VarDecl;

        // if (forDecl) st.enterScope();

        // propagate visitor to the other 2 expressions and to the body
        if (f.getInit() != null)
            f.getInit().accept(this, obj);

        // check condition type
        Type condition = f.getCond() == null ? null : f.getCond().accept(this, obj);
        if (condition != null && condition != Type.BOOL) {
            ErrorHandler.raise(new Fatal("Condition is not of type bool"));
            // typeError(f.getCond().getPosition(), "cannot convert %s to boolean",
            // condition.toString().toLowerCase());
        }

        if (f.getIncr() != null)
            f.getIncr().accept(this, obj);

        f.getBody().accept(this, obj);

        // if (forDecl)st.exitScope();

        return null;
    }

    @Override
    public Type visit(RelationalExpression r, FunctionDeclaration argument) {
        Type left = r.getLeft().accept(this, null);
        Type right = r.getRight().accept(this, null);
        Type res = left.relationalOperation(right);
        if (res == null) {
            ErrorHandler.raise(new Fatal("Undefined relational operation"));
        }
        r.setType(res);
        return res;
    }
}
