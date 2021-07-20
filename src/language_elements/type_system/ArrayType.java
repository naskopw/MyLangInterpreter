package language_elements.type_system;

public class ArrayType extends Type {
    private Type internal;

    protected ArrayType(Type internal) {
        super(Primitive.ARRAY);
        this.internal = internal;
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
        if(this == other)
            return Type.BOOL;
        return null;
    }

    @Override
    public boolean isCompatible(Type other) {
        //array with null internal type represents a generic array
        if(other.isArray() && ((ArrayType) other).getInternalType() == null)
            return true;
        return this == other;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public String toString() {
        return internal + "[]";
    }

    public Type getInternalType() {
        return internal;
    }

}