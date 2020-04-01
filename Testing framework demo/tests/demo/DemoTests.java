package demo;

import annotations.CustomTest;
import annotations.CustomTestClass;
import asserts.CustomAssert;

@CustomTestClass
public class DemoTests {
	
	@CustomTest
	public boolean Test2x2()
	{
		return CustomAssert.AssertEquals(6, Program.sum(3, 3));
	}
	@CustomTest
	public boolean TestFail()
	{
		return CustomAssert.AssertEquals(4, Program.sum(1,1));
	}
}
