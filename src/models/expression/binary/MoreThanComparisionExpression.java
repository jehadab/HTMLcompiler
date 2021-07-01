package models.expression.binary;

public class MoreThanComparisionExpression extends ComparsionExpression {

	@Override
	protected String nodeName() {
		return "MoreThanComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + ">" + rightOprand.getExpressionForJS();
	}
}
