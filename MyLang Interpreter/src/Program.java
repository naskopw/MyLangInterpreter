import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.Interpreter;
import interpreter.Interpreter.Mode;
import lex.Lexer;
import parser.Parser;
import prelexer.KeywordAnalyzer;
import prelexer.SymbolStream;

public class Program {

	private static final String SPEC_KEYWORDS_PATH = "res/keywords.json";

	static void usage() {
		ErrorHandler.raise(new Fatal(MessageTemplater.Usage));
	}

	static SymbolStream scanFile(String filename) {
		String line;
		StringBuilder inputSymbols = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while ((line = reader.readLine()) != null) {
				inputSymbols.append(line + '\n');
			}
		} catch (FileNotFoundException e) {
			ErrorHandler.raise(new Fatal(MessageTemplater.FileNotFound, filename));
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.raise(new Fatal(null));
		}
		return new SymbolStream(inputSymbols);
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (args.length != 1)
			usage();
		KeywordAnalyzer k = new KeywordAnalyzer(SPEC_KEYWORDS_PATH);
		Lexer lexer = new Lexer(scanFile(args[0]), k);
		lexer.scan();
		lexer.printTokens();
		Parser p = new Parser(lexer.getTokens());
		Interpreter interp = new Interpreter();
		interp.run(p.parse(), Mode.COMPILE);
	}

}
