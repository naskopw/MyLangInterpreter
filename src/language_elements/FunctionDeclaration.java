package language_elements;

import java.util.Collection;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import language_elements.statement.BlockStatement;
import language_elements.type_system.Type;
import prelexer.Position;

public class FunctionDeclaration extends ASTNode {
	private static final FunctionArgument[] VOID_ARGS = new FunctionArgument[0];

	private Type retType;
	private Identifier id;

	private FunctionArgument[] args;
	private BlockStatement body;

	public FunctionDeclaration(Position pos, Type retType, Identifier id, Collection<FunctionArgument> args, BlockStatement body) {
		super(pos);
		this.retType = retType;
		this.id = id;
		this.args = (args == null) ? VOID_ARGS : args.toArray(new FunctionArgument[args.size()]);
		this.body = body;
	}

	public Type getType() {
		return retType;
	}

	public Identifier getId() {
		return id;
	}

	public FunctionArgument[] getArgs() {
		return args;
	}

	public Type[] getArgsTypes() {
		Type[] t = new Type[args.length];
		for (int i = 0; i < args.length; i++)
			t[i] = args[i].getType();
		return t;
	}

	public BlockStatement getBody() {
		return body;
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