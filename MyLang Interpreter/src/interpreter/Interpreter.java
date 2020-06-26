package interpreter;

import java.util.Map;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.runtime.FunctionExecutor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;

public class Interpreter {
	public enum Mode {
		LINE_BY_LINE, COMPILE
	}

	private RuntimeEnvironment runtime;

	public void run(TranslationUnit program, Mode mode) {
		if (mode.equals(Mode.COMPILE)) {
			if (!program.getFunctions().keySet().contains("main"))
				ErrorHandler.raise(new Fatal(MessageTemplater.NoEntryPoint));
			runtime = new RuntimeEnvironment(program.getFunctions());
			FunctionExecutor funcExecutor = new FunctionExecutor(runtime);

			for (Map.Entry<String, FunctionDeclaration> func : program.getFunctions().entrySet()) {
				runtime.enterScope(func.getKey());
				funcExecutor.execute(func.getValue());
				runtime.leaveScope(func.getKey());
			}
		} else {
			System.out.println(Mode.LINE_BY_LINE);
		}
	}
}
