package error_handling;

import prelexer.Position;

//Simple error handling
//just for debugging

public class ErrorHandler {
	String msg;

	public static void raise(Error err) {
		raise(err, null);
	}

	public static void raise(Error err, Position pos) {
		String msg = err.get();
		if (pos != null)
			msg += pos;
		System.err.println(msg);
		if (err.type == Error.Type.FATAL_ERROR)
			System.exit(1);
	}
}
