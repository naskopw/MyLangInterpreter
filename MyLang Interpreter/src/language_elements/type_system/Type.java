package language_elements.type_system;

import error_handling.MessageTemplater;

public abstract class Type {

	// used to instantiate the right type in the type checker
	public static final Type INT = new IntType();
	public static final Type FLOAT = new FloatType();
	public static final Type BOOL = new BoolType();
	public static final Type STRING = null;
	public static final Type VOID = new VoidType();

	public enum Primitive {
		INT, FLOAT, BOOL, STRING, VOID
	}

	private Primitive type;

	public Primitive getType() {
		return type;
	}

	protected Type(Primitive type) {
		this.type = type;
	}

	public boolean isOfType(Type.Primitive t) {
		return t == type;
	}

	public static Type getPrimitiveType(lex.Token.Type type) {
		switch (type) {
		case T_INT_TYPE:
			return INT;
		case T_FLOAT_TYPE:
			return FLOAT;
		case T_BOOL_TYPE:
			return BOOL;
		case T_STRING_TYPE:
			return STRING;
		case T_VOID_TYPE:
			return VOID;
		default:
			throw new IllegalArgumentException(MessageTemplater.UnknownType + type);
		}
	}

	public abstract Type plus(Type other);

	public abstract Type minus(Type other);

	public abstract Type multiplication(Type other);

	public abstract Type division(Type other);

	public abstract Type relationalOperation(Type other);

	public abstract Type equalityOperation(Type other);

	public abstract Type logicalOperation(Type other);

	@Override
	public abstract String toString();
}
