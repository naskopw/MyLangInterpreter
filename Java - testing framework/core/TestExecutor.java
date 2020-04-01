package core;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import annotations.CustomTest;
import annotations.CustomTestClass;
import logger.Logger;
import logger.SimpleLogger;

public class TestExecutor {
	private Logger logger;
	private List<Test> cases;
	private String className;

	public TestExecutor(String className) {
		this.className = className;
		this.logger = new SimpleLogger();
		this.cases = new LinkedList<Test>();
	}

	private void publishReport() throws Exception {
		if (cases.size() == 0)
			throw new Exception(
					"Class " + className + " does not contain any test cases");

		logger.logData(className, cases);
	}

	public void executeTests() throws Exception {

		Class<?> c = Class.forName(className);
		Object instance = c.getDeclaredConstructor().newInstance();

		if (c.getAnnotation(CustomTestClass.class) == null)
			throw new Exception("Class " + className + " not annotated with " + CustomTestClass.class.getName());

		for (Method current : c.getMethods()) {
			if (current.getAnnotation(CustomTest.class) != null) {
				boolean result = (boolean) current.invoke(instance);
				cases.add(new Test(result, current.getName()));
			}
		}
		publishReport();

	}
}
