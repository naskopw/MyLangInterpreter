package language_elements;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.VoidVisitor;
import prelexer.Position;

public class TranslationUnit extends ASTNode {
	private Map<String, FunctionDeclaration> functions;

	public TranslationUnit(Position start, Collection<FunctionDeclaration> functions) {
		super(start);
		this.functions = new LinkedHashMap<>();
		for (FunctionDeclaration d : functions) {
			if (this.functions.get(d.getId().getVal()) != null)
				ErrorHandler.raise(new Fatal(MessageTemplater.FuncAlreadyDeclared, d.getId()), d.getPosition());
			this.functions.put(d.getId().getVal(), d);
		}
	}

	public Map<String, FunctionDeclaration> getFunctions() {
		return functions;
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