package models;

import models.util.Formatter;

public abstract class AbstractASTNode implements ASTNodeInterface {

	abstract protected String nodeName();
	
	abstract protected Formatter nodeValue(Formatter formatter);
	
	public String format(Formatter formatter) {
		nodeValue(formatter);
		return formatter.format();
	}
	
	public String toString(String prefix) {
		Formatter formatter = new Formatter().name(nodeName());
		formatter.prefix(prefix);
		return format(formatter);
	}
	
	@Override
	public String toString() {
		Formatter formatter = new Formatter().name(nodeName());
		return format(formatter);
	}
}
