package models.statements;

import models.expression.ValueExpression;
import models.util.Formatter;

public class ObjectLoopStatement extends LoopStatement {

	protected String keyVariable;
	protected String valueVariable;
	public ObjectLoopStatement(String keyVariable, String valueVariable, ValueExpression objectToLoopOn) {
		super(objectToLoopOn);
		this.keyVariable = keyVariable;
		this.valueVariable = valueVariable;
	}

	@Override
	protected String nodeName() {
		return "ObjectLoopStatement";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.addProperty("keyVariable", keyVariable)
			.addProperty("valueVariable", valueVariable);
		formatter.object(objectToLoopOn.toString("loopOn"));
		return formatter;
	}

}
