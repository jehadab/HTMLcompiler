package models.expression.value.literal;

import models.expression.ValueExpression;
import models.util.Formatter;

public class ArrayLiteral extends CompoundLiteral {

	protected ValueExpression[] elements;
	
	public ArrayLiteral(ValueExpression[] elements) {
		this.elements = elements;
	}
	
	@Override
	protected String nodeName() {
		return "ArrayLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.array("elements", elements);
	}
	
}
