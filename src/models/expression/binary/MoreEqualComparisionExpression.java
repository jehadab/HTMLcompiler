package models.expression.binary;

public class MoreEqualComparisionExpression extends ComparsionExpression {

	@Override
	protected String nodeName() {
		return "MoreEqualComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + ">=" + rightOprand.getExpressionForJS();
	}
}
