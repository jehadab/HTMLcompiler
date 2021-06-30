package models.nodes;

import models.AbstractASTNode;
import models.util.Formatter;

public class MustachNode extends DocumentNode {

	protected AbstractASTNode expression;
	protected   String operand ;
	protected  String method ;
	protected String expressionAsString;
	public MustachNode(AbstractASTNode expression, String expressionAsString) {
		this.expression = expression;
		this.expressionAsString = expressionAsString;
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
}
