package prelexer;

public class Position {
	private Integer pos;

	public Position(Integer pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		return String.format("--line:%d", pos);
	}
}
