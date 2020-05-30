package utility;

public class CharUtility {
	public static boolean in(char targetSymbol, String str) {
		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == targetSymbol)
				return true;
		return false;
	}

	public static boolean isWhiteSpace(char targetSymbol) {
		if (in(targetSymbol, WHITESPACES))
			return true;
		return false;
	}

	public static final String WHITESPACES = " \n\t\r\f";

	public static boolean isValidIdentiferOrKeyword(char c) {
		return (c == '_' || Character.isLetter(c));
	}
}
