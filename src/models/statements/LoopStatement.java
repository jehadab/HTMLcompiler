package models.statements;

import models.expression.ValueExpression;

public abstract class LoopStatement extends Statement {

	protected ValueExpression objectToLoopOn;
	
	protected LoopStatement(ValueExpression objectToLoopOn) {
		this.objectToLoopOn = objectToLoopOn;
	}
}
