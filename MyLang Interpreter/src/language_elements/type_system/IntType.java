package language_elements.type_system;

public class IntType extends Type {

	public IntType() {
		super(Type.Primitive.INT);

	}

	@Override
	public Type plus(Type other) {
		if (other.isOfType(Type.Primitive.INT))
			return Type.INT;
		if (other.isOfType(Type.Primitive.FLOAT))
			return Type.FLOAT;
		if (other.isOfType(Type.Primitive.STRING))
			return Type.STRING;
		return null;
	}

	@Override
	public Type minus(Type other) {
		if (other.isOfType(Type.Primitive.INT))
			return Type.INT;
		if (other.isOfType(Type.Primitive.FLOAT))
			return Type.FLOAT;

		return null;
	}

	@Override
	public Type multiplication(Type other) {
		if (other.isOfType(Type.Primitive.INT))
			return Type.INT;
		if (other.isOfType(Type.Primitive.FLOAT))
			return Type.FLOAT;

		return null;
	}

	@Override
	public Type division(Type other) {
		if (other.isOfType(Type.Primitive.INT))
			return Type.INT;
		if (other.isOfType(Type.Primitive.FLOAT))
			return Type.FLOAT;

		return null;
	}

	@Override
	public Type relationalOperation(Type other) {
		if (other.isOfType(Type.Primitive.INT) || other.isOfType(Type.Primitive.FLOAT))
			return Type.BOOL;

		return null;
	}

	@Override
	public Type equalityOperation(Type other) {
		if (other.isOfType(Type.Primitive.INT) || other.isOfType(Type.Primitive.FLOAT))
			return Type.BOOL;

		return null;
	}

	@Override
	public Type logicalOperation(Type other) {
		return null;
	}

	@Override
	public String toString() {
		return "int";
	}

}