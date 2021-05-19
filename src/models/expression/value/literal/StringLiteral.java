package models.expression.value.literal;

import models.util.Formatter;

public class StringLiteral extends PrimitiveLiteral {

	protected String string;
	
	public StringLiteral(String string) {
		this.string = string;
	}

	@Override
	protected String nodeName() {
		return "StringLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("value", string);
	}

}
