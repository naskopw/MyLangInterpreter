package error_handling;

public class Fatal extends Error {

	public Fatal(String msg, Object arg, String description) {
		super(Type.FATAL_ERROR, msg, arg, description);
	}

	public Fatal(String msg, Object arg) {
		super(Type.FATAL_ERROR, msg, arg);
	}

	public Fatal(String msg) {
		super(Type.FATAL_ERROR, msg);
	}
}
