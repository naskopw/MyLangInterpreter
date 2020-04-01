package core;

public class Test {
	private boolean result;
	private String methodName;

	public Test(boolean result, String methodName) {
		this.setResult(result);
		this.setMethodName(methodName);
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
