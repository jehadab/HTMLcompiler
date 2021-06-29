package models.nodes;

import models.AbstractASTNode;
import models.util.Formatter;

public class MustachNode extends DocumentNode {

	protected AbstractASTNode expression;

	protected String expressionAsString;
	public MustachNode(AbstractASTNode expression, String expressionAsString) {
		this.expression = expression;
		this.expressionAsString = expressionAsString;
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
