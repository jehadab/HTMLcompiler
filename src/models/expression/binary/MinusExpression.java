package models.expression.binary;

import models.expression.Expression;

public class MinusExpression extends ArithmeticExpression {

	public MinusExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

	@Override
	protected String nodeName() {
		return "MinusExpression";
	}
	
}
