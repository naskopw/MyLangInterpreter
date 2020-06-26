package interpreter.runtime.expression_eval;

import java.awt.Window.Type;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.Symbol;
import interpreter.ast_visitor.CommonTypedVisitor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.expression.BoolLiteral;
import language_elements.expression.EqualityExpression;
import language_elements.expression.LogicalExpression;
import language_elements.expression.RelationalExpression;
import language_elements.expression.VarLiteral;
import language_elements.type_system.Type.Primitive;

public class BoolEvaluator extends CommonTypedVisitor<Boolean, Object> {

	private RuntimeEnvironment runtime;

	public BoolEvaluator(RuntimeEnvironment runtime) {
		this.runtime = runtime;
	}

	@Override
	public Boolean visit(VarLiteral v, Object argument) {
		Symbol variable = runtime.accessLocalStorage().get(v.getId().getVal());
		return (Boolean) variable.getValue();
	}

	@Override
	public Boolean visit(BoolLiteral b, Object argument) {
		return b.getValue();
	}

	@Override
	public Boolean visit(LogicalExpression l, Object argument) {
		switch (l.getOperation()) {
		case AND:
			return l.getLeft().accept(this, argument) && l.getRight().accept(this, argument);
		case OR:
			return l.getLeft().accept(this, argument) || l.getRight().accept(this, argument);
		default:
			ErrorHandler.raise(new Fatal(null));
			return null;
		}
	}

	@Override
	public Boolean visit(RelationalExpression r, Object obj) {
		float left = 0, right = 0;
		if (!r.getLeft().getType().getType().equals(r.getRight().getType().getType()))
			ErrorHandler.raise(new Fatal(MessageTemplater.TypeMismatch));

		switch (r.getLeft().getType().getType()) {
		case FLOAT:
			left = r.getLeft().accept(new FloatEvaluator(runtime), null);
			right = r.getRight().accept(new FloatEvaluator(runtime), null);
			break;
		case INT:
			left = r.getLeft().accept(new IntegerEvaluator(runtime), null);
			right = r.getRight().accept(new IntegerEvaluator(runtime), null);
			break;
		default:
			ErrorHandler.raise(new Fatal(null));

		}

		switch (r.getOperation()) {
		case LESS_THAN:
			return left < right;
		case LESS_THAN_EQ:
			return left <= right;
		case GREATER_THAN:
			return left > right;
		case GREATER_THAN_EQ:
			return left >= right;
		default:
			ErrorHandler.raise(new Fatal(null));
			return null;
		}
	}

	@Override
	public Boolean visit(EqualityExpression e, Object obj) {
		Object l = null, r = null;

		Primitive ltype = e.getLeft().getType().getType();

		switch (ltype) {
		case INT:
			l = e.getLeft().accept(new IntegerEvaluator(runtime), null);
			r = e.getRight().accept(new IntegerEvaluator(runtime), null);
			break;
		case FLOAT:
			l = e.getLeft().accept(new FloatEvaluator(runtime), null);
			r = e.getRight().accept(new FloatEvaluator(runtime), null);
			break;
		case BOOL:
			l = e.getLeft().accept(this, null);
			r = e.getRight().accept(this, null);
			break;
		default:
			ErrorHandler.raise(new Fatal(null));
			return null;
		}
		switch (e.getOperation()) {
		case EQ:
			return l.equals(r);
		case NEQ:
			return !l.equals(r);
		default:
			ErrorHandler.raise(new Fatal(null));
			return null;
		}
	}
}