package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;

public class ArithmeticBinExpression extends BinaryExpression {
	public static enum ArithmeticBinOperation {
		PLUS, MINUS, MULT, DIV;
	}

	private ArithmeticBinOperation operation;

	public ArithmeticBinExpression(ArithmeticBinOperation op, Expression left, Expression right) {
		super(left, right);
		this.operation = op;
	}

	public ArithmeticBinOperation getOperation() {
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