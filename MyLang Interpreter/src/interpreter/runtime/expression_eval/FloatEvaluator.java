package interpreter.runtime.expression_eval;

import interpreter.Symbol;
import interpreter.ast_visitor.CommonTypedVisitor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.expression.ArithmeticBinExpression;
import language_elements.expression.FloatLiteral;
import language_elements.expression.IntegerLiteral;
import language_elements.expression.VarLiteral;

public class FloatEvaluator extends CommonTypedVisitor<Float, Object> {

	private RuntimeEnvironment runtime;

	public FloatEvaluator(RuntimeEnvironment runtime) {
		this.runtime = runtime;
	}


	@Override
	public Float visit(ArithmeticBinExpression e, Object arg) {
		float l = e.getLeft().accept(this, arg);
		float r = e.getRight().accept(this, arg);

		switch (e.getOperation()) {
		case PLUS:
			return l + r;
		case MINUS:
			return l - r;
		case MULT:
			return l * r;
		case DIV:
			return l / r;
		default:
			return null;
		}
	}

	@Override
	public Float visit(FloatLiteral i, Object o) {
		return i.getValue();
	}

	@Override
	public Float visit(IntegerLiteral i, Object o) {
		return (float) i.getValue();
	}

	@Override
	public Float visit(VarLiteral v, Object argument) {
		Symbol variable = runtime.accessLocalStorage().get(v.getId().getVal());
		return (Float) variable.getValue();

	}

}
