package interpreter.ast_visitor;

import language_elements.FunctionDeclaration;
import language_elements.TranslationUnit;
import language_elements.expression.*;
import language_elements.statement.*;

public interface TypedVisitor<T, S> {

    T visit(Visitable v, S argument);

    T visit(TranslationUnit p, S argumentument);

    T visit(IfStatement i, S argument);

    T visit(WhileStatement w, S argument);

    T visit(BlockStatement b, S argument);

    T visit(ForStatement f, S argument);

    T visit(PrintStatement p, S argument);

    T visit(ReturnStatement r, S argument);

    T visit(BreakStatement b, S argument);

    T visit(ContinueStatement c, S argument);

    T visit(VarDecl v, S argument);

    T visit(ArithmeticBinExpression e, S argument);

    T visit(LogicalExpression l, S argument);

    T visit(EqualityExpression e, S argument);

    T visit(RelationalExpression r, S argument);

    T visit(LogicalNotExpression n, S argument);

    T visit(PostIncrementOperation p, S argument);

    T visit(AssignExpression e, S argument);

    T visit(FloatLiteral f, S argument);

    T visit(IntegerLiteral i, S argument);

    T visit(BoolLiteral b, S argument);

    T visit(StringLiteral s, S argument);

    T visit(VarLiteral v, S argument);

    T visit(FuncCallExpression f, S argument);

    T visit(FunctionDeclaration d, S argument);

    T visit(ArrayDeclaration a, S obj);

    T visit(ArrayAccess a, S obj);

}
