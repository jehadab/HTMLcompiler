// Generated from HTMLParser.g4 by ANTLR 4.9

package misc;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HTMLParser}.
 */
public interface HTMLParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HTMLParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(HTMLParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(HTMLParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(HTMLParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(HTMLParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#scriptletOrSeaWs}.
	 * @param ctx the parse tree
	 */
	void enterScriptletOrSeaWs(HTMLParser.ScriptletOrSeaWsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#scriptletOrSeaWs}.
	 * @param ctx the parse tree
	 */
	void exitScriptletOrSeaWs(HTMLParser.ScriptletOrSeaWsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(HTMLParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(HTMLParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#node}.
	 * @param ctx the parse tree
	 */
	void enterNode(HTMLParser.NodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#node}.
	 * @param ctx the parse tree
	 */
	void exitNode(HTMLParser.NodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(HTMLParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(HTMLParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(HTMLParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(HTMLParser.StyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#elementContent}.
	 * @param ctx the parse tree
	 */
	void enterElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#elementContent}.
	 * @param ctx the parse tree
	 */
	void exitElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code HalfElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void enterHalfElement(HTMLParser.HalfElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code HalfElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void exitHalfElement(HTMLParser.HalfElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void enterNormalElement(HTMLParser.NormalElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void exitNormalElement(HTMLParser.NormalElementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SelfClosedElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void enterSelfClosedElement(HTMLParser.SelfClosedElementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SelfClosedElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 */
	void exitSelfClosedElement(HTMLParser.SelfClosedElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#textNode}.
	 * @param ctx the parse tree
	 */
	void enterTextNode(HTMLParser.TextNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#textNode}.
	 * @param ctx the parse tree
	 */
	void exitTextNode(HTMLParser.TextNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Comment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 */
	void enterComment(HTMLParser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Comment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 */
	void exitComment(HTMLParser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConditionalComment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 */
	void enterConditionalComment(HTMLParser.ConditionalCommentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConditionalComment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 */
	void exitConditionalComment(HTMLParser.ConditionalCommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#elementAttributes}.
	 * @param ctx the parse tree
	 */
	void enterElementAttributes(HTMLParser.ElementAttributesContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#elementAttributes}.
	 * @param ctx the parse tree
	 */
	void exitElementAttributes(HTMLParser.ElementAttributesContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#attributeNode}.
	 * @param ctx the parse tree
	 */
	void enterAttributeNode(HTMLParser.AttributeNodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#attributeNode}.
	 * @param ctx the parse tree
	 */
	void exitAttributeNode(HTMLParser.AttributeNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterExpDirective(HTMLParser.ExpDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitExpDirective(HTMLParser.ExpDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DefaultDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDefaultDirective(HTMLParser.DefaultDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DefaultDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDefaultDirective(HTMLParser.DefaultDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StmtDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void enterStmtDirective(HTMLParser.StmtDirectiveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StmtDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 */
	void exitStmtDirective(HTMLParser.StmtDirectiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditionExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditionExpression(HTMLParser.AdditionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditionExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditionExpression(HTMLParser.AdditionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DividExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDividExpression(HTMLParser.DividExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DividExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDividExpression(HTMLParser.DividExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostIncrementExpression(HTMLParser.PostIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostIncrementExpression(HTMLParser.PostIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreDecrementExpression(HTMLParser.PreDecrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreDecrementExpression(HTMLParser.PreDecrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreIncrementExpression(HTMLParser.PreIncrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreIncrementExpression(HTMLParser.PreIncrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ModExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterModExpression(HTMLParser.ModExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ModExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitModExpression(HTMLParser.ModExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiExpression(HTMLParser.MultiExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiExpression(HTMLParser.MultiExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpression(HTMLParser.LogicalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpression(HTMLParser.LogicalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MinusExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMinusExpression(HTMLParser.MinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MinusExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMinusExpression(HTMLParser.MinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryConditionalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryConditionalExpression(HTMLParser.TernaryConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryConditionalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryConditionalExpression(HTMLParser.TernaryConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExclaminationExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExclaminationExpression(HTMLParser.ExclaminationExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExclaminationExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExclaminationExpression(HTMLParser.ExclaminationExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPostDecrementExpression(HTMLParser.PostDecrementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPostDecrementExpression(HTMLParser.PostDecrementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Exp}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExp(HTMLParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Exp}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExp(HTMLParser.ExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FilterExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(HTMLParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FilterExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(HTMLParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#parenthesized}.
	 * @param ctx the parse tree
	 */
	void enterParenthesized(HTMLParser.ParenthesizedContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#parenthesized}.
	 * @param ctx the parse tree
	 */
	void exitParenthesized(HTMLParser.ParenthesizedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MoreThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterMoreThanComparisionExpression(HTMLParser.MoreThanComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MoreThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitMoreThanComparisionExpression(HTMLParser.MoreThanComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterLessThanComparisionExpression(HTMLParser.LessThanComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitLessThanComparisionExpression(HTMLParser.LessThanComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MoreEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterMoreEqualComparisionExpression(HTMLParser.MoreEqualComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MoreEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitMoreEqualComparisionExpression(HTMLParser.MoreEqualComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LessEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterLessEqualComparisionExpression(HTMLParser.LessEqualComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LessEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitLessEqualComparisionExpression(HTMLParser.LessEqualComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterEqualComparisionExpression(HTMLParser.EqualComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitEqualComparisionExpression(HTMLParser.EqualComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void enterNotEqualComparisionExpression(HTMLParser.NotEqualComparisionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 */
	void exitNotEqualComparisionExpression(HTMLParser.NotEqualComparisionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterAndExpression(HTMLParser.AndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitAndExpression(HTMLParser.AndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void enterOrExpression(HTMLParser.OrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 */
	void exitOrExpression(HTMLParser.OrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void enterLoopStatement(HTMLParser.LoopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#loopStatement}.
	 * @param ctx the parse tree
	 */
	void exitLoopStatement(HTMLParser.LoopStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayLoopRaw}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 */
	void enterArrayLoopRaw(HTMLParser.ArrayLoopRawContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayLoopRaw}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 */
	void exitArrayLoopRaw(HTMLParser.ArrayLoopRawContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IndexedArrayLoop}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 */
	void enterIndexedArrayLoop(HTMLParser.IndexedArrayLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IndexedArrayLoop}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 */
	void exitIndexedArrayLoop(HTMLParser.IndexedArrayLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RawObjectLoop}
	 * labeled alternative in {@link HTMLParser#objectLoop}.
	 * @param ctx the parse tree
	 */
	void enterRawObjectLoop(HTMLParser.RawObjectLoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RawObjectLoop}
	 * labeled alternative in {@link HTMLParser#objectLoop}.
	 * @param ctx the parse tree
	 */
	void exitRawObjectLoop(HTMLParser.RawObjectLoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReferenceExpression}
	 * labeled alternative in {@link HTMLParser#reference}.
	 * @param ctx the parse tree
	 */
	void enterReferenceExpression(HTMLParser.ReferenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReferenceExpression}
	 * labeled alternative in {@link HTMLParser#reference}.
	 * @param ctx the parse tree
	 */
	void exitReferenceExpression(HTMLParser.ReferenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(HTMLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(HTMLParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#access}.
	 * @param ctx the parse tree
	 */
	void enterAccess(HTMLParser.AccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#access}.
	 * @param ctx the parse tree
	 */
	void exitAccess(HTMLParser.AccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(HTMLParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(HTMLParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#compound}.
	 * @param ctx the parse tree
	 */
	void enterCompound(HTMLParser.CompoundContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#compound}.
	 * @param ctx the parse tree
	 */
	void exitCompound(HTMLParser.CompoundContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#literalArray}.
	 * @param ctx the parse tree
	 */
	void enterLiteralArray(HTMLParser.LiteralArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#literalArray}.
	 * @param ctx the parse tree
	 */
	void exitLiteralArray(HTMLParser.LiteralArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#literalObject}.
	 * @param ctx the parse tree
	 */
	void enterLiteralObject(HTMLParser.LiteralObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#literalObject}.
	 * @param ctx the parse tree
	 */
	void exitLiteralObject(HTMLParser.LiteralObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#indexAccess}.
	 * @param ctx the parse tree
	 */
	void enterIndexAccess(HTMLParser.IndexAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#indexAccess}.
	 * @param ctx the parse tree
	 */
	void exitIndexAccess(HTMLParser.IndexAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#propertyAccess}.
	 * @param ctx the parse tree
	 */
	void enterPropertyAccess(HTMLParser.PropertyAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#propertyAccess}.
	 * @param ctx the parse tree
	 */
	void exitPropertyAccess(HTMLParser.PropertyAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#objectProperty}.
	 * @param ctx the parse tree
	 */
	void enterObjectProperty(HTMLParser.ObjectPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#objectProperty}.
	 * @param ctx the parse tree
	 */
	void exitObjectProperty(HTMLParser.ObjectPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#parameters}.
	 * @param ctx the parse tree
	 */
	void enterParameters(HTMLParser.ParametersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#parameters}.
	 * @param ctx the parse tree
	 */
	void exitParameters(HTMLParser.ParametersContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(HTMLParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(HTMLParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link HTMLParser#mustache}.
	 * @param ctx the parse tree
	 */
	void enterMustache(HTMLParser.MustacheContext ctx);
	/**
	 * Exit a parse tree produced by {@link HTMLParser#mustache}.
	 * @param ctx the parse tree
	 */
	void exitMustache(HTMLParser.MustacheContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RawFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterRawFilter(HTMLParser.RawFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RawFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitRawFilter(HTMLParser.RawFilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParametrizedFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterParametrizedFilter(HTMLParser.ParametrizedFilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParametrizedFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitParametrizedFilter(HTMLParser.ParametrizedFilterContext ctx);
}