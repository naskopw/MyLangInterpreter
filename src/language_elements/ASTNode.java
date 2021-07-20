package language_elements;

import interpreter.ast_visitor.Visitable;
import prelexer.Position;

public abstract class ASTNode implements Visitable {
	private Position start;

	public ASTNode(Position start) {
		this.start = start;
	}

	public Position getPosition() {
		return start;
	}

}