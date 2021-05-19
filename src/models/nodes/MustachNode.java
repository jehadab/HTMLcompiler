package models.nodes;

import models.AbstractASTNode;
import models.util.Formatter;

public class MustachNode extends DocumentNode {

	protected AbstractASTNode expression;
	
	public MustachNode(AbstractASTNode expression) {
		this.expression = expression;
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

}
