package models.expression.unary;

import models.expression.Expression;
import models.expression.UnaryExpression;

public class PreIncrementExpression extends UnaryExpression {

	public PreIncrementExpression(Expression oprand) {
		super(oprand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String nodeName() {
		return "PreIncrementExpression";
	}
	
}
