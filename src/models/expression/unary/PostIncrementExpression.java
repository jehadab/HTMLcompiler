package models.expression.unary;

import models.expression.Expression;
import models.expression.UnaryExpression;

public class PostIncrementExpression extends UnaryExpression {

	public PostIncrementExpression(Expression oprand) {
		super(oprand);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected String nodeName() {
		return "PostIncrementExpression";
	}
	
}
