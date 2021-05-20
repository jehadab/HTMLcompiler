package models.expression.value.reference;

import models.expression.ValueExpression;
import models.util.Formatter;

public class ReferenceExpression extends ValueExpression {

	protected String name;
	
	public ReferenceExpression(String name) {
		this.name = name;
	}

	@Override
	protected String nodeName() {
		return "ReferenceExpression";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("reference", name);
	}
	
}
