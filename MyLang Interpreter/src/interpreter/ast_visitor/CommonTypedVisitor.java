package interpreter.ast_visitor;

import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;
import language_elements.expression.*;
import language_elements.statement.*;

public class CommonTypedVisitor<T, A> implements TypedVisitor<T, A> {

    @Override
    public T visit(Visitable v, A arg) {
        return null;
    }

    @Override
    public T visit(TranslationUnit p, A arg) {
        for (String id : p.getFunctions().keySet()) {
            p.getFunctions().get(id).accept(this, arg);
        }
        return null;
    }

    @Override
    public T visit(IfStatement v, A arg) {
        v.getCondition().accept(this, arg);
        v.getThenStatement().accept(this, arg);
        if (v.getElseStatement() != null)
            v.getElseStatement().accept(this, arg);
        return null;
    }

    @Override
    public T visit(WhileStatement v, A arg) {
        v.getCondition().accept(this, arg);
        v.getBody().accept(this, null);
        return null;
    }

    @Override
    public T visit(ForStatement v, A arg) {
        if (v.getInit() != null)
            v.getInit().accept(this, arg);
        if (v.getCond() != null)
            v.getCond().accept(this, arg);
        if (v.getIncr() != null)
            v.getIncr().accept(this, arg);
        v.getBody().accept(this, arg);
        return null;
    }

    @Override
    public T visit(BlockStatement v, A arg) {
        for (Statement s : v.getStatements()) {
            s.accept(this, arg);

        }
        return null;
    }

    @Override
    public T visit(PrintStatement p, A arg) {
        p.getExpression().accept(this, arg);
        return null;
    }

    @Override
    public T visit(ReturnStatement r, A arg) {
        if (r.getExpression() != null)
            r.getExpression().accept(this, arg);
        return null;
    }

    @Override
    public T visit(BreakStatement b, A arg) {
        return null;
    }

    @Override
    public T visit(ContinueStatement c, A arg) {
        return null;
    }

    @Override
    public T visit(VarDecl v, A arg) {
        if (v.getInitializer() != null)
            v.getInitializer().accept(this, arg);
        return null;
    }

    @Override
    public T visit(ArithmeticBinExpression e, A arg) {
        e.getLeft().accept(this, arg);
        e.getRight().accept(this, arg);
        return null;
    }

    @Override
    public T visit(LogicalExpression l, A arg) {
        l.getLeft().accept(this, arg);
        l.getRight().accept(this, arg);
        return null;
    }

    @Override
    public T visit(RelationalExpression r, A arg) {
        r.getLeft().accept(this, arg);
        r.getRight().accept(this, arg);
        return null;
    }

    @Override
    public T visit(EqualityExpression e, A arg) {
        e.getLeft().accept(this, arg);
        e.getRight().accept(this, arg);
        return null;
    }

    @Override
    public T visit(LogicalNotExpression n, A arg) {
        n.getExpression().accept(this, arg);
        return null;
    }

    @Override
    public T visit(PostIncrementOperation p, A arg) {
        p.getExpression().accept(this, arg);
        return null;
    }

    @Override
    public T visit(AssignExpression e, A arg) {
        e.getLvalue().accept(this, arg);
        e.getExpression().accept(this, arg);
        return null;
    }

    @Override
    public T visit(FloatLiteral f, A arg) {
        return null;
    }

    @Override
    public T visit(IntegerLiteral i, A arg) {
        return null;
    }

    @Override
    public T visit(BoolLiteral b, A arg) {
        return null;
    }

    @Override
    public T visit(VarLiteral v, A arg) {
        return null;
    }

    @Override
    public T visit(FuncCallExpression f, A arg) {
        for (Expression e : f.getArgs()) {
            e.accept(this, arg);

        }
        return null;
    }

    @Override
    public T visit(FunctionDeclaration d, A arg) {
        return d.getBody().accept(this, arg);
    }

}