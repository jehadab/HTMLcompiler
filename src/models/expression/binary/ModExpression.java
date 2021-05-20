package models.expression.binary;

import models.expression.Expression;

public class ModExpression extends ArithmeticExpression {

	public ModExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

	@Override
	protected String nodeName() {
		return "ModExpression";
	}
	
}
