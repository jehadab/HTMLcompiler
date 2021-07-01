package models.expression.value.literal;

import models.expression.value.misc.ObjectProperty;
import models.util.Formatter;

public class StringLiteral extends PrimitiveLiteral {

	protected String string;
	
	public StringLiteral(String string) {
		this.string = string;
	}

	public String getString() {
		return string;
	}

	@Override
	protected String nodeName() {
		return "StringLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("value", string);
	}

	public String getExpressionForJS() {
		return '"' + string + '"';
	}
}
