package models.expression.binary;

import models.expression.Expression;

public class DividExpression extends ArithmeticExpression {

	public DividExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

	@Override
	protected String nodeName() {
		return "DividExpression";
	}

}
