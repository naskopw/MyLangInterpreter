package interpreter.ast_visitor;

public interface Visitable {
	<T, A> T accept(TypedVisitor<T, A> v, A arg);

	<A> void accept(VoidVisitor<A> v, A arg);
}
