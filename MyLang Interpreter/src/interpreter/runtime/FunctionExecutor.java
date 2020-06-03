package interpreter.runtime;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.ast_visitor.Visitable;
import interpreter.ast_visitor.VoidVisitor;
import interpreter.runtime.expression_eval.FloatEvaluator;
import interpreter.runtime.expression_eval.IntegerEvaluator;
import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;
import language_elements.expression.ArithmeticBinExpression;
import language_elements.expression.AssignExpression;
import language_elements.expression.BoolLiteral;
import language_elements.expression.EqualityExpression;
import language_elements.expression.Expression;
import language_elements.expression.FloatLiteral;
import language_elements.expression.FuncCallExpression;
import language_elements.expression.IntegerLiteral;
import language_elements.expression.LogicalExpression;
import language_elements.expression.LogicalNotExpression;
import language_elements.expression.PostIncrementOperation;
import language_elements.expression.RelationalExpression;
import language_elements.expression.VarLiteral;
import language_elements.statement.BlockStatement;
import language_elements.statement.BreakStatement;
import language_elements.statement.ContinueStatement;
import language_elements.statement.ForStatement;
import language_elements.statement.IfStatement;
import language_elements.statement.PrintStatement;
import language_elements.statement.ReturnStatement;
import language_elements.statement.Statement;
import language_elements.statement.VarDecl;
import language_elements.statement.WhileStatement;
import language_elements.type_system.Type;
import language_elements.type_system.TypeChecker;

public class FunctionExecutor implements VoidVisitor<Object> {
	TypeChecker typeChecker;
	Object returnValue = null;

	public FunctionExecutor(FunctionDeclaration function) {
		execute(function);
	}

	public FunctionExecutor() {

	}

	public void execute(FunctionDeclaration function) {
		typeChecker = new TypeChecker();
		for (Statement st : function.getBody().getStatements()) {
			st.accept(this, null);
		}
	}

	@Override
	public void visit(ReturnStatement v, Object frame) {
		if (v.getExpression() != null)
			returnValue = interpretExpression(v.getExpression());
		System.out.println("More implementation required");
	}

	public void visit(PrintStatement v, Object frame) {
		if (v.isNewLine())
			System.out.println(interpretExpression(v.getExpression()));
		else
			System.out.print(interpretExpression(v.getExpression()));
	}

	private Object interpretExpression(Expression e) {
		Type type = typeChecker.evaluate(e);
		switch (type.getType()) {
		case INT:
			return e.accept(new IntegerEvaluator(), null);
		case FLOAT:
			return e.accept(new FloatEvaluator(), null);
		default:
			ErrorHandler.raise(new Fatal(MessageTemplater.UnknownType));
			return null;
		}
	}

	@Override
	public void visit(Visitable v, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(TranslationUnit p, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IfStatement i, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(WhileStatement w, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ForStatement f, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BlockStatement b, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BreakStatement b, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ContinueStatement c, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VarDecl v, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(ArithmeticBinExpression e, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LogicalExpression l, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(RelationalExpression r, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(EqualityExpression e, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(LogicalNotExpression n, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(PostIncrementOperation p, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(AssignExpression e, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FloatLiteral f, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(IntegerLiteral i, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(BoolLiteral b, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(VarLiteral v, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FuncCallExpression f, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visit(FunctionDeclaration d, Object arg) {
		// TODO Auto-generated method stub

	}
}
