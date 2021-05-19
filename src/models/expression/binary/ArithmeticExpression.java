package models.expression.binary;

import models.expression.BinaryExpression;
import models.expression.Expression;

public abstract class ArithmeticExpression extends BinaryExpression {

	public ArithmeticExpression(Expression leftOprand, Expression rightOprand) {
		super(leftOprand, rightOprand);
	}

}
