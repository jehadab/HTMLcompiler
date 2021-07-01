package models.expression.parenthesized;

import models.expression.Expression;
import models.expression.UnaryExpression;
import models.util.Formatter;

public class ParenthesizedExpression extends Expression {

    protected Expression oprand;
    public ParenthesizedExpression(Expression oprand) {
        this.oprand = oprand;
    }

    @Override
    protected String nodeName() {
        return "PostDecrementExpression";
    }

    @Override
    protected Formatter nodeValue(Formatter formatter) {
        return null;
    }

    @Override
    public String getExpressionForJS() {
        return "(" + oprand.getExpressionForJS() + ")";
    }
}
