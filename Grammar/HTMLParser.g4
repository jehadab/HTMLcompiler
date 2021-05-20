
parser grammar HTMLParser;

@header {
package misc;
}

options { tokenVocab=HTMLLexer; }

document
    : header body
    ;

header
	: scriptletOrSeaWs* XML? scriptletOrSeaWs* DTD? scriptletOrSeaWs* (commentNode | SEA_WS )*
	;
    
scriptletOrSeaWs
    : SCRIPTLET
    | SEA_WS
    ;

body
	: node*
	;
	
node
    : elementNode
    | commentNode
    | textNode
    | scriptletOrSeaWs
    | style
    | script
    ;

script
	: SCRIPT_OPEN (SCRIPT_BODY | SCRIPT_SHORT_BODY)
	;
	
style
	: STYLE_OPEN (STYLE_BODY | STYLE_SHORT_BODY)
	;
	
elementContent
    : ( node | CDATA | mustache )*
    ;

elementNode
	: TAG_OPEN TAG_NAME elementAttributes? TAG_CLOSE #HalfElement
    | TAG_OPEN TAG_NAME elementAttributes? TAG_CLOSE elementContent TAG_SLASH_OPEN TAG_NAME TAG_CLOSE #NormalElement
    | TAG_OPEN TAG_NAME elementAttributes? TAG_SLASH_CLOSE #SelfClosedElement
	;
	
textNode
    : HTML_TEXT
    | SEA_WS
    ;

commentNode
    : HTML_COMMENT #Comment
    | HTML_CONDITIONAL_COMMENT #ConditionalComment
    ;
    
elementAttributes
    : (attributeNode | directive)+
    ;

attributeNode
	: ATT_NAME (TAG_EQUALS ATT_VALUE)?
	;

directive
	: CP_APP            TAG_EQUALS TERMINAL reference TERMINAL #ExpDirective
	| CP_MODEL          TAG_EQUALS TERMINAL (reference | access) TERMINAL #ExpDirective
	| CP_SHOW           TAG_EQUALS TERMINAL expression TERMINAL #ExpDirective
	| CP_HIDE           TAG_EQUALS TERMINAL expression TERMINAL #ExpDirective
	| CP_IF             TAG_EQUALS TERMINAL expression TERMINAL #ExpDirective
	| CP_SWITCH         TAG_EQUALS TERMINAL expression TERMINAL #ExpDirective
	| CP_SWITCH_CASE    TAG_EQUALS TERMINAL expression TERMINAL #ExpDirective
	| CP_SWITCH_DEFAULT #DefaultDirective
	| CP_FOR            TAG_EQUALS TERMINAL loopStatement TERMINAL #StmtDirective
	| EVENT             TAG_EQUALS TERMINAL functionCall TERMINAL #ExpDirective
	;
	
expression
	: reference #Exp
	| literal #Exp
	| access #Exp
	| EXC expression #ExclaminationExpression
	| INCREMENT expression #PreIncrementExpression
	| DECREMENT expression #PreDecrementExpression
	| expression INCREMENT #PostIncrementExpression
	| expression DECREMENT #PostDecrementExpression
	| expression MULT expression #MultiExpression
	| expression DIVIDE expression #DividExpression
	| expression SUM expression #AdditionExpression
	| expression MINUS expression #MinusExpression
	| expression MOD expression #ModExpression
	| expression comparisonOperator expression #ComparisonExpression
	| expression logicalOperator expression #LogicalExpression
	| expression QUESTION_MARK expression COLON expression #TernaryConditionalExpression
	| expression filter #FilterExpression
	| parenthesized #Exp
	| functionCall #Exp
	| mustache #Exp
	;

parenthesized
	: OPEN_PAR expression CLOSE_PAR
	;
	
comparisonOperator
	: MORE_THAN #MoreThanComparisionExpression
	| LESS_THAN #LessThanComparisionExpression
	| MORE_EQUAL_THAN #MoreEqualComparisionExpression
	| LESS_EQUAL_THAN #LessEqualComparisionExpression
	| EQUAL #EqualComparisionExpression
	| NOT_EQUAL #NotEqualComparisionExpression
	;
	
logicalOperator
	: AND #AndExpression
	| OR #OrExpression
	;
	
loopStatement
	: arrayLoop
	| objectLoop
	;

arrayLoop
	: IDENTIFIRE IN (reference | literalArray | access) #arrayLoopRaw
	| IDENTIFIRE IN (reference | literalArray | access) SEMICOLON IDENTIFIRE ASSIGN INDEX #IndexedArrayLoop
	;

objectLoop
	: IDENTIFIRE COMMA IDENTIFIRE IN (reference | literalObject | access) #RawObjectLoop
	;

reference
	: IDENTIFIRE #ReferenceExpression
	;

literal
	: primitive
	| compound 
	;

access 
	: (reference | literalObject | literalArray) indexAccess
	| (reference | literalObject) propertyAccess
	; 	
	
primitive
	: NUMBER #NumberLiteral
	| BOOLEAN #BooleanLiteral
	| APO STRING_WITH_QUOT APO #StringLiteral
	| QUOT STRING_WITH_APO QUOT #StringLiteral
	;

compound
	: literalArray 
	| literalObject
	;
		
literalArray
	: OPEN_SQUARE literal (COMMA literal)* CLOSE_SQUARE
	;

literalObject
	: OPEN_BRACKET objectProperty (COMMA objectProperty)* COMMA? CLOSE_BRACKET
	;

indexAccess 
	: OPEN_SQUARE (reference | literal) CLOSE_SQUARE (indexAccess | propertyAccess)?
	;

propertyAccess 
	: DOT reference (indexAccess | propertyAccess)?
	;
		
objectProperty
	: IDENTIFIRE COLON expression
	;
	
functionCall
	: reference OPEN_PAR parameters? CLOSE_PAR
	;

parameters
	: ( parameter (COMMA parameter)* )
	;

parameter
	: expression | access 
	;
	
mustache
	: MUSTACHE_OPEN expression ? MUSTACHE_CLOSE
	;
	
filter
	: PIPE reference #RawFilter
	| PIPE reference COLON parameters #ParametrizedFilter
	;
	