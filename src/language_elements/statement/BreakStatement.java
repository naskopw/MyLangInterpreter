package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import prelexer.Position;

public class BreakStatement extends Statement {

	public BreakStatement(Position start) {
		super(start);
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