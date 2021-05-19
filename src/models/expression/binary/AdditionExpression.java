package models.expression.binary;

import models.expression.Expression;

public class AdditionExpression extends ArithmeticExpression {

	public AdditionExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

	@Override
	protected String nodeName() {
		return "AdditionExpression";
	}

}
