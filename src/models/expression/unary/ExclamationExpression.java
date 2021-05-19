package models.expression.unary;

import models.expression.Expression;
import models.expression.UnaryExpression;

public class ExclamationExpression extends UnaryExpression {

	public ExclamationExpression(Expression oprand) {
		super(oprand);
	}

	@Override
	protected String nodeName() {
		return "ExclamationExpression";
	}

}
