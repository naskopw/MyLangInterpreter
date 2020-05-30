package lex;

public class Keyword {
	public static final String[] keywords = { "print", "int" };

	private static boolean contains(String[] array, String value) {
		for (String each : array)
			if (each.equals(value))
				return true;
		return false;
	}

	private static int indexOf(String[] array, String value) {
		int index = -1;
		for (String each : array) {
			if (each.equals(value))
				break;
			index++;
		}
		return index + 1;
	}

	public static boolean is(String keyword) {
		return contains(keywords, keyword);
	}

	private static boolean match(String keyword, String target) {
		return keywords[indexOf(keywords, keyword)].equals(target);
	}

	public static Token get(String keyword) {

		if (is(keyword)) {
			if (match(keyword, "int"))
				return new Token(Token.Type.T_TYPE, "int");
		}
		return null;
	}
}
