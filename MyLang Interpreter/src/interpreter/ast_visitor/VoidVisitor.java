package interpreter.ast_visitor;

import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;
import language_elements.expression.ArithmeticBinExpression;
import language_elements.expression.AssignExpression;
import language_elements.expression.BoolLiteral;
import language_elements.expression.EqualityExpression;
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
import language_elements.statement.VarDecl;
import language_elements.statement.WhileStatement;

public interface VoidVisitor<A> {
	void visit(Visitable v, A arg);

	void visit(TranslationUnit p, A arg);

	void visit(IfStatement i, A arg);

	void visit(WhileStatement w, A arg);

	void visit(ForStatement f, A arg);

	void visit(BlockStatement b, A arg);

	void visit(PrintStatement p, A arg);

	void visit(ReturnStatement r, A arg);

	void visit(BreakStatement b, A arg);

	void visit(ContinueStatement c, A arg);

	void visit(VarDecl v, A arg);

	void visit(ArithmeticBinExpression e, A arg);

	void visit(LogicalExpression l, A arg);

	void visit(RelationalExpression r, A arg);

	void visit(EqualityExpression e, A arg);

	void visit(LogicalNotExpression n, A arg);

	void visit(PostIncrementOperation p, A arg);

	void visit(AssignExpression e, A arg);

	void visit(FloatLiteral f, A arg);

	void visit(IntegerLiteral i, A arg);

	void visit(BoolLiteral b, A arg);

	void visit(VarLiteral v, A arg);

	void visit(FuncCallExpression f, A arg);

	void visit(FunctionDeclaration d, A arg);
}