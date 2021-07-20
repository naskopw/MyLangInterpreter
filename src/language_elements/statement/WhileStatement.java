package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.expression.Expression;
import prelexer.Position;

public class WhileStatement extends Statement {
	private Expression condition;
	private Statement body;

	public WhileStatement(Expression condition, Statement body, Position start) {
		super(start);
		this.condition = condition;
		this.body = body;
	}

	public Expression getCondition() {
		return condition;
	}

	public Statement getBody() {
		return body;
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
