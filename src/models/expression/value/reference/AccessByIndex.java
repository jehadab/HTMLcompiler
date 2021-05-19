package models.expression.value.reference;

import models.AbstractASTNode;
import models.expression.Expression;
import models.expression.ValueExpression;
import models.util.Formatter;

public class AccessByIndex extends ValueExpression implements AccessMethod {

	protected Expression index;
	protected AccessMethod method;

	public AccessByIndex(Expression index) {
		this.index = index;
	}

	public AccessByIndex(Expression index, AccessMethod method) {
		this(index);
		this.method = method;
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.object(index.toString("index"));
		if (method != null)
			formatter.object(((AbstractASTNode) method).toString("method"));
		return formatter;
	}

	@Override
	protected String nodeName() {
		return "AccessByIndex";
	}
}
