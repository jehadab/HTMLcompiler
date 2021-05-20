package models.expression.value;

import java.util.List;

import models.expression.Expression;
import models.util.Formatter;

public class FunctionCallExpression extends Expression {

	protected Expression function; 
	protected List<Expression> parameters; 
	public FunctionCallExpression(Expression function) {
		this.function = function;
	}
	
	public FunctionCallExpression(Expression function, List<Expression> parameters) {
		this.function = function;
		this.parameters = parameters;
	}

	@Override
	protected String nodeName() {
		return "FunctionCallExpression";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.object(function.toString("function"));
		if (parameters != null)
			formatter.array("parameters", parameters.toArray(new Expression[parameters.size()]));
		return formatter;
	}
}
