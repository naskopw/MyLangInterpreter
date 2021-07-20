package language_elements.statement;

import java.util.Collection;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import prelexer.Position;

public class BlockStatement extends Statement {
	private Statement[] statements;

	public BlockStatement(Collection<Statement> statements, Position pos) {
		super(pos);
		this.statements = statements.toArray(new Statement[statements.size()]);
	}

	public Statement[] getStatements() {
		return statements;
	}

	@Override
	public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
		return v.visit(this, arg);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}
}