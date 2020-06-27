package language_elements.type_system;

public class BoolType extends Type {

	protected BoolType() {
		super(Type.Primitive.BOOL);
	}

	@Override
	public Type plus(Type other) {
		if (other == Type.STRING)
			return Type.STRING;
		return null;
	}

	@Override
	public Type minus(Type other) {
		return null;
	}

	@Override
	public Type multiplication(Type other) {
		return null;
	}

	@Override
	public Type division(Type other) {
		return null;
	}

	@Override
	public Type relationalOperation(Type other) {
		return null;
	}

	@Override
	public Type equalityOperation(Type other) {
		if (other == Type.BOOL)
			return Type.BOOL;

		return null;
	}

	@Override
	public Type logicalOperation(Type other) {
		if (other == Type.BOOL)
			return Type.BOOL;

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
		return "bool";
	}

}