package language_elements.expression;

import java.util.Collection;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.Identifier;

public class FuncCallExpression extends Expression {
	private static final Expression[] VOID_ARGS = new Expression[0];

	private final Identifier funcName;
	private final Expression[] args;
	private boolean isNative;

	public FuncCallExpression(Identifier funcName, Collection<Expression> args) {
		super(funcName.getPosition());
		this.args = args == null ? VOID_ARGS : args.toArray(new Expression[args.size()]);
		this.funcName = funcName;
	}

	public FuncCallExpression(Identifier funcName) {
		this(funcName, null);
	}

	public Identifier getFuncName() {
		return funcName;
	}

	public Expression[] getArgs() {
		return args;
	}

	public boolean isNative() {
		return isNative;
	}

	public void setNative(boolean isNative) {
		this.isNative = isNative;
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