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

	@Override
	public String getExpressionForJS() {
		String res = "[";
		for (int i=0;i<elements.length;i++) {
			ValueExpression val = elements[i];
			res += val.getExpressionForJS();
			if(i != elements.length - 1)
			{
				res += ",";
			}
		}
		res += "]";
		return res;
	}
}
