package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;

public class RelationalExpression extends BinaryExpression {
	public static enum RelationalOperation {
		LESS_THAN, GREATER_THAN, LESS_THAN_EQ, GREATER_THAN_EQ
	}

	private RelationalOperation operation;

	public RelationalExpression(RelationalOperation op, Expression left, Expression right) {
		super(left, right);
		this.operation = op;
	}

	public RelationalOperation getOperation() {
		return operation;
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