package models.nodes;

import models.util.Formatter;

public class AttributeNode extends DocumentNode {
	protected String attribute;
	protected String value;
	
	protected AttributeNode(String attribute) {
		this.attribute = attribute;
	}
	
	public AttributeNode(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	@Override
	protected String nodeName() {
		return "AttributeNode";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("attribute", attribute)
				.addProperty("value", value);
	}
		
}
