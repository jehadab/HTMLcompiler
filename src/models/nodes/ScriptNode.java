package models.nodes;

import models.util.Formatter;

public class ScriptNode extends DocumentNode {
	
	protected String scriptElement;
	
	public ScriptNode(String scriptElement) {
		this.scriptElement = scriptElement;
	}
	
	@Override
	protected String nodeName() {
		return "ScriptNode";
	}


	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("content", scriptElement);
	}

}
