package interpreter;

import java.util.Map;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.runtime.FunctionExecutor;
import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;

public class Interpreter {
	public enum Mode {
		LINE_BY_LINE, COMPILE
	}

	public void run(TranslationUnit program, Mode mode) {
		if (mode.equals(Mode.COMPILE)) {
			FunctionExecutor funcExecutor = new FunctionExecutor();
			if (!program.getFunctions().keySet().contains("main"))
				ErrorHandler.raise(new Fatal(MessageTemplater.NoEntryPoint));
			for (Map.Entry<String, FunctionDeclaration> func : program.getFunctions().entrySet()) {
				funcExecutor.execute(func.getValue());
			}
		} else {
			System.out.println(Mode.LINE_BY_LINE);
		}
	}
}
