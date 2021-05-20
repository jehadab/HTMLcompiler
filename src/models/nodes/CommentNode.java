package models.nodes;

import models.enums.CommentType;
import models.util.Formatter;

public class CommentNode extends DocumentNode {

	protected String comment;
	protected CommentType type;
	
	public CommentNode(String comment, CommentType type) {
		this.comment = comment;
		this.type = type;
	}

	@Override
	protected String nodeName() {
		return "CommentNode";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("type", type.toString())
				.addProperty("comment", comment);
	}

}
