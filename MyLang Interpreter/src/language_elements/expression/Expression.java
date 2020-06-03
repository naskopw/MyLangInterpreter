package language_elements.expression;

import language_elements.statement.Statement;
import language_elements.type_system.Type;
import prelexer.Position;

public abstract class Expression extends Statement {

	// Private will still work because of the visitor
	private Type expressionType;

	public Expression(Position start) {
		super(start);
	}

	public void setType(Type type) {
		this.expressionType = type;
	}

	public Type getType() {
		return expressionType;
	}
}