package models.expression;

import models.util.Formatter;

public abstract class BinaryExpression extends Expression {

	protected Expression leftOprand;
	protected Expression rightOprand;
	
	public BinaryExpression(Expression leftOprand, Expression rightOprand) {
		this.leftOprand = leftOprand;
		this.rightOprand = rightOprand;
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.object(leftOprand.toString("leftOprand"))
			.object(rightOprand.toString("rightOprand"));
		return formatter;
	}
	
}
