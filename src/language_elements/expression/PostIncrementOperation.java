package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;

public class PostIncrementOperation extends PostExpression {
	private IncrementOperator op;

	public PostIncrementOperation(IncrementOperator op, Expression e) {
		super(e);
		this.op = op;
	}

	public IncrementOperator getOperator() {
		return op;
	}

	@Override
	public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}
}