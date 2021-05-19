package models.statements;

import java.util.List;

import models.AbstractASTNode;
import models.expression.Expression;
import models.util.Formatter;

public class FilterStatement extends Expression {
	protected AbstractASTNode oprand;
	protected Expression filter; 
	protected List<Expression> parameters;
	
	public FilterStatement(Expression filter) {
		this.filter = filter;
	}
	
	public FilterStatement(Expression filter, List<Expression> parameters) {
		this(filter);
		this.parameters = parameters;
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

}
