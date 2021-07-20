import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.Interpreter;
import interpreter.Interpreter.Mode;
import lex.Lexer;
import parser.Parser;
import prelexer.KeywordAnalyzer;
import prelexer.SymbolStream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Program {

	private static final String SPEC_KEYWORDS_PATH = "res/keywords.json";

	static void usage() {
		ErrorHandler.raise(new Fatal(MessageTemplater.Usage));
	}

	static SymbolStream scanFile(String filename) {
		String line;
		StringBuilder inputSymbols = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while (null != (line = reader.readLine())) {
				inputSymbols.append(line).append('\n');
			}
		} catch (FileNotFoundException e) {
			ErrorHandler.raise(new Fatal(MessageTemplater.FileNotFound, filename));
		} catch (IOException e) {
			e.printStackTrace();
			ErrorHandler.raise(new Fatal(null));
		}
		return new SymbolStream(inputSymbols);
	}

	public static void main(String[] args) throws SecurityException,
			IllegalArgumentException {
		if (args.length != 1)
			usage();
		KeywordAnalyzer k = new KeywordAnalyzer(SPEC_KEYWORDS_PATH);
		Lexer lexer = new Lexer(scanFile(args[0]), k);
		lexer.scan();
		Parser p = new Parser(lexer.getTokens());
		Interpreter interpreter = new Interpreter();
		interpreter.run(p.parse(), Mode.COMPILE);
	}

}
