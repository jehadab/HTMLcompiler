package models.nodes;

import models.AbstractASTNode;
import models.expression.Expression;
import models.util.Formatter;

import java.util.ArrayList;

public class MustachNode extends DocumentNode {

	protected AbstractASTNode expression;
	protected   String operand ="";
	protected  String method ="";
	protected String expressionAsString;
	protected ArrayList<String> parameter_value=new ArrayList<String>();
	public MustachNode(AbstractASTNode expression, String expressionAsString) {
		this.expression = expression;
		this.expressionAsString = expressionAsString;
	}

	public void setExpressionAsString(String expressionAsString) {
		this.expressionAsString = expressionAsString;
	}

	public ArrayList<String> getParameter_value() {
		return parameter_value;
	}

	public String getOperand() {
		return operand;
	}


	public void setOperand(String operand) {
		this.operand = operand;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public AbstractASTNode getExpression() {
		return expression;
	}

	public MustachNode() {
		
	}

	@Override
	protected String nodeName() {
		return "MustachNode";
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.object(expression.toString("Exp"));
	}

	public String getExpressionAsString() {
		return expressionAsString;
	}

	public Expression getExpressionAsExpression() {
		return (Expression) expression;
	}
}
