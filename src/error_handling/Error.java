package error_handling;

public abstract class Error {
	protected enum Type {
		FATAL_ERROR, WARNING, ERROR
	}

	protected Type type;
	protected String what;
	protected Object arg;
	protected String description;

	public Error(Type type, String msg) {
		this(type, msg, null, null);
	}

	public Error(Type type, String msg, Object arg) {
		this(type, msg, arg, null);
	}

	public Error(Type type, String msg, Object arg, String description) {
		this.type = type;
		this.what = msg;
		this.arg = arg;
		this.description = description;
	}

	public String get() {
		String msg = (arg == null) ? String.format("%s: %s", type, what)
				: String.format("%s: %s \'%s\'", type, what, arg);
		if (description != null)
			msg += String.format("\n%s", description);

		return msg;
	}

}
