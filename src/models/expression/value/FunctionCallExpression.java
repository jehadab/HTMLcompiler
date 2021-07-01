package models.expression.value;

import java.util.List;

import CodeGeneration.codegeneration;
import models.expression.Expression;
import models.expression.ValueExpression;
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

	@Override
	public String getExpressionForJS() {
		String res = function.getExpressionForJS();
		res += '(';
		if(parameters != null)
		{
			for (int i=0;i<parameters.size();i++) {
				Expression val = parameters.get(i);
				res += val.getExpressionForJS();
				if(i != parameters.size() - 1)
				{
					res += ",";
				}
			}
		}
		res += ")";
		return res;
	}
}
