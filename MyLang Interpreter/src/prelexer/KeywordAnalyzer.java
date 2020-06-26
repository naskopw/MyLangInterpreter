package prelexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.*;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import lex.Token;

public class KeywordAnalyzer {
	private static final String JSONArrayName = "keywords";
	private Map<String, Token> keywords;

	public KeywordAnalyzer(String filename) {

		keywords = new HashMap<String, Token>();
		try {
			parseJSON(readFile(filename));
		} catch (Exception e) {
			ErrorHandler.raise(new Fatal(MessageTemplater.IncorrectJSON, filename));
		}
	}

	private String readFile(String filename) {
		String line;
		StringBuilder inputSymbols = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while ((line = reader.readLine()) != null) {
				inputSymbols.append(line);
			}
		} catch (FileNotFoundException e1) {
			ErrorHandler.raise(new Fatal(MessageTemplater.FileNotFound, filename));
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.raise(new Fatal(null));
		}
		return inputSymbols.toString();
	}

	private void parseJSON(String jsonString) {
		JSONObject obj = new JSONObject(jsonString);

		// Ugly, but don't have time to learn the library API
		JSONArray arr = obj.getJSONArray(JSONArrayName);
		JSONObject keywordJSON = null;
		Iterator<String> iterator = null;
		String key = null;
		String value = null;
		String tokenType = null;
		String valueTypeWithQuotes = null;
		String valueType = null;
		for (int i = 0; i < arr.length(); i++) {
			keywordJSON = arr.getJSONObject(i);
			iterator = keywordJSON.keySet().iterator();
			key = iterator.next();
			value = keywordJSON.get(key).toString();
			tokenType = value;
			if (value.contains(",")) {
				tokenType = value.substring(2, value.indexOf(",") - 1);
				valueTypeWithQuotes = value.substring(value.indexOf(",") + 1, value.length() - 1);
				valueType = valueTypeWithQuotes.substring(1, valueTypeWithQuotes.length() - 1);
			}
			keywords.put(key, new Token(Token.Type.valueOf(tokenType), valueType));
		}
	}

	public Token getKeyword(String key) {
		return keywords.get(key);
	}
}
