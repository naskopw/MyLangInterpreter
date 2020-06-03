package language_elements.type_system;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import interpreter.ast_visitor.TypedVisitor;
import interpreter.ast_visitor.Visitable;
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
import language_elements.statement.VarDecl;
import language_elements.statement.WhileStatement;

public class TypeChecker implements TypedVisitor<Type, Object> {

	public Type evaluate(Expression e) {
		return e.accept(this, null);
	}

	@Override
	public Type visit(ArithmeticBinExpression e, Object obj) {

		Type left = e.getLeft().accept(this, null);
		Type right = e.getRight().accept(this, null);
		Type res = null;
		switch (e.getOperation()) {
		case DIV:
			res = left.division(right);
			break;
		case MINUS:
			res = left.minus(right);
			break;
		case MULT:
			res = left.multiplication(right);
			break;
		case PLUS:
			res = left.plus(right);
			break;
		}

		if (res == null) {
			ErrorHandler.raise(new Fatal(MessageTemplater.UnknownType));
		}
		e.setType(res);
		return res;
	}

	@Override
	public Type visit(IntegerLiteral e, Object obj) {
		return e.getType();
	}

	@Override
	public Type visit(FloatLiteral e, Object obj) {
		return e.getType();
	}

	@Override
	public Type visit(Visitable v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(TranslationUnit p, Object argumentument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(IfStatement i, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(WhileStatement w, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(BlockStatement b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(ForStatement f, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(PrintStatement p, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(ReturnStatement r, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(BreakStatement b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(ContinueStatement c, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(VarDecl v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(LogicalExpression l, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(EqualityExpression e, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(RelationalExpression r, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(LogicalNotExpression n, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(PostIncrementOperation p, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(AssignExpression e, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(BoolLiteral b, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(VarLiteral v, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FuncCallExpression f, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(FunctionDeclaration d, Object argument) {
		// TODO Auto-generated method stub
		return null;
	}

}
