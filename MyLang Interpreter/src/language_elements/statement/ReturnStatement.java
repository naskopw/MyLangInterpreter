package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.expression.Expression;
import prelexer.Position;

public class ReturnStatement extends Statement {
	private Expression expression;

	public ReturnStatement(Position start, Expression exp) {
		super(start);
		this.expression = exp;
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression e) {
		this.expression = e;
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