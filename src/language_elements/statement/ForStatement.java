package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.expression.Expression;
import prelexer.Position;

public class ForStatement extends Statement {
	private Statement init;
	private Expression cond;
	private Expression incr;

	private Statement body;

	public ForStatement(Position start, Statement init, Expression cond, Expression incr, Statement body) {
		super(start);
		this.init = init;
		this.cond = cond;
		this.incr = incr;
		this.body = body;
	}

	public Statement getInit() {
		return init;
	}

	public Expression getCond() {
		return cond;
	}

	public Expression getIncr() {
		return incr;
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