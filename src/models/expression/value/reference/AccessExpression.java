package models.expression.value.reference;

import models.AbstractASTNode;
import models.expression.Expression;
import models.expression.ValueExpression;
import models.util.Formatter;

public class AccessExpression extends ValueExpression {

	protected Expression object;
	protected AccessMethod method;
	
	public AccessExpression(Expression object, AccessMethod method) {
		this.object = object;
		this.method = method;
	}
	
	@Override
	protected String nodeName() {
		return "AccessExpression";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.object(object.toString("object"))
				.object(((AbstractASTNode) method).toString("method"));
	}
}
