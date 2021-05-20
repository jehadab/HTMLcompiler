// Generated from HTMLParser.g4 by ANTLR 4.9

package misc;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HTMLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HTMLParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HTMLParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(HTMLParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeader(HTMLParser.HeaderContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#scriptletOrSeaWs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptletOrSeaWs(HTMLParser.ScriptletOrSeaWsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(HTMLParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#node}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNode(HTMLParser.NodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(HTMLParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#style}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStyle(HTMLParser.StyleContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#elementContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementContent(HTMLParser.ElementContentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code HalfElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHalfElement(HTMLParser.HalfElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NormalElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNormalElement(HTMLParser.NormalElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SelfClosedElement}
	 * labeled alternative in {@link HTMLParser#elementNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfClosedElement(HTMLParser.SelfClosedElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#textNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTextNode(HTMLParser.TextNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Comment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(HTMLParser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ConditionalComment}
	 * labeled alternative in {@link HTMLParser#commentNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalComment(HTMLParser.ConditionalCommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#elementAttributes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElementAttributes(HTMLParser.ElementAttributesContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#attributeNode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeNode(HTMLParser.AttributeNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpDirective(HTMLParser.ExpDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DefaultDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultDirective(HTMLParser.DefaultDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StmtDirective}
	 * labeled alternative in {@link HTMLParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtDirective(HTMLParser.StmtDirectiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AdditionExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditionExpression(HTMLParser.AdditionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DividExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDividExpression(HTMLParser.DividExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostIncrementExpression(HTMLParser.PostIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreDecrementExpression(HTMLParser.PreDecrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PreIncrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreIncrementExpression(HTMLParser.PreIncrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ModExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModExpression(HTMLParser.ModExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultiExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiExpression(HTMLParser.MultiExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComparisonExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonExpression(HTMLParser.ComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LogicalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpression(HTMLParser.LogicalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MinusExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpression(HTMLParser.MinusExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TernaryConditionalExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTernaryConditionalExpression(HTMLParser.TernaryConditionalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExclaminationExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExclaminationExpression(HTMLParser.ExclaminationExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostDecrementExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostDecrementExpression(HTMLParser.PostDecrementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Exp}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(HTMLParser.ExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FilterExpression}
	 * labeled alternative in {@link HTMLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpression(HTMLParser.FilterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#parenthesized}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesized(HTMLParser.ParenthesizedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MoreThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreThanComparisionExpression(HTMLParser.MoreThanComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessThanComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessThanComparisionExpression(HTMLParser.LessThanComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MoreEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMoreEqualComparisionExpression(HTMLParser.MoreEqualComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LessEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLessEqualComparisionExpression(HTMLParser.LessEqualComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualComparisionExpression(HTMLParser.EqualComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotEqualComparisionExpression}
	 * labeled alternative in {@link HTMLParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotEqualComparisionExpression(HTMLParser.NotEqualComparisionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpression(HTMLParser.AndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrExpression}
	 * labeled alternative in {@link HTMLParser#logicalOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpression(HTMLParser.OrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#loopStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopStatement(HTMLParser.LoopStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayLoopRaw}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayLoopRaw(HTMLParser.ArrayLoopRawContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IndexedArrayLoop}
	 * labeled alternative in {@link HTMLParser#arrayLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexedArrayLoop(HTMLParser.IndexedArrayLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RawObjectLoop}
	 * labeled alternative in {@link HTMLParser#objectLoop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawObjectLoop(HTMLParser.RawObjectLoopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ReferenceExpression}
	 * labeled alternative in {@link HTMLParser#reference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReferenceExpression(HTMLParser.ReferenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(HTMLParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(HTMLParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(HTMLParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BooleanLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanLiteral(HTMLParser.BooleanLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringLiteral}
	 * labeled alternative in {@link HTMLParser#primitive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteral(HTMLParser.StringLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#compound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompound(HTMLParser.CompoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#literalArray}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralArray(HTMLParser.LiteralArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#literalObject}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralObject(HTMLParser.LiteralObjectContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#indexAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexAccess(HTMLParser.IndexAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#propertyAccess}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyAccess(HTMLParser.PropertyAccessContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#objectProperty}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectProperty(HTMLParser.ObjectPropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HTMLParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#parameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameters(HTMLParser.ParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(HTMLParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link HTMLParser#mustache}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMustache(HTMLParser.MustacheContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RawFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRawFilter(HTMLParser.RawFilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParametrizedFilter}
	 * labeled alternative in {@link HTMLParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParametrizedFilter(HTMLParser.ParametrizedFilterContext ctx);
}