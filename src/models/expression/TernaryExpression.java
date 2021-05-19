package models.expression;

import models.util.Formatter;

public abstract class TernaryExpression extends Expression {
	protected Expression firstOprand;
	protected Expression secondOprand;
	protected Expression thirdOprand;
	
	public TernaryExpression(Expression firstOprand, Expression secondOprand, Expression thirdOprand) {
		this.firstOprand = firstOprand;
		this.secondOprand = secondOprand;
		this.thirdOprand = thirdOprand;
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.object(firstOprand.toString("firstOprand"))
			.object(secondOprand.toString("secondOprand"))
			.object(thirdOprand.toString("thirdOprand"));
		return formatter;
	}
	
}
