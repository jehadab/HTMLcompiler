package models.expression.binary;

import models.expression.Expression;

public class MultiExpression extends ArithmeticExpression {

	public MultiExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

	@Override
	protected String nodeName() {
		return "MultiExpression";
	}
}
