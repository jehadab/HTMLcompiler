package models.nodes;

import models.enums.ElementType;
import models.util.Formatter;

public class ElementNode extends DocumentNode {
	protected String name;
	protected ElementType type;
	protected DocumentNode[] attributes;
	protected DocumentNode[] contents;
	
	public ElementNode(String name, DocumentNode[] attributes) {
		this.name = name;
		this.attributes = attributes;
		this.type = ElementType.SelfClosed;
	}
	
	public ElementNode(String name, DocumentNode[] attributes, DocumentNode[] contents) {
		this(name, attributes);
		this.contents = contents;
		this.type = ElementType.Normal;
	}

	@Override
	protected String nodeName() {
		return "ElementNode";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.addProperty("type", type.toString())
		.addProperty("name", name)
		.array("attributes", attributes);
		if (contents != null)
			formatter.array("children", contents);
		return formatter;
	}

}
