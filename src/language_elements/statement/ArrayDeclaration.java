package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.Identifier;
import language_elements.expression.ArrayAccess;
import language_elements.expression.Expression;
import language_elements.type_system.Type;
import prelexer.Position;

import java.util.Collection;

public class ArrayDeclaration extends Statement {
    private Identifier id;
    private Expression[] dimensions;
    private Type type;

    public ArrayDeclaration(Position start, Type type, Collection<Expression> dimensions, Identifier id) {
        super(start);
        this.dimensions = dimensions.toArray(new Expression[dimensions.size()]);
        this.type = type;
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public Expression[] getDimensions() {
        return dimensions;
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
        return v.visit(this, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        v.visit(this, arg);
    }

}
