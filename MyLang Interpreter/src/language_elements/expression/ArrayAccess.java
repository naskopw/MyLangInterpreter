package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;

public class ArrayAccess extends Lvalue {
    private Expression lvalue;
    private Expression index;

    public ArrayAccess(Expression lvalue, Expression index) {
        super(lvalue.getPosition());
        this.lvalue = lvalue;
        this.index = index;
    }


    @Override
    public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

    public Expression getLvalue() {
        return lvalue;
    }

    public Expression getIndex() {
        return index;
    }

    @Override
    public String toString() {
        boolean par = lvalue instanceof AssignExpression;
        return (par ? "(" : "") + lvalue + (par ? ")" : "") + "[" + index + "]";
    }

}
