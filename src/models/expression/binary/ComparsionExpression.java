package models.expression.binary;

import models.expression.BinaryExpression;
import models.expression.Expression;

public abstract class ComparsionExpression extends BinaryExpression {

	public ComparsionExpression() {
		super(null, null);
	}
	
	public void setOprands(Expression leftOprand, Expression rightOprand) {
		this.leftOprand = leftOprand;
		this.rightOprand = rightOprand;
	}
	
}
