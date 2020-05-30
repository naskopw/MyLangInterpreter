package lex;

import java.util.LinkedList;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import prelexer.SymbolStream;
import utility.CharUtility;

public class Lexer {
	private LinkedList<Token> tokens;

	public TokenStream getTokens() {
		return new TokenStream(tokens);
	}

	private SymbolStream stream;

	public Lexer(SymbolStream stream) {
		tokens = new LinkedList<Token>();
		this.stream = stream;
	}

	private void inspectsChar(char c) {
		if (CharUtility.isWhiteSpace(c))
			return;
		switch (c) {
		case '+':
			createToken(Token.Type.T_PLUS);
			break;
		case '-':
			createToken(Token.Type.T_MINUS);
			break;
		case '*':
			createToken(Token.Type.T_STAR);
			break;
		case '/':
			createToken(Token.Type.T_SLASH);
			break;
		case '=':
			createToken(Token.Type.T_EQUALS);
			break;
		case ';':
			createToken(Token.Type.T_SEMI);
			break;
		case '(':
			createToken(Token.Type.T_L_PAREN);
			break;
		case ')':
			createToken(Token.Type.T_R_PAREN);
			break;
		case '{':
			createToken(Token.Type.T_L_CURRLY_PAREN);
			break;
		case '}':
			createToken(Token.Type.T_R_CURRLY_PAREN);
			break;
		default:
			if (Character.isDigit(c)) {
				createToken(Token.Type.T_INT_LITERAL, Integer.toString(createInteger()));
				break;
			} else if (CharUtility.isValidIdentiferOrKeyword(c)) {
				String name = createIdentifier();
				if (Keyword.is(name)) {
					createToken(Keyword.get(name));
					break;
				}
				createToken(Token.Type.T_IDENTIFIER, name);
				break;
			}
			ErrorHandler.raise(new Fatal(MessageTemplater.UnknownSymbol, stream.current()));
		}
	}

	public void scan() {
		while (stream.hasNext()) {
			inspectsChar(stream.next());
		}
		createToken(Token.Type.T_EOF);
	}

	private int createInteger() {
		StringBuilder intBuffer = new StringBuilder();
		intBuffer.append(stream.current());
		while (stream.hasNext() && Character.isDigit(stream.next())) {
			intBuffer.append(stream.current());
		}
		if (!Character.isDigit(stream.current()))
			stream.previous();
		return Integer.parseInt(intBuffer.toString());
	}

	private String createIdentifier() {
		StringBuilder identifierBuffer = new StringBuilder();
		identifierBuffer.append(stream.current());
		while (stream.hasNext() && Character.isLetterOrDigit(stream.next())) {
			identifierBuffer.append(stream.current());
		}
		if (!Character.isLetterOrDigit(stream.current()))
			stream.previous();
		return identifierBuffer.toString();
	}

	public void printTokens() {
		for (Token t : tokens)
			System.out.println(t);
	}

	private void createToken(Token.Type type) {
		tokens.add(new Token(type, null));
	}

	private void createToken(Token.Type type, String value) {
		tokens.add(new Token(type, value));
	}

	private void createToken(Token t) {
		tokens.add(t);
	}
}
