package models.expression.value.literal;

import models.util.Formatter;

public class NumberLiteral extends PrimitiveLiteral {
	
	protected double number;
	
	public NumberLiteral(double number) {
		this.number = number;
	}

	@Override
	protected String nodeName() {
		return "NumberLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("value", Double.toString(number));
	}
	
}
