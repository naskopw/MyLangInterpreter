package language_elements.expression;

import prelexer.Position;

public abstract class Lvalue extends Expression {

	public Lvalue(Position start) {
		super(start);
	}

}