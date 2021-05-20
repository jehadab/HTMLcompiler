package models.documents;

import models.AbstractASTNode;
import models.util.Formatter;

public class Document extends AbstractASTNode {
	protected DocumentHeader header;
	protected DocumentBody body;

	public Document(DocumentHeader header, DocumentBody body) {
		this.header = header;
		this.body = body;
	}
	
	public Document(DocumentBody body) {
		this.header = new DocumentHeader();
		this.body = body;
	}

	@Override
	protected String nodeName() {
		return "Document";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.object(header.toString("header"))
				.object(body.toString("body"));
	}

}
