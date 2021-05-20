package models.expression.value.reference;

import models.AbstractASTNode;
import models.expression.ValueExpression;
import models.util.Formatter;

public class AccessByProperty extends ValueExpression implements AccessMethod {

	protected String name;
	protected AccessMethod method;

	public AccessByProperty(String name) {
		this.name = name;
	}

	public AccessByProperty(String name, AccessMethod method) {
		this(name);
		this.method = method;
	}

	@Override
	protected String nodeName() {
		return "AccessByProperty";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		 formatter.addProperty("property", name);
		 if (method != null)
			 formatter.object(((AbstractASTNode) method).toString("method"));
		 return formatter;
	}

}
