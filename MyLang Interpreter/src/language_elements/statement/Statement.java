package language_elements.statement;

import language_elements.ASTNode;
import prelexer.Position;

public abstract class Statement extends ASTNode {
	public Statement(Position start) {
		super(start);
	}
}