package models.expression.value.literal;

import models.expression.ValueExpression;
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

	@Override
	public String getExpressionForJS() {
		String res = "{";
		for (int i=0;i<properties.length;i++) {
			ObjectProperty val = properties[i];
			res += val.getExpressionForJS();
			if(i != properties.length - 1)
			{
				res += ",";
			}
		}
		res += "}";
		return res;
	}
}
