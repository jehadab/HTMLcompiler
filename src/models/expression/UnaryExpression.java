package models.expression;

import models.util.Formatter;

public abstract class UnaryExpression extends Expression {

	protected Expression oprand;
	
	public UnaryExpression(Expression oprand) {
		this.oprand = oprand;
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.object(oprand.toString("oprand"));
	}
	
}
