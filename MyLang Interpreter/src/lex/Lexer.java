package lex;

import java.util.LinkedList;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import prelexer.KeywordAnalyzer;
import prelexer.SymbolStream;
import utility.CharUtility;

public class Lexer {
	private LinkedList<Token> tokens;

	public TokenStream getTokens() {
		return new TokenStream(tokens);

	}

	private SymbolStream stream;
	private KeywordAnalyzer keywordAnalyzer;

	public Lexer(SymbolStream stream, KeywordAnalyzer keywordAnalyzer) {
		tokens = new LinkedList<Token>();
		this.stream = stream;
		this.keywordAnalyzer = keywordAnalyzer;
	}

	private void inspectsChar(char c) {
		if (CharUtility.isWhiteSpace(c))
			return;
		switch (c) {
		case '+':
			if (isLongerToken("++"))
				createToken(Token.Type.T_INCREMENT);
			else
				createToken(Token.Type.T_PLUS);
			break;
		case '-':
			if (isLongerToken("--"))
				createToken(Token.Type.T_DECREMENT);
			else
				createToken(Token.Type.T_MINUS);
			break;
		case '*':
			createToken(Token.Type.T_STAR);
			break;
		case '/':
			createToken(Token.Type.T_SLASH);
			break;
		case '=':
			if (isLongerToken("=="))
				createToken(Token.Type.T_DOUBLE_EQUALS);
			else
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
		case ',':
			createToken(Token.Type.T_COMMA);
			break;
		case '{':
			createToken(Token.Type.T_L_CURRLY_PAREN);
			break;
		case '}':
			createToken(Token.Type.T_R_CURRLY_PAREN);
			break;
		case '>':
			if (isLongerToken(">="))
				createToken(Token.Type.T_GREATER_EQ);
			else
				createToken(Token.Type.T_GREATER_THAN);
			break;
		case '<':
			if (isLongerToken("<="))
				createToken(Token.Type.T_LESS_EQ);
			else
				createToken(Token.Type.T_LESS_THAN);
			break;
		case '!':
			if (isLongerToken("!="))
				createToken(Token.Type.T_NOT_EQ);
			else
				createToken(Token.Type.T_EQUALS);
			break;
		case '&':
			if (isLongerToken("&&"))
				createToken(Token.Type.T_AND);
			break;
		case '|':
			if (isLongerToken("||"))
				createToken(Token.Type.T_OR);
			break;
		case '[':
			createToken(Token.Type.T_L_SQ_PAREN);
			break;
		case ']':
			createToken(Token.Type.T_R_SQ_PAREN);
			break;
		default:
			if (Character.isDigit(c)) {
				String numLiteral = createNumberLiteral();
				if (CharUtility.in('.', numLiteral))
					createToken(Token.Type.T_FLOAT_LITERAL, numLiteral);
				else
					createToken(Token.Type.T_INT_LITERAL, numLiteral);
				break;
			} else if (CharUtility.isValidIdentiferOrKeyword(c)) {
				String name = createIdentifier();
				Token keyword = keywordAnalyzer.getKeyword(name);

				if (keyword != null) {
					createToken(keyword);
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

	private String createNumberLiteral() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(stream.current());
		while (stream.hasNext() && (Character.isDigit(stream.next()) || stream.current().equals('.'))) {
			buffer.append(stream.current());
		}

		if (!Character.isDigit(stream.current()))
			stream.previous();

		return buffer.toString();
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

	@Deprecated
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

	private boolean isLongerToken(String name) {
		boolean isThatToken = stream.peek().equals(name.charAt(1));
		if (isThatToken)
			stream.next();
		return isThatToken;
	}
}
