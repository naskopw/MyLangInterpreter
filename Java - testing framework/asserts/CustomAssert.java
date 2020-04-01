package asserts;

public class CustomAssert {
	public static boolean AssertEquals(Object expected, Object actual) {
		return expected.equals(actual) ? true : false;
	}
}
