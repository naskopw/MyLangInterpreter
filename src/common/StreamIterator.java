package common;

import java.util.Iterator;

public interface StreamIterator<T> extends Iterator<T> {
	public T peek();

	public T current();

	public T previous();
}
