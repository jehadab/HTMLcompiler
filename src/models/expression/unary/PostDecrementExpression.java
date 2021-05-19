package models.expression.unary;

import models.expression.Expression;
import models.expression.UnaryExpression;

public class PostDecrementExpression extends UnaryExpression {

	public PostDecrementExpression(Expression oprand) {
		super(oprand);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String nodeName() {
		return "PostDecrementExpression";
	}
	
}
