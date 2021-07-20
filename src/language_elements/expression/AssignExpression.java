package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;

public class AssignExpression extends Expression {
	private Expression lvalue;
	private Expression e;

	public AssignExpression(Expression lvalue, Expression e) {
		super(lvalue.getPosition());
		this.lvalue = lvalue;
		this.e = e;
	}

	public Expression getLvalue() {
		return lvalue;
	}

	public Expression getExpression() {
		return e;
	}

	public void setExpression(Expression e) {
		this.e = e;
	}

	@Override
	public String toString() {
		return lvalue + " = " + e.toString();
	}

	@Override
	public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
		return v.visit(this, null);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}

}