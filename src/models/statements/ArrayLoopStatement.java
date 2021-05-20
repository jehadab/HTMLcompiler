package models.statements;

import models.expression.ValueExpression;
import models.util.Formatter;

public class ArrayLoopStatement extends LoopStatement {

	protected String loopVariable;
	protected String indexVariable;
	
	public ArrayLoopStatement(String loopVariable, ValueExpression objectToLoop) {
		super(objectToLoop);
		this.loopVariable = loopVariable;
	}

	public ArrayLoopStatement(String loopVariable, String indexVariable, ValueExpression objectToLoop) {
		this(loopVariable, objectToLoop);
		this.indexVariable = indexVariable; 
	}
	
	@Override
	protected String nodeName() {
		return "ArrayLoopStatement";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.addProperty("loopVariable", loopVariable)
				.addProperty("indexed", Boolean.toString(indexVariable != null));
		if (indexVariable != null)
			formatter.addProperty("indexVariable", indexVariable);
		formatter.object(objectToLoopOn.toString("loopOn"));
		return formatter;
	}

}
