package models.expression.binary;

public class LessEqualComparisionExpression extends ComparsionExpression {

	@Override
	protected String nodeName() {
		return "LessEqualComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + "<=" + rightOprand.getExpressionForJS();
	}
}
