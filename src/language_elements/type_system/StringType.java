package language_elements.type_system;

public class StringType extends Type {

    public StringType() {
        super(Primitive.STRING);
    }

    @Override
    public Type plus(Type other) {
        return Type.STRING;
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
        if(other == Type.STRING)
            return Type.BOOL;
        return null;
    }

    @Override
    public boolean isCompatible(Type other) {
        return other == Type.STRING;
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public String toString() {
        return "STRING";
    }

}
