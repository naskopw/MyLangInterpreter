package lex;

import java.util.LinkedList;
import java.util.ListIterator;

import utility.StreamIterator;

public class TokenStream implements StreamIterator<Token> {
	private Token current = null;
	private ListIterator<Token> t;

	public TokenStream(LinkedList<Token> tokens) {
		t = tokens.listIterator();

	}

	@Override
	public boolean hasNext() {
		return t.hasNext();
	}

	@Override
	public Token next() {
		current = t.next();
		return current;
	}

	@Override
	public Token peek() {
		Token nextToken = next();
		t.previous();
		return nextToken;
	}

	@Override
	public Token current() {
		return current;
	}

	@Override
	public Token previous() {
		return t.previous();
	}

}
