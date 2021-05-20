package models.expression.binary;

import models.expression.BinaryExpression;
import models.expression.Expression;

public abstract class LogicalExpression extends BinaryExpression {

	public LogicalExpression() {
		super(null, null);
	}

	public void setOprands(Expression leftOprand, Expression rightOprand) {
		this.leftOprand = leftOprand;
		this.rightOprand = rightOprand;
	}

}
