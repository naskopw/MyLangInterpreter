package parser;

import java.util.ArrayList;
import java.util.List;

import error_handling.ErrorHandler;
import error_handling.Fatal;
import error_handling.MessageTemplater;
import language_elements.FunctionArgument;
import language_elements.FunctionDeclaration;
import language_elements.Identifier;
import language_elements.TranslationUnit;
import language_elements.expression.ArithmeticBinExpression;
import language_elements.expression.AssignExpression;
import language_elements.expression.BoolLiteral;
import language_elements.expression.EqualityExpression;
import language_elements.expression.Expression;
import language_elements.expression.FloatLiteral;
import language_elements.expression.FuncCallExpression;
import language_elements.expression.IncrementOperator;
import language_elements.expression.IntegerLiteral;
import language_elements.expression.LogicalExpression;
import language_elements.expression.LogicalNotExpression;
import language_elements.expression.PostIncrementOperation;
import language_elements.expression.RelationalExpression;
import language_elements.expression.VarLiteral;
import language_elements.expression.ArithmeticBinExpression.ArithmeticBinOperation;
import language_elements.expression.EqualityExpression.EqualityOperation;
import language_elements.expression.LogicalExpression.BooleanBinOperation;
import language_elements.expression.RelationalExpression.RelationalOperation;
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
import lex.Token;
import lex.TokenStream;
import prelexer.Position;
import lex.Token.Type;

public class Parser {

	private TokenStream stream;

	public Parser(TokenStream stream) {
		this.stream = stream;
	}

	public TranslationUnit parse() {
		return parseProgram();
	}

	private TranslationUnit parseProgram() {
		List<FunctionDeclaration> functions = new ArrayList<>();
		while (stream.hasNext() && !stream.peek().isOfType(Type.T_EOF)) {
			functions.add(parseFuncDeclaration());
		}
		return new TranslationUnit(new Position(0), functions);
	}

	private FunctionDeclaration parseFuncDeclaration() {
		language_elements.type_system.Type returnType = parseType();

		Token id = require(Type.T_IDENTIFIER);
		Identifier funcName = new Identifier(id.getPosition(), id.getValue());

		require(Type.T_L_PAREN);
		List<FunctionArgument> arguments = new ArrayList<>();
		while (!stream.peek().isOfType(Type.T_R_PAREN)) {
			language_elements.type_system.Type argumentType = parseType();

			id = require(Type.T_IDENTIFIER);

			Identifier argumentIdentifier = new Identifier(id.getPosition(), id.getValue());
			arguments.add(new FunctionArgument(argumentIdentifier.getPosition(), argumentType, argumentIdentifier));

			if (stream.peek().isOfType(Type.T_COMMA))
				stream.next();
		}
		require(Type.T_R_PAREN);
		BlockStatement body = parseBlockStatement();
		return new FunctionDeclaration(funcName.getPosition(), returnType, funcName, arguments, body);
	}

	private language_elements.type_system.Type parseType() {
		language_elements.type_system.Type t = null;
		try {
			t = language_elements.type_system.Type.getPrimitiveType(stream.next().getType());
		} catch (IllegalArgumentException e) {
			ErrorHandler.raise(new Fatal(MessageTemplater.UnknownType));
		}
		return t;
	}

	// STATEMENTS
	private Statement parseStatement() {
		switch (stream.peek().getType()) {
		case T_IF:
			return parseIfStatement();
		case T_WHILE:
			return parseWhileStatement();
		case T_FOR:
			return parseForStatement();
		case T_L_CURRLY_PAREN:
			return parseBlockStatement();
		case T_PRINT:
			return parsePrintStatement(false);
		case T_PRINT_LN:
			return parsePrintStatement(true);
		case T_RETURN:
			return parseReturnStatement();
		case T_CONTINUE:
			return new ContinueStatement(stream.next().getPosition());
		case T_BREAK:
			return new BreakStatement(stream.next().getPosition());
		default:
			Statement s = parseExpression();
			return s;
		}
	}

	private BlockStatement parseBlockStatement() {
		Position start = require(Type.T_L_CURRLY_PAREN).getPosition();
		Token peek = null;
		List<Statement> statements = new ArrayList<>();
		while (!(peek = stream.peek()).isOfType(Type.T_R_CURRLY_PAREN)) {
			if (peek.isPrimitiveType()) {
				statements.add(parseVarDeclaration());
			} else {
				statements.add(parseStatement());
			}
		}

		require(Type.T_R_CURRLY_PAREN);
		return new BlockStatement(statements, start);
	}

	private Statement parseVarDeclaration() {
		Token typeToken = stream.next();
		language_elements.type_system.Type type = language_elements.type_system.Type
				.getPrimitiveType(typeToken.getType());

		Token identifierToken = require(Type.T_IDENTIFIER);
		Identifier identifier = new Identifier(identifierToken.getPosition(), identifierToken.getValue());

		Expression initializer = null;
		if (stream.peek().isOfType(Type.T_EQUALS)) {
			require(Type.T_EQUALS);
			initializer = new AssignExpression(new VarLiteral(identifier), parseExpression());
		}
		return new VarDecl(typeToken.getPosition(), type, identifier, initializer);

	}

	private Statement parseReturnStatement() {
		Position start = require(Type.T_RETURN).getPosition();
		Expression e = null;

		if (stream.peek().isOfType(Type.T_SEMI))
			stream.next();
		else {
			e = parseExpression();
		}
		return new ReturnStatement(start, e);
	}

	private Statement parseIfStatement() {
		Position start = require(Type.T_IF).getPosition();

		require(Type.T_L_PAREN);
		Expression e = parseExpression();
		require(Type.T_R_PAREN);

		Statement thenBody = parseStatement();
		Statement elseBody = null;

		if (stream.peek().isOfType(Type.T_ELSE)) {
			stream.next();
			elseBody = parseStatement();
		}
		return new IfStatement(e, thenBody, elseBody, start);
	}

	private Statement parseWhileStatement() {
		Position start = require(Type.T_WHILE).getPosition();

		require(Type.T_L_PAREN);
		Expression cond = parseExpression();
		require(Type.T_R_PAREN);

		Statement body = parseStatement();
		return new WhileStatement(cond, body, start);
	}

	private Statement parseForStatement() {
		Position start = require(Type.T_FOR).getPosition();
		require(Type.T_L_PAREN);

		Statement init = null;
		if (!stream.peek().isOfType(Type.T_SEMI))
			init = varDeclarationOrExpression();

		require(Type.T_SEMI);

		Expression cond = null;
		if (!stream.peek().isOfType(Type.T_SEMI))
			cond = parseExpression();

		require(Type.T_SEMI);

		Expression action = null;
		if (!stream.peek().isOfType(Type.T_R_PAREN))
			action = parseExpression();
		require(Type.T_R_PAREN);
		Statement body = parseStatement();

		return new ForStatement(start, init, cond, action, body);
	}

	private Statement varDeclarationOrExpression() {
		if (stream.peek().isPrimitiveType()) {
			return parseVarDeclaration();
		} else {
			return parseExpression();
		}
	}

	private Statement parsePrintStatement(boolean isNewLine) {
		Position start = stream.next().getPosition();
		Expression e = parseExpression();
		return new PrintStatement(start, e, isNewLine);
	}

	// EXPRESSIONS
	private Expression parseExpression() {
		return parseLogicalExpression();
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parseLogicalExpression() {
		Expression left = parseEqualityExpression();

		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_OR) || operation.isOfType(Type.T_AND)) {
			stream.next();
			Expression right = parseEqualityExpression();

			switch (operation.getType()) {
			case T_OR:
				left = new LogicalExpression(BooleanBinOperation.OR, left, right);
				break;
			case T_AND:
				left = new LogicalExpression(BooleanBinOperation.AND, left, right);
				break;
			}
		}
		return left;
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parseEqualityExpression() {
		Expression left = parseRelationalExpression();

		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_DOUBLE_EQUALS) || operation.isOfType(Type.T_NOT_EQ)) {
			stream.next();
			Expression right = parseRelationalExpression();

			switch (operation.getType()) {
			case T_DOUBLE_EQUALS:
				left = new EqualityExpression(EqualityOperation.EQ, left, right);
				break;
			case T_NOT_EQ:
				left = new EqualityExpression(EqualityOperation.NEQ, left, right);
				break;
			}
		}
		return left;
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parseRelationalExpression() {
		Expression left = parseAdditiveExpression();

		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_LESS_THAN) || operation.isOfType(Type.T_GREATER_THAN)
				|| operation.isOfType(Type.T_GREATER_EQ) || operation.isOfType(Type.T_LESS_EQ)) {
			stream.next();
			Expression right = parseAdditiveExpression();

			switch (operation.getType()) {
			case T_LESS_THAN:
				left = new RelationalExpression(RelationalOperation.LESS_THAN, left, right);
				break;
			case T_GREATER_THAN:
				left = new RelationalExpression(RelationalOperation.GREATER_THAN, left, right);
				break;
			case T_LESS_EQ:
				left = new RelationalExpression(RelationalOperation.LESS_THAN_EQ, left, right);
				break;
			case T_GREATER_EQ:
				left = new RelationalExpression(RelationalOperation.GREATER_THAN_EQ, left, right);
				break;
			}
		}
		return left;
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parseAdditiveExpression() {
		Expression left = parseMultiplicativeExpression();

		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_PLUS) || operation.isOfType(Type.T_MINUS)) {
			stream.next();
			Expression right = parseMultiplicativeExpression();

			switch (operation.getType()) {
			case T_PLUS:
				left = new ArithmeticBinExpression(ArithmeticBinOperation.PLUS, left, right);
				break;
			case T_MINUS:
				left = new ArithmeticBinExpression(ArithmeticBinOperation.MINUS, left, right);
				break;
			}
		}
		return left;
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parseMultiplicativeExpression() {
		Expression left = parseUnaryExpression();

		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_SLASH) || operation.isOfType(Type.T_STAR)) {
			stream.next();
			Expression right = parseUnaryExpression();

			switch (operation.getType()) {
			case T_SLASH:
				left = new ArithmeticBinExpression(ArithmeticBinOperation.DIV, left, right);
				break;
			case T_STAR:
				left = new ArithmeticBinExpression(ArithmeticBinOperation.MULT, left, right);
				break;
			}

		}
		return left;
	}

	private Expression parseUnaryExpression() {
		if (stream.peek().isOfType(Type.T_NOT)) {
			Position pos = stream.next().getPosition();
			return new LogicalNotExpression(parseUnaryExpression(), pos);
		}
		if (stream.peek().isOfType(Type.T_PLUS)) {

			stream.next();
			return parseUnaryExpression();
		}
		if (stream.peek().isOfType(Type.T_MINUS)) {
			Position pos = stream.next().getPosition();
			return new ArithmeticBinExpression(ArithmeticBinOperation.MULT, new IntegerLiteral(pos, -1),
					parseUnaryExpression());
		}

		return parsePostExpression();
	}

	@SuppressWarnings("incomplete-switch")
	private Expression parsePostExpression() {
		Expression left = parseLiteral();
		Token operation = null;
		while ((operation = stream.peek()).isOfType(Type.T_INCREMENT) || operation.isOfType(Type.T_DECREMENT)) {
			operation = stream.next();
			switch (operation.getType()) {
			case T_INCREMENT:
				left = new PostIncrementOperation(IncrementOperator.INCR, left);
				break;
			case T_DECREMENT:
				left = new PostIncrementOperation(IncrementOperator.DECR, left);
				break;
			}
		}
		return left;
	}

	private Expression parseLiteral() {
		Token current = stream.next();
		switch (current.getType()) {
		case T_INT_LITERAL:
			try {
				int i = Integer.parseInt(current.getValue());
				return new IntegerLiteral(current.getPosition(), i);
			} catch (NumberFormatException e) {
				ErrorHandler.raise(new Fatal(e.toString(), current));
			}
		case T_FLOAT_LITERAL:
			float f = Float.parseFloat(current.getValue());
			return new FloatLiteral(current.getPosition(), f);
		case T_BOOL_LITERAL:
			return new BoolLiteral(current.getPosition(), Boolean.parseBoolean(current.getValue()));
		case T_IDENTIFIER:
			if (stream.peek().isOfType(Type.T_L_PAREN)) {
				require(Type.T_L_PAREN);
				List<Expression> arguments = null;
				if (stream.peek().getValue() != ")")
					arguments = parseExpressionList();
				require(Type.T_R_PAREN);
				return new FuncCallExpression(new Identifier(current.getPosition(), current.getValue()), arguments);
			}
			return new VarLiteral(new Identifier(current.getPosition(), current.getValue()));
		case T_L_PAREN:
			Expression e = parseLogicalExpression();
			require(Type.T_R_PAREN);
			return e;
		default:
			ErrorHandler.raise(new Fatal(MessageTemplater.Expected, current.getValue()));
			return null;
		}
	}

	private Token require(Token.Type t) {
		Token next = stream.next();
		if (next.getType() != t) {
			ErrorHandler.raise(new Fatal(MessageTemplater.Unexpected, next, MessageTemplater.Expected + ' ' + t));
		}
		return next;
	}

	private List<Expression> parseExpressionList() {
		List<Expression> expressions = new ArrayList<>();
		expressions.add(parseLogicalExpression());
		while (stream.peek().isOfType(Type.T_COMMA)) {
			stream.next();
			expressions.add(parseLogicalExpression());
		}
		return expressions;
	}

}
