package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.Identifier;

public class VarLiteral extends Lvalue {
	private Identifier id;

	public VarLiteral(Identifier id) {
		super(id.getPosition());
		this.id = id;
	}

	public Identifier getId() {
		return id;
	}

	@Override
	public String toString() {
		return id.getVal();
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