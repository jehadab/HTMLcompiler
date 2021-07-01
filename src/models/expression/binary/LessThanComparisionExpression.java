package models.expression.binary;

public class LessThanComparisionExpression extends ComparsionExpression {
	
	@Override
	protected String nodeName() {
		return "LessThanComparisionExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + "<" + rightOprand.getExpressionForJS();
	}
}
