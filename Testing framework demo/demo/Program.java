package demo;

import core.TestExecutor;

public class Program {


	public static int sum(int x,int y)
	{
		return x+y;
	}

	public static void main(String[] args) throws Exception {
		TestExecutor executor = new TestExecutor(DemoTests.class.getName());
		executor.executeTests();
	}

}
