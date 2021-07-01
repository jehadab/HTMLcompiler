package models.expression.value.reference;

import CodeGeneration.codegeneration;
import models.expression.ValueExpression;
import models.util.Formatter;
import visitors.Visitor;

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

	public String getName() {
		return name;
	}

	@Override
	public String getExpressionForJS() {
		return codegeneration.cpapp_value + '.' + name;
	}
}
