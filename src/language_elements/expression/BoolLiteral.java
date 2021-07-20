package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.type_system.Type;
import prelexer.Position;

public class BoolLiteral extends Expression {
	private boolean value;

	public BoolLiteral(Position start, boolean b) {
		super(start);
		this.value = b;
		setType(Type.BOOL);
	}

	public boolean getValue() {
		return value;
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