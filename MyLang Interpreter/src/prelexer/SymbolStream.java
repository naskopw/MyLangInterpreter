package prelexer;

import utility.StreamIterator;

public class SymbolStream implements StreamIterator<Character> {
	private final String data;
	private int i = -1;

	public SymbolStream(StringBuilder source) {
		this.data = source.toString();
	}

	public SymbolStream(String source) {
		this.data = source;
	}

	@Override
	public boolean hasNext() {
		return i < data.length() - 1;
	}

	@Override
	public Character next() {
		return i < data.length() - 1 ? data.charAt(++i) : null;
	}

	@Override
	public Character peek() {
		return i < data.length() - 1 ? data.charAt(i + 1) : null;
	}

	@Override
	public Character current() {
		return i >= 0 ? data.charAt(i) : null;

	}

	@Override
	public Character previous() {
		return i >= 0 ? data.charAt(--i) : null;

	}

	public int getPos() {
		return (i >= 0 && i < data.length()) ? i : null;
	}

}