package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.Visitable;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.type_system.Type;
import prelexer.Position;

public class IntegerLiteral extends Expression implements Visitable {
	private int value;

	public IntegerLiteral(Position start, int value) {
		super(start);
		this.value = value;
		setType(Type.INT);

	}

	public int getValue() {
		return value;
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