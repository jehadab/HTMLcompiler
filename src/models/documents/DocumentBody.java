package models.documents;

import java.util.List;

import models.AbstractASTNode;
import models.nodes.DocumentNode;
import models.util.Formatter;

public class DocumentBody extends AbstractASTNode {
	protected List<DocumentNode> nodes;
	
	public DocumentBody(List<DocumentNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	protected String nodeName() {
		return "DocumentBody";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		for (DocumentNode node : nodes) {
			formatter.object(node.toString());
		}
		return formatter;
	}
	
}
