package language_elements.type_system;

import error_handling.MessageTemplater;

import java.util.HashMap;

public abstract class Type {

    // used to instantiate the right type in the type checker
    public static final Type INT = new IntType();
    public static final Type FLOAT = new FloatType();
    public static final Type BOOL = new BoolType();
    public static final Type STRING = new StringType();
    public static final Type VOID = new VoidType();

    public enum Primitive {
        INT, FLOAT, BOOL, STRING, VOID, ARRAY
    }

	private static final HashMap<Type, ArrayType> arrayFromType = new HashMap<>();

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

	public static ArrayType arrayType(Type t) {
		if(!arrayFromType.containsKey(t)) {
			ArrayType at = new ArrayType(t);
			arrayFromType.put(t, at);
		}
		return arrayFromType.get(t);
	}


	public abstract Type plus(Type other);

    public abstract Type minus(Type other);

    public abstract Type multiplication(Type other);

    public abstract Type division(Type other);

    public abstract Type relationalOperation(Type other);

    public abstract Type equalityOperation(Type other);

    public abstract Type logicalOperation(Type other);

    public abstract boolean isCompatible(Type other);

    public abstract boolean isArray();

    @Override
    public abstract String toString();
}
