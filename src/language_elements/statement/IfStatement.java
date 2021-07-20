package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.expression.Expression;
import prelexer.Position;

public class IfStatement extends Statement {
	private Expression condition;
	private Statement thenStmt;
	private Statement elseStatement;

	public IfStatement(Expression condition, Statement thenStmt, Position pos) {
		this(condition, thenStmt, null, pos);
	}

	public IfStatement(Expression condition, Statement thenStmt, Statement elseStmt, Position pos) {
		super(pos);
		this.condition = condition;
		this.thenStmt = thenStmt;
		this.elseStatement = elseStmt;
	}

	public Expression getCondition() {
		return condition;
	}

	public Statement getThenStatement() {
		return thenStmt;
	}

	public Statement getElseStatement() {
		return elseStatement;
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