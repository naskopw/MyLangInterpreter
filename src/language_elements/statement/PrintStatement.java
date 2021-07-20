package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.expression.Expression;
import prelexer.Position;

public class PrintStatement extends Statement {
	private Expression e;
	private boolean newLine;

	public PrintStatement(Position start, Expression e) {
		super(start);
		this.e = e;
	}

	public PrintStatement(Position start, Expression e, boolean newLine) {
		super(start);
		this.e = e;
		this.newLine = newLine;
	}

	public Expression getExpression() {
		return e;
	}

	public boolean isNewLine() {
		return newLine;
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