package language_elements.statement;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.Identifier;
import language_elements.expression.Expression;
import language_elements.type_system.Type;
import prelexer.Position;

public class VarDecl extends Statement {
	private Type type;
	private Identifier identifier;
	private Expression init;

	public VarDecl(Position pos, Type type, Identifier identifier) {
		super(pos);
		this.type = type;
		this.identifier = identifier;
	}

	public VarDecl(Position pos, Type type, Identifier identifier, Expression init) {
		this(pos, type, identifier);
		this.init = init;
	}

	public Type getType() {
		return type;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Expression getInitializer() {
		return init;
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