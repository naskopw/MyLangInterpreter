package language_elements;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.type_system.Type;
import prelexer.Position;

public class FunctionArgument extends ASTNode {
	private Type type;
	private Identifier id;

	public FunctionArgument(Position pos, Type type, Identifier id) {
		super(pos);
		this.type = type;
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public Identifier getIdentifier() {
		return id;
	}

	@Override
	public <T, A> T accept(TypedVisitor<T, A> v, A arg) {
		return v.visit(this, null);
	}

	@Override
	public <A> void accept(VoidVisitor<A> v, A arg) {
		v.visit(this, arg);
	}
}