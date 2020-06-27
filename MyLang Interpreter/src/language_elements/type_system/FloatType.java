package language_elements.type_system;

public class FloatType extends Type {

	public FloatType() {
		super(Type.Primitive.FLOAT);
	}

	@Override
	public Type plus(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.FLOAT;
		if (other == Type.STRING)
			return Type.STRING;
		return null;
	}

	@Override
	public Type minus(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.FLOAT;
		return null;
	}

	@Override
	public Type multiplication(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.FLOAT;
		return null;
	}

	@Override
	public Type division(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.FLOAT;
		return null;
	}

	@Override
	public Type relationalOperation(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.BOOL;
		return null;
	}

	@Override
	public Type equalityOperation(Type other) {
		if (other == Type.FLOAT || other == Type.INT)
			return Type.BOOL;
		return null;
	}

	@Override
	public Type logicalOperation(Type other) {
		return null;
	}

	@Override
	public boolean isCompatible(Type other) {
		return false;
	}

	@Override
	public boolean isArray() {
		return false;
	}

	@Override
	public String toString() {
		return "float";
	}

}