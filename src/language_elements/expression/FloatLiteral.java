package language_elements.expression;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.type_system.Type;
import prelexer.Position;

public class FloatLiteral extends Expression {
	private float value;

	public FloatLiteral(Position start, float value) {
		super(start);
		this.value = value;
		setType(Type.FLOAT);
	}

	public float getValue() {
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