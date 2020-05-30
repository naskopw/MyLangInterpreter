import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import lex.Lexer;
//import parser.Parser;
import prelexer.SymbolStream;

public class Program {

	static void usage() {
		System.out.printf("Usage: java Program \'inputFile\'\n");
		System.exit(1);
	}

	static SymbolStream scanFile(String filename) {
		String line;
		StringBuilder inputSymbols = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			while ((line = reader.readLine()) != null) {
				inputSymbols.append(line);
			}
		} catch (FileNotFoundException e1) {
			System.err.printf("The input file \'%s\' is not found", filename);
		} catch (IOException e) {
			System.err.println("IO error occured");
			e.printStackTrace();
		}
		return new SymbolStream(inputSymbols);
	}

	public static void main(String[] args) {
		if (args.length != 1)
			usage();
		Lexer lexer = new Lexer(scanFile(args[0]));
		lexer.scan();
		lexer.printTokens(); //for debug
		//Parser p = new Parser(lexer.getTokens());
	}
}
