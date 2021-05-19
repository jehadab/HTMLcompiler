package models.expression.unary;

import models.expression.Expression;
import models.expression.UnaryExpression;

public class PreDecrementExpression extends UnaryExpression {

	public PreDecrementExpression(Expression oprand) {
		super(oprand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String nodeName() {
		return "PreDecrementExpression";
	}
	
}
