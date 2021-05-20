package models.directive;

import models.AbstractASTNode;
import models.nodes.DocumentNode;
import models.util.Formatter;

public class Directive extends DocumentNode {

	protected String type;
	protected AbstractASTNode value;
	
	public Directive(String type) {
		this.type = type;
	}

	
	public Directive(String type, AbstractASTNode value) {
		this.type = type;
		this.value = value;
	}

	@Override
	protected String nodeName() {
		return "Directive";
	}
	
	@Override
	protected Formatter nodeValue(Formatter formatter) {
		formatter.addProperty("type", type);
		if(value != null)
			formatter.object(value.toString("value"));
		return formatter;
	}


	public String getName() {
		return type;
	}

}
