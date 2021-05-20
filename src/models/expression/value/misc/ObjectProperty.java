package models.expression.value.misc;

import models.expression.ValueExpression;

public class ObjectProperty {

	protected String key;
	protected ValueExpression value;

	public ObjectProperty(String key, ValueExpression value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value.toString("Value");
	}
	
	@Override
	public String toString() {
		return "key: " + key + " " + value.toString("value");
	}
	
}
