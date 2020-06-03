package language_elements.expression;

public abstract class PostExpression extends Expression {
	private Expression expression;

	public PostExpression(Expression e) {
		super(e.getPosition());
		this.expression = e;
	}

	public Expression getExpression() {
		return expression;
	}
}