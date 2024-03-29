package interpreter.ast_visitor;

import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;
import language_elements.expression.*;
import language_elements.statement.*;

public class CommonVoidVisitor<A> implements VoidVisitor<A> {

	@Override
	public void visit(Visitable v, A arg) {
	}

	@Override
	public void visit(TranslationUnit p, A arg) {
		for (String id : p.getFunctions().keySet()) {
			p.getFunctions().get(id).accept(this, arg);
		}
	}

	@Override
	public void visit(IfStatement v, A arg) {
		v.getCondition().accept(this, arg);
		v.getThenStatement().accept(this, arg);
		if (v.getElseStatement() != null)
			v.getElseStatement().accept(this, arg);
	}

	@Override
	public void visit(WhileStatement v, A arg) {
		v.getCondition().accept(this, arg);
		v.getBody().accept(this, null);
	}

	@Override
	public void visit(ForStatement v, A arg) {
		if (v.getInit() != null)
			v.getInit().accept(this, arg);
		if (v.getCond() != null)
			v.getCond().accept(this, arg);
		if (v.getIncr() != null)
			v.getIncr().accept(this, arg);
		v.getBody().accept(this, arg);
	}

	@Override
	public void visit(BlockStatement v, A arg) {
		for (Statement s : v.getStatements()) {
			s.accept(this, arg);
		}
	}

	@Override
	public void visit(PrintStatement p, A arg) {
		p.getExpression().accept(this, arg);
	}

	@Override
	public void visit(ReturnStatement r, A arg) {
		if (r.getExpression() != null)
			r.getExpression().accept(this, arg);
	}

	@Override
	public void visit(BreakStatement b, A arg) {
	}

	@Override
	public void visit(ContinueStatement c, A arg) {
	}

	@Override
	public void visit(VarDecl v, A arg) {
		if (v.getInitializer() != null)
			v.getInitializer().accept(this, arg);
	}

	@Override
	public void visit(ArithmeticBinExpression e, A arg) {
		e.getLeft().accept(this, arg);
		e.getRight().accept(this, arg);
	}

	@Override
	public void visit(LogicalExpression l, A arg) {
		l.getLeft().accept(this, arg);
		l.getRight().accept(this, arg);
	}

	@Override
	public void visit(RelationalExpression r, A arg) {
		r.getLeft().accept(this, arg);
		r.getRight().accept(this, arg);
	}

	@Override
	public void visit(EqualityExpression e, A arg) {
		e.getLeft().accept(this, arg);
		e.getRight().accept(this, arg);
	}

	@Override
	public void visit(LogicalNotExpression n, A arg) {
		n.getExpression().accept(this, arg);
	}

	@Override
	public void visit(PostIncrementOperation p, A arg) {
		p.getExpression().accept(this, arg);
	}

	@Override
	public void visit(AssignExpression e, A arg) {
		e.getLvalue().accept(this, arg);
		e.getExpression().accept(this, arg);
	}

	@Override
	public void visit(FloatLiteral f, A arg) {
	}

	@Override
	public void visit(IntegerLiteral i, A arg) {
	}

	@Override
	public void visit(BoolLiteral b, A arg) {
	}

	@Override
	public void visit(StringLiteral s, A arg) {

	}

	@Override
	public void visit(VarLiteral v, A arg) {
	}

	@Override
	public void visit(FuncCallExpression f, A arg) {
		for (Expression e : f.getArgs()) {
			e.accept(this, arg);
		}
	}

	@Override
	public void visit(FunctionDeclaration d, A arg) {
		d.getBody().accept(this, arg);
	}

	@Override
	public void visit(ArrayDeclaration a, A arg) {
		for(Expression e : a.getDimensions()) {
			e.accept(this, arg);
		}
	}

	@Override
	public void visit(ArrayAccess a, A obj) {
		a.getLvalue().accept(this, obj);
		a.getIndex().accept(this, obj);
	}
}