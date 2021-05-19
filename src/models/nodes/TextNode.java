package models.nodes;

import models.util.Formatter;

public class TextNode extends DocumentNode {
	
	protected String text;
	
	public TextNode(String text) {
		this.text = text;
	}
	
	@Override
	protected String nodeName() {
		return "TextNode";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("textValue", text);
	}

}
