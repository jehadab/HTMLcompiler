package models.statements;

import java.util.List;

import models.AbstractASTNode;
import models.expression.Expression;
import models.util.Formatter;

public class FilterStatement extends Expression {
	protected AbstractASTNode oprand;
	protected Expression filter; 
	protected List<Expression> parameters;
	protected  String filter_method;
	protected  String operand;

    public List<Expression> getParameters() {
        return parameters;
    }

    public FilterStatement(Expression filter, String filter_method ) {
		this.filter = filter;
		this.filter_method=filter_method;
	}

	public String getOperand() {
		return operand;
	}

	public String getFilter_method() {
		return filter_method;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public FilterStatement(Expression filter, List<Expression> parameters, String method_name) {
		this.filter=filter;
		this.parameters = parameters;
		this.filter_method=method_name;
	}
	
	public void setOprand(Expression oprand) {
		this.oprand = oprand;
	}
	
	@Override
	protected String nodeName() {
		return "FilterStatement";
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.object(oprand.toString("Oprand"))
		.object(filter.toString("FilterMethod"));
		if (parameters != null)
			formatter.array("parameters", parameters.toArray(new Expression[parameters.size()]));
		return formatter;
	}

	@Override
	public String getExpressionForJS() {
		return null;
	}
}
