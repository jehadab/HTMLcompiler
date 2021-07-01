package models.expression.binary;

public class EqualComparisionExpression extends ComparsionExpression {

	@Override
	protected String nodeName() {
		return "EqualComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + "==" + rightOprand.getExpressionForJS();
	}
}
