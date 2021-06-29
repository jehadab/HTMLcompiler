package visitors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import CodeGeneration.codegeneration;

import SymboleTable.Scope;
import SymboleTable.Symbole;
import SymboleTable.SymboleTable;
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
import models.directive.Directive;
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
import models.nodes.MustachNode;
import org.antlr.runtime.tree.ParseTree;

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
		System.out.println(" we are in the visitModExpression  ");
		Expression leftOprand = visit(ctx.getChild(0));
		System.out.println(" value for the leftOprand  : "+ctx.getChild(0));
		Expression rightOprand = visit(ctx.getChild(2));
		System.out.println(" value for the rightOprand  :"+ctx.getChild(2));
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
//        System.out.println(" pritn the varaibel here   :"+ctx.getChild(0).getText() );
//		codegeneration code = new codegeneration();
//		code.cpapp_value=ctx.getChild(0).getText();
        get_Directive_value((ctx.getChild(0).getText()));
        if(Element.equals("Mustach") && pipElement== true)
        {
            if(ctx.getChild(0).getText().equals("currency"))
            {
                filter_method="currency";
            }
            if(ctx.getChild(0).getText().equals("upper"))
            {
                filter_method="upper";
            } if(ctx.getChild(0).getText().equals("lower"))
			{
				filter_method="lower";
			}
        }
        if(Element.equals("Mustach")) {

            Symbole symbole = new Symbole(ctx.getChild(0).getText());
            Scope SymboleScope = new Scope();
            SymboleScope = DocumentVisitor.scopesStack.peek();
            symbole.setSymbole_scope(SymboleScope);
//            if (findSymbole(symbole.getName(), DocumentVisitor.scopesStack.peek()) == false)
//			{
//
//				symboletable.addSymbole(symbole);
//
//			}
			//semanticCheck.isVariableExist(symbole.getName(),DocumentVisitor.scopesStack.peek());
			//semanticCheck.isVariableExist(symbole.getName(),DocumentVisitor.scopesStack.peek());

        }
        if(Element.equals("Directive") )
        {
			if(!Element_Directive_name.equals("cp-model"))
			{
				if(ElementDirective_number==1)
					store_symbole_scope(ctx.getChild(0).getText() , DocumentVisitor.scopesStack.peek().getParent());
			}
			else {
				store_symbole_scope(ctx.getChild(0).getText() , DocumentVisitor.scopesStack.peek());
			}


        }
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

        char d = ctx.getChild(0).getText().charAt(0);
if(filter_method.equals("currency"))
{
    if(ctx.APO().size()==0)
    {
        try {

            FileWriter fw = new FileWriter(ErrorFile , true);
            BufferedWriter error = new BufferedWriter(fw);
            line_number=ctx.start.getLine();
            error.write("erro in line:"+""+line_number);
            error.write("(currency) pipe should recieve one character only" );
            error.newLine();
            error.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

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
public boolean 	findSymbole( String symbolename , Scope currentscope){
		boolean find=false;

for(int i=0;i<symboletable.getSymboles().size();i++)
	{
		if(symboletable.getSymboles().get(i).getName().equals(symbolename) &&
		symboletable.getSymboles().get(i).getSymbole_scope().getId().equals(currentscope.getId())
		)
		{
			find=true;

			break;
		}

	}
//
//if(currentscope.getId().equals("global"))
//{
//	find=false;
//
//}
//else{
//		findSymbole(  symbolename , currentscope.getParent());
//	}

return find;
	}
    public void  store_symbole_scope(String value , Scope scope)
    {

        boolean find=false;
        if(scope.getId().equals("global"))
        {
			for(int i=0;i<symboletable.getSymboles().size();i++)
			{

				if(symboletable.getSymboles().get(i).getName().equals(value)
						&& symboletable.getSymboles().get(i).getSymbole_scope().getId().equals("global")
				)
				{

					find=true;
					return;

				}
			}
			if(find==false){
			Symbole symbole = new Symbole(value);
			Scope symbole_scope = new Scope();
			symbole_scope.setId("global");
			symbole.setSymbole_scope(symbole_scope);
			symboletable.addSymbole(symbole);
			return ;
		}
        }
        for(int i=0;i<symboletable.getSymboles().size();i++)
        {

            if(symboletable.getSymboles().get(i).getName().equals(value)
			&& symboletable.getSymboles().get(i).getSymbole_scope().getId().equals(scope.getId())
			)
            {

                find=true;
                return;

            }
        }
        if(find==false)
        {
            store_symbole_scope(value,scope.getParent());
        }
    }
    public void  get_Directive_value(String Directive_value){
        Element_Directive_Value= Directive_value;
    }
}