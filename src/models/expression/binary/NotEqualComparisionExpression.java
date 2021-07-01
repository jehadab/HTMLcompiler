package models.expression.binary;

public class NotEqualComparisionExpression extends ComparsionExpression {
	
	@Override
	protected String nodeName() {
		return "NotEqualComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + "!=" + rightOprand.getExpressionForJS();
	}
}
