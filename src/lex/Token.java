package lex;

import error_handling.ErrorHandler;
import error_handling.Warning;
import prelexer.Position;

public class Token {
	public enum Type {
		T_PLUS, T_MINUS, T_STAR, T_SLASH, T_EQUALS, T_IDENTIFIER, T_PRINT, T_PRINT_LN, T_SEMI, T_EOF, T_L_PAREN,
		T_R_PAREN, T_L_CURRLY_PAREN, T_R_CURRLY_PAREN, T_INT_LITERAL, T_FLOAT_LITERAL, T_BOOL_LITERAL, T_CHAR_LITERAL,
		T_STRING_LITERAL, T_COMMA, T_NOT, T_INCREMENT, T_DECREMENT, T_LESS_THAN, T_GREATER_THAN, T_GREATER_EQ,
		T_LESS_EQ, T_NOT_EQ, T_DOUBLE_EQUALS, T_OR, T_AND, T_PLUS_EQ, T_MINUS_EQ, T_INT_TYPE, T_FLOAT_TYPE,
		T_STRING_TYPE, T_BOOL_TYPE, T_RETURN, T_FOR, T_WHILE, T_IF, T_ELSE, T_CONTINUE, T_BREAK, T_L_SQ_PAREN,
		T_R_SQ_PAREN, T_VOID_TYPE, T_TRUE, T_FALSE
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
//		ErrorHandler.raise(new Warning("Token get position is not implemented."));
		return null;
	}

	public boolean isPrimitiveType() {
		return (type == Type.T_INT_TYPE || type == Type.T_FLOAT_TYPE || type == Type.T_BOOL_TYPE
				|| type == Type.T_STRING_TYPE);
	}
}
