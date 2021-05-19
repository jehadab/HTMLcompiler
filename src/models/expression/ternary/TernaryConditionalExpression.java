package models.expression.ternary;

import models.expression.Expression;
import models.expression.TernaryExpression;

public class TernaryConditionalExpression extends TernaryExpression {

	public TernaryConditionalExpression(Expression firstOprand, Expression secondOprand, Expression thirdOprand) {
		super(firstOprand, secondOprand, thirdOprand);
	}

	@Override
	protected String nodeName() {
		return "TernaryConditionalExpression";
	}

}
