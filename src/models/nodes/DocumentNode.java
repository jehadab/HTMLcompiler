package models.nodes;

import models.AbstractASTNode;
import models.util.Formatter;

public abstract class DocumentNode extends AbstractASTNode {

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter;
	}
	
}
