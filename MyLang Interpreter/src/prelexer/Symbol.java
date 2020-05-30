package prelexer;

public class Symbol {
	public Symbol(char data, Position pos) {
		this.data = data;
		this.pos = pos;
	}

	private Character data;
	private Position pos;

	public Position getPos() {
		return pos;
	}

	@Override
	public String toString() {
		return data.toString();
	}
}
