package language_elements;

import prelexer.Position;

public class Identifier {
	private transient Position position;
	private String id;

	public Identifier(Position pos, String id) {
		this.position = pos;
		this.id = id;
	}

	public Identifier(String id) {
		this(new Position(0), id);
	}

	public String getVal() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return id;
	}

}