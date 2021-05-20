
lexer grammar  HTMLLexer;

@header {
package misc;
}

@members {
boolean insideDirective;
boolean insideMustache;
boolean hasStartTerminal;
char terminal;
void foo(char symbol, int nextMode) {
	if (insideMustache) {
		pushMode(nextMode);
	} else if (! hasStartTerminal) {
		terminal = symbol;
		hasStartTerminal = true;
		setType(TERMINAL);
	} else if (hasStartTerminal && terminal == symbol) { 
		hasStartTerminal = false;
		popMode();
		setType(TERMINAL);
	} else
		pushMode(nextMode);
}
}
	
HTML_COMMENT
    : '<!--' .*? '-->'
    ;

HTML_CONDITIONAL_COMMENT
    : '<![' .*? ']>'
    ;

XML
    : '<?xml' .*? '>'
    ;

CDATA
    : '<![CDATA[' .*? ']]>'
    ;

DTD
    : '<!' .*? '>'
    ;

SCRIPTLET
    : '<?' .*? '?>'
    | '<%' .*? '%>'
    ;

SEA_WS
    :  (' '|'\t'|'\r'? '\n')+
    ;

SCRIPT_OPEN
    : '<script' .*? '>' ->pushMode(SCRIPT)
    ;

STYLE_OPEN
    : '<style' .*? '>'  ->pushMode(STYLE)
    ;

TAG_SLASH_OPEN
    : '<' WHITESPACE* '/'-> pushMode(NAME_MODE)
    ;
    
TAG_OPEN
    : '<' WHITESPACE* -> pushMode(NAME_MODE)
    ;
	
MUSTACHE_OPEN
	: '{' WHITESPACE* '{' {insideMustache = true; pushMode(DIRECTIVE_MODE);}
	;
	
HTML_TEXT
    : ~[<{]*
    ;
 
mode NAME_MODE;

TAG_NAME
	: TAG_NameStartChar TAG_NameChar* -> mode(REST_OF_TAG_MODE) 
	;
 
mode REST_OF_TAG_MODE;

TAG_CLOSE
    : '>' -> popMode
    ;
    
TAG_SLASH_CLOSE
    : '/' WHITESPACE* '>' -> popMode
    ;
    
CP_APP
	: 'cp-app' {insideDirective = true;}
	;
	
CP_SHOW
	: 'cp-show' {insideDirective = true;}
	;

CP_HIDE
	: 'cp-hide' {insideDirective = true;}
	;
	
CP_IF
	: 'cp-if' {insideDirective = true;}
	;
	
CP_FOR
	: 'cp-for' {insideDirective = true;}
	;

CP_MODEL
	: 'cp-model' {insideDirective = true;}
	;
		
CP_SWITCH
	: 'cp-switch' {insideDirective = true;}
	;

CP_SWITCH_CASE
	: 'cp-switch-case' {insideDirective = true;}
	;

CP_SWITCH_DEFAULT
	: 'cp-switchDefault' {insideDirective = true;}
	;

EVENT
	: '@' EVENT_NAME {insideDirective = true;}
	;

ATT_NAME
    : TAG_NameStartChar TAG_NameChar* {insideDirective = false;}
    ;
    
TAG_EQUALS
    : '=' {if (! insideDirective) pushMode(ATTRIBUTE_VALUE_MODE);
    	   else pushMode(DIRECTIVE_MODE);}
    ;

WHITESPACE_AM
    : WHITESPACE -> channel(HIDDEN)
    ;

mode ATTRIBUTE_VALUE_MODE;



ATT_VALUE
    : ATTRIBUTE -> popMode
    ;
    
WHITESPACE_AVM
    : WHITESPACE -> channel(HIDDEN)
    ;
    
mode DIRECTIVE_MODE;

MUSTACHe_OPEN
	: '{' '{' {insideMustache = true; pushMode(DIRECTIVE_MODE); setType(MUSTACHE_OPEN);}
	;
	
MUSTACHE_CLOSE
	: '}' '}' {insideMustache = false; popMode();}
	;

TERMINAL	
    : '}' '}'
	;
	
QUOT
	: '"'  {foo('"', STRING_VALUE_INSIDE_QUOT);}
	;
	
APO
	: '\'' {foo('\'', STRING_VALUE_INSIDE_APO);}
	;
	
INCREMENT
	: '++'
	;
	
DECREMENT
	: '--'
	;

MULT
	: '*'
	;
	
DIVIDE
	: '/'
	;
	
MINUS
	: '-'
	;
		
MOD
	: '%'
	;
	
SUM
	: '+'
	;
	
NUMBER 
	: DIGIT+
	;
	
BOOLEAN 
	: 'true'
	| 'false'
	;
	
IN
	: 'in'
	;
	
INDEX
	: 'index'
	;
	
ASSIGN
	: '='
	;
	
OPEN_PAR
	: '('
	; 
	
CLOSE_PAR
	: ')'
	;
  
OPEN_SQUARE
	: '['
	;

CLOSE_SQUARE
	: ']'
	;

OPEN_BRACKET
	: '{'
	;

CLOSE_BRACKET
	: '}'
	;
	
LESS_THAN
	: '<'
	;
	 
MORE_THAN
	: '>'
	;

EQUAL
	: '=='
	;

NOT_EQUAL
	: '!='
	;
	
MORE_EQUAL_THAN
	: '>='
	;
	
LESS_EQUAL_THAN
	: '<='
	;
	
PIPE
	: '|'
	;
	
COMMA
	: ','
	;

DOT
	: '.'
	;

SEMICOLON
	: ';'
	;
	
AND
	: '&&'
	;
	
OR
	: '||'
	;

EXC
	: '!'
	;

QUESTION_MARK
	: '?'
	;

COLON
	: ':'
	;
	
IDENTIFIRE
	: (LETTER | UNDER_SCORE) (LETTER | DIGIT | UNDER_SCORE)*
	;

WHITESPACE_EAVM
    : WHITESPACE -> channel(HIDDEN)
    ;	
  
  
mode STRING_VALUE_INSIDE_QUOT;

QUOT_COPY
	: '"' -> type(QUOT), popMode
	;
	
STRING_WITH_APO
	: ~["]+
	;
	
mode STRING_VALUE_INSIDE_APO;

APO_COPY
	: '\'' -> type(APO), popMode
	;
	
STRING_WITH_QUOT
	:  ~'\''+
	;
	
mode SCRIPT;

SCRIPT_BODY
    : .*? '</script>' -> popMode
    ;

SCRIPT_SHORT_BODY
    : .*? '</>' -> popMode
    ;

// <styles>

mode STYLE;

STYLE_BODY
    : .*? '</style>' -> popMode
    ;

STYLE_SHORT_BODY
    : .*? '</>' -> popMode
    ;
    
/**********************************************************
 * Fragments
 * 
 * 
 * ********************************************************/
 
fragment ATTCHARS
    : ATTCHAR+ ' '?
    ;

fragment ATTCHAR
    : '-'
    | '_'
    | '.'
    | '/'
    | '+'
    | ','
    | '?'
    | '='
    | ':'
    | ';'
    | '#'
    | [0-9a-zA-Z]
    ;

fragment HEXCHARS
    : '#' [0-9a-fA-F]+
    ;

fragment DECCHARS
    : [0-9]+ '%'?
    ;

fragment DOUBLE_QUOTE_STRING
    : '"' ~[<"]* '"'
    ;

fragment SINGLE_QUOTE_STRING
    : '\'' ~[<']* '\''
    ;

fragment HEXDIGIT
    : [a-fA-F0-9]
    ;

fragment DIGIT
    : [0-9]
    ;

fragment TAG_NameChar
    : TAG_NameStartChar
    | '-'
    | '_'
    | '.'
    | DIGIT
    | '\u00B7'
    | '\u0300'..'\u036F'
    | '\u203F'..'\u2040'
    ;

fragment TAG_NameStartChar
    : [:a-zA-Z]
    | '\u2070'..'\u218F'
    | '\u2C00'..'\u2FEF'
    | '\u3001'..'\uD7FF'
    | '\uF900'..'\uFDCF'
    | '\uFDF0'..'\uFFFD'
    ;


fragment WHITESPACE
    : [ \t\r\n]
    ;
    
fragment ATTRIBUTE
    : ( DOUBLE_QUOTE_STRING
    | SINGLE_QUOTE_STRING
    | ATTCHARS
    | HEXCHARS
    | DECCHARS )
    ;
    
fragment EVENT_NAME
	: 'click'
	| 'mouseover'
	| 'mousein'
	| 'mouseout'
	| 'focus'
	| 'unfocus'
	| 'submit'
	| 'mousepress'
	| 'mousedown'
	| 'mouseup'
	| 'change'
	;
	
fragment LETTER
	: 'a'..'z' 
	| 'A'..'Z'
	;
	
fragment UNDER_SCORE
	: '_'
	;