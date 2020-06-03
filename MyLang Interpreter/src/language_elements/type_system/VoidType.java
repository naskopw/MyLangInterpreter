package language_elements.type_system;

public class VoidType extends Type {

	public VoidType() {
		super(Type.Primitive.VOID);
	}

	@Override
	public Type plus(Type other) {
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
	public Type logicalOperation(Type other) {
		return null;
	}

	@Override
	public Type relationalOperation(Type other) {
		return null;
	}

	@Override
	public Type equalityOperation(Type other) {
		return null;
	}

	@Override
	public String toString() {
		return "void";
	}

}