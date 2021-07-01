package models.expression.binary;

public class AndExpression extends LogicalExpression {

	@Override
	protected String nodeName() {
		return "AndExpression";
	}

	@Override
	public String getExpressionForJS() {
		return leftOprand.getExpressionForJS() + "&&" + rightOprand.getExpressionForJS();
	}
}
