package models.expression.value.literal;

import models.util.Formatter;

public class BooleanLiteral extends PrimitiveLiteral {

	protected boolean value;
	
	public BooleanLiteral(boolean value) {
		this.value = value;
	}
	
	@Override
	protected String nodeName() {
		return "BooleanLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("value", Boolean.toString(value));
	}
	
}
