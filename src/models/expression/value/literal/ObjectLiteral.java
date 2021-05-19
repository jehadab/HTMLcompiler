package models.expression.value.literal;

import models.expression.value.misc.ObjectProperty;
import models.util.Formatter;

public class ObjectLiteral extends CompoundLiteral {

	protected ObjectProperty[] properties;
	
	public ObjectLiteral(ObjectProperty[] properties) {
		this.properties = properties;
	}

	@Override
	protected String nodeName() {
		return "ObjectLiteral";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		if (properties != null)
			formatter.array("Properties", properties);
		return formatter;
	}
	
}
