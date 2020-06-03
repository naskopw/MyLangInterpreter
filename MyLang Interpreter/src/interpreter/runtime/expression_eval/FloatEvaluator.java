package interpreter.runtime.expression_eval;

import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.Visitable;
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

public class FloatEvaluator implements TypedVisitor<Float, Object> {

	@Override
	public Float visit(ArithmeticBinExpression e, Object arg) {
		float l = e.getLeft().accept(this, arg);
		float r = e.getRight().accept(this, arg);

		switch (e.getOperation()) {
		case PLUS:
			return l + r;
		case MINUS:
			return l - r;
		case MULT:
			return l * r;
		case DIV:
			return l / r;
		default:
			return null;
		}
	}

	@Override
	public Float visit(FloatLiteral i, Object o) {
		return i.getValue();
	}

	@Override
	public Float visit(IntegerLiteral i, Object o) {
		return (float) i.getValue();
	}

	@Override
	public Float visit(Visitable v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(TranslationUnit p, Object argumentument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(IfStatement i, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(WhileStatement w, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(BlockStatement b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(ForStatement f, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(PrintStatement p, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(ReturnStatement r, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(BreakStatement b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(ContinueStatement c, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(VarDecl v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(LogicalExpression l, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(EqualityExpression e, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(RelationalExpression r, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(LogicalNotExpression n, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(PostIncrementOperation p, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(AssignExpression e, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(BoolLiteral b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(VarLiteral v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(FuncCallExpression f, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Float visit(FunctionDeclaration d, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

}
