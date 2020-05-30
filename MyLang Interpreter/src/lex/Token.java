package lex;

import prelexer.Position;

public class Token {
	public enum Type {
		T_PLUS, T_MINUS, T_STAR, T_SLASH, T_TYPE, T_EQUALS, T_IDENTIFIER, T_PRINT, T_SEMI, T_EOF, T_L_PAREN, T_R_PAREN,
		T_L_CURRLY_PAREN, T_R_CURRLY_PAREN, T_INT_LITERAL, T_FLOAT_LITERAL, T_BOOL_LITERAL, T_CHAR_LITERAL,
		T_STRING_LITERAL, T_COMMA, T_NOT
	}

	private Type type;
	private String value;

	public String getValue() {
		return value;
	}

	public Type getType() {
		return type;
	}

	public boolean isOfType(Token.Type t) {
		return type.equals(t);
	}

	@Override
	public String toString() {
		if (value == null)
			return "Token [" + type + "]";
		return "Token [" + type + ", " + value + "]";
	}

	public Token(Type type, String value) {
		this.type = type;
		this.value = value;
	}

	public boolean is(Type tType, String string) {
		if (type == tType && value.equals(string))
			return true;
		return false;
	}

	public Position getPosition() {
		System.out.println("Token.getPosition() - Not implemented");
		return null;
	}

}
