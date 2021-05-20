package models.nodes;

import models.util.Formatter;

public class StyleNode extends DocumentNode {
	
	protected String styleElement;
	
	public StyleNode(String styleElement) {
		this.styleElement = styleElement;
	}
	
	@Override
	protected String nodeName() {
		return "StyleNode";
	}


	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("content", styleElement);
	}

}
