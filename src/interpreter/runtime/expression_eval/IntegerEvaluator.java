package interpreter.runtime.expression_eval;

import interpreter.Symbol;
import interpreter.ast_visitor.CommonTypedVisitor;
import interpreter.runtime.RuntimeEnvironment;
import language_elements.expression.ArithmeticBinExpression;
import language_elements.expression.IntegerLiteral;
import language_elements.expression.VarLiteral;

public class IntegerEvaluator extends CommonTypedVisitor<Integer, Object> {

    private RuntimeEnvironment runtime;

    public IntegerEvaluator(RuntimeEnvironment runtime) {
        this.runtime = runtime;
    }

    @Override
    public Integer visit(ArithmeticBinExpression e, Object arg) {
        Integer l = e.getLeft().accept(this, arg);
        Integer r = e.getRight().accept(this, arg);

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
    public Integer visit(IntegerLiteral i, Object o) {
        return i.getValue();
    }

    @Override
    public Integer visit(VarLiteral v, Object argument) {
        Symbol variable = runtime.accessLocalStorage().get(v.getId().getVal());
        return (Integer) variable.getValue();

    }
}