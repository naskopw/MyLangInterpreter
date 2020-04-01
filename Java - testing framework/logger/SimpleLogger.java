package logger;

import java.util.List;

import core.Test;

public class SimpleLogger extends Logger {
	@Override
	public void logData(String className, List<Test> tests) {
		System.out.printf("Running %d tests for class %s\n", tests.size(), className);
		int index = 1;
		int failed = 0;
		int successful = 0;

		for (Test test : tests) {
			System.out.printf("Test case %d \"%s\" -----> %s\n", index, test.getMethodName(),
					test.getResult() ? "SUCCESS" : "FAIL");
			
			if (test.getResult())
				successful++;
			else
				failed++;
			
			index++;
		}
		System.out.println("-----------------------------------------------");
		System.out.printf("Total failed tests: %d/%d\n", failed, failed + successful);
		System.out.printf("Total successful tests: %d/%d\n", successful, failed + successful);
	}
}
