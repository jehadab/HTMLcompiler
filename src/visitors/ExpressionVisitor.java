package visitors;

import java.util.ArrayList;
import java.util.List;

import misc.HTMLParser;
import misc.HTMLParser.AccessContext;
import misc.HTMLParser.AdditionExpressionContext;
import misc.HTMLParser.AndExpressionContext;
import misc.HTMLParser.BooleanLiteralContext;
import misc.HTMLParser.ComparisonExpressionContext;
import misc.HTMLParser.DividExpressionContext;
import misc.HTMLParser.EqualComparisionExpressionContext;
import misc.HTMLParser.ExclaminationExpressionContext;
import misc.HTMLParser.ExpContext;
import misc.HTMLParser.FilterExpressionContext;
import misc.HTMLParser.FunctionCallContext;
import misc.HTMLParser.IndexAccessContext;
import misc.HTMLParser.LessEqualComparisionExpressionContext;
import misc.HTMLParser.LessThanComparisionExpressionContext;
import misc.HTMLParser.LiteralArrayContext;
import misc.HTMLParser.LiteralContext;
import misc.HTMLParser.LiteralObjectContext;
import misc.HTMLParser.LogicalExpressionContext;
import misc.HTMLParser.MinusExpressionContext;
import misc.HTMLParser.ModExpressionContext;
import misc.HTMLParser.MoreEqualComparisionExpressionContext;
import misc.HTMLParser.MoreThanComparisionExpressionContext;
import misc.HTMLParser.MultiExpressionContext;
import misc.HTMLParser.NotEqualComparisionExpressionContext;
import misc.HTMLParser.NumberLiteralContext;
import misc.HTMLParser.ObjectPropertyContext;
import misc.HTMLParser.OrExpressionContext;
import misc.HTMLParser.ParameterContext;
import misc.HTMLParser.PostDecrementExpressionContext;
import misc.HTMLParser.PostIncrementExpressionContext;
import misc.HTMLParser.PreDecrementExpressionContext;
import misc.HTMLParser.PreIncrementExpressionContext;
import misc.HTMLParser.PropertyAccessContext;
import misc.HTMLParser.ReferenceExpressionContext;
import misc.HTMLParser.TernaryConditionalExpressionContext;
import models.expression.Expression;
import models.expression.ValueExpression;
import models.expression.binary.AdditionExpression;
import models.expression.binary.AndExpression;
import models.expression.binary.ComparsionExpression;
import models.expression.binary.DividExpression;
import models.expression.binary.EqualComparisionExpression;
import models.expression.binary.LessEqualComparisionExpression;
import models.expression.binary.LessThanComparisionExpression;
import models.expression.binary.LogicalExpression;
import models.expression.binary.MinusExpression;
import models.expression.binary.ModExpression;
import models.expression.binary.MoreEqualComparisionExpression;
import models.expression.binary.MoreThanComparisionExpression;
import models.expression.binary.MultiExpression;
import models.expression.binary.NotEqualComparisionExpression;
import models.expression.binary.OrExpression;
import models.expression.ternary.TernaryConditionalExpression;
import models.expression.unary.ExclamationExpression;
import models.expression.unary.PostDecrementExpression;
import models.expression.unary.PostIncrementExpression;
import models.expression.unary.PreDecrementExpression;
import models.expression.unary.PreIncrementExpression;
import models.expression.value.FunctionCallExpression;
import models.expression.value.literal.ArrayLiteral;
import models.expression.value.literal.BooleanLiteral;
import models.expression.value.literal.NumberLiteral;
import models.expression.value.literal.ObjectLiteral;
import models.expression.value.literal.StringLiteral;
import models.expression.value.misc.ObjectProperty;
import models.expression.value.reference.AccessByIndex;
import models.expression.value.reference.AccessByProperty;
import models.expression.value.reference.AccessExpression;
import models.expression.value.reference.AccessMethod;
import models.expression.value.reference.ReferenceExpression;

public class ExpressionVisitor extends Visitor<Expression>{

	@Override
	public Expression visitFunctionCall(FunctionCallContext ctx) {
		if (ctx.getChildCount() == 3)
		 return new FunctionCallExpression(visit(ctx.getChild(0)));
		
		List<Expression> parameters = new ArrayList<Expression>();
		for (int index = 0; index < ctx.getChild(2).getChildCount(); index += 2 ) {
			parameters.add(visit(ctx.getChild(2).getChild(index)));
		}
		return new FunctionCallExpression(visit(ctx.getChild(0)), parameters); 
	}

	@Override
	public Expression visitParameter(ParameterContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public Expression visitAdditionExpression(AdditionExpressionContext ctx) {
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		return new AdditionExpression(leftOprand, rightOprand);
	}

	@Override
	public Expression visitComparisonExpression(ComparisonExpressionContext ctx) {
		ComparsionExpression cx = (ComparsionExpression) visit(ctx.getChild(1));
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		cx.setOprands(leftOprand, rightOprand);
		return cx;
	}

	@Override
	public Expression visitDividExpression(DividExpressionContext ctx) {
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		return new DividExpression(leftOprand, rightOprand);
	}

	@Override
	public Expression visitPostIncrementExpression(PostIncrementExpressionContext ctx) {
		Expression oprand = visit(ctx.getChild(0));
		return new PostIncrementExpression(oprand);
	}

	@Override
	public Expression visitPostDecrementExpression(PostDecrementExpressionContext ctx) {
		Expression oprand = visit(ctx.getChild(0));
		return new PostDecrementExpression(oprand);
	}
	
	@Override
	public Expression visitPreDecrementExpression(PreDecrementExpressionContext ctx) {
		Expression oprand = visit(ctx.getChild(1));
		return new PreDecrementExpression(oprand);
	}

	@Override
	public Expression visitPreIncrementExpression(PreIncrementExpressionContext ctx) {
		Expression oprand = visit(ctx.getChild(1));
		return new PreIncrementExpression(oprand);
	}

	@Override
	public Expression visitModExpression(ModExpressionContext ctx) {
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		return new ModExpression(leftOprand, rightOprand);
	}

	@Override
	public Expression visitMultiExpression(MultiExpressionContext ctx) {
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		return new MultiExpression(leftOprand, rightOprand);
	}

	@Override
	public Expression visitLogicalExpression(LogicalExpressionContext ctx) {
		LogicalExpression cx = (LogicalExpression) visit(ctx.getChild(1));
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		cx.setOprands(leftOprand, rightOprand);
		return cx;
	}

	@Override
	public Expression visitAndExpression(AndExpressionContext ctx) {
		return new AndExpression();
	}

	@Override
	public Expression visitOrExpression(OrExpressionContext ctx) {
		return new OrExpression();
	}

	@Override
	public Expression visitMinusExpression(MinusExpressionContext ctx) {
		Expression leftOprand = visit(ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		return new MinusExpression(leftOprand, rightOprand);
	}

	@Override
	public Expression visitTernaryConditionalExpression(TernaryConditionalExpressionContext ctx) {
		Expression firstOprand = visit(ctx.getChild(0));
		Expression secondOprand = visit(ctx.getChild(2));
		Expression thirdOprand = visit(ctx.getChild(4));
		return new TernaryConditionalExpression(firstOprand, secondOprand, thirdOprand);
	}

	@Override
	public Expression visitExclaminationExpression(ExclaminationExpressionContext ctx) {
		Expression oprand = visit(ctx.getChild(1));
		return new ExclamationExpression(oprand);
	}

	@Override
	public Expression visitExp(ExpContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public Expression visitFilterExpression(FilterExpressionContext ctx) {
		return (Expression) documentVisitor.visit(ctx);
	}

	@Override
	public ValueExpression visitBooleanLiteral(BooleanLiteralContext ctx) {
		String booleanText = ctx.getChild(0).getText();
		boolean bool = Boolean.parseBoolean(booleanText);
		return new BooleanLiteral(bool);
	}

	@Override
	public Expression visitCompound(HTMLParser.CompoundContext ctx) {
		return visit(ctx.getChild(0)); 
	}

	@Override
	public Expression visitLiteral(LiteralContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public Expression visitLiteralArray(LiteralArrayContext ctx) {
		int childrenCount = ctx.getChildCount();
		List<Expression> values = new ArrayList<Expression>();
		int index = 1;
		while (index < childrenCount - 1) {
			values.add(visit(ctx.getChild(index)));
			index += 2;
		}
		return new ArrayLiteral(values.toArray(new ValueExpression[values.size()]));
	}

	@Override
	public Expression visitLiteralObject(LiteralObjectContext ctx) {
		int childrenCount = ctx.getChildCount();
		List<ObjectProperty> properties = new ArrayList<ObjectProperty>();
		int index = 1;
		while (index < childrenCount - 1) {
			properties.add(analyze((ObjectPropertyContext) ctx.getChild(index)));
			index += 2;
		}
		return new ObjectLiteral(properties.toArray(new ObjectProperty[properties.size()]));
	}

	@Override
	public Expression visitNumberLiteral(NumberLiteralContext ctx) {
		String numberText = ctx.getChild(0).getText();
		int number = Integer.parseInt(numberText);
		return new NumberLiteral(number);
	}

	@Override
	public Expression visitReferenceExpression(ReferenceExpressionContext ctx) {
		return new ReferenceExpression(ctx.getChild(0).getText());
	}

	@Override
	public Expression visitAccess(AccessContext ctx) {
		Expression object = visit(ctx.getChild(0));
		AccessMethod method = (AccessMethod) visit(ctx.getChild(1));
		return new AccessExpression(object, method);
	}

	@Override
	public Expression visitIndexAccess(IndexAccessContext ctx) {
		Expression index = visit(ctx.getChild(1));
		if (ctx.getChildCount() == 3)
			return new AccessByIndex(index);
		AccessMethod method = (AccessMethod) visit(ctx.getChild(3));
		return new AccessByIndex(index, method);
	}

	@Override
	public Expression visitPropertyAccess(PropertyAccessContext ctx) {
		String property = ctx.getChild(1).getText();
		if (ctx.getChildCount() == 2)
			return new AccessByProperty(property);
		AccessMethod method = (AccessMethod) visit(ctx.getChild(2));
		return new AccessByProperty(property, method);
	}

	@Override 
	public Expression visitStringLiteral(HTMLParser.StringLiteralContext ctx) {
		String string = ctx.getChild(1).getText();
		return new StringLiteral(string);
	}

	@Override
	public Expression visitParenthesized(HTMLParser.ParenthesizedContext ctx) {
		return visit(ctx.getChild(1));
	}
	
	protected ObjectProperty analyze(ObjectPropertyContext ctx) {
		String name = ctx.getChild(0).getText();
		Expression expression = visit(ctx.getChild(2));
		return new ObjectProperty(name, (ValueExpression) expression);
	}

	@Override
	public Expression visitMoreThanComparisionExpression(MoreThanComparisionExpressionContext ctx) {
		return new MoreThanComparisionExpression();
	}

	@Override
	public Expression visitLessThanComparisionExpression(LessThanComparisionExpressionContext ctx) {
		return new LessThanComparisionExpression();
	}

	@Override
	public Expression visitMoreEqualComparisionExpression(MoreEqualComparisionExpressionContext ctx) {
		return new MoreEqualComparisionExpression();
	}

	@Override
	public Expression visitLessEqualComparisionExpression(LessEqualComparisionExpressionContext ctx) {
		return new LessEqualComparisionExpression();
	}

	@Override
	public Expression visitEqualComparisionExpression(EqualComparisionExpressionContext ctx) {
		return new EqualComparisionExpression();
	}

	@Override
	public Expression visitNotEqualComparisionExpression(NotEqualComparisionExpressionContext ctx) {
		return new NotEqualComparisionExpression();
	}
}
