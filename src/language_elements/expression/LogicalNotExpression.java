package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import prelexer.Position;

public class LogicalNotExpression extends UnaryExpression {

	public LogicalNotExpression(Expression e, Position pos) {
		super(e, pos);
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