package error_handling;

public class Warning extends Error {

	public Warning(String msg, Object arg, String description) {
		super(Type.WARNING, msg, arg, description);
	}

	public Warning(String msg, String description) {
		super(Type.WARNING, msg, description);
	}

	public Warning(String msg) {
		super(Type.WARNING, msg);
	}

	public Warning(Type type, String msg, Object arg) {
		super(Type.WARNING, msg, arg, null);
	}

}
