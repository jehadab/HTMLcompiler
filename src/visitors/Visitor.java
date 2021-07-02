package visitors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import SymboleTable.Scope;
import SymboleTable.Symbole;
import SymboleTable.SymboleTable;
import CodeGeneration.codegeneration;
import SemanticCheck.SemanticCheck;
import SymboleTable.SymboleTable;
import misc.HTMLParserBaseVisitor;
import models.AbstractASTNode;
import models.directive.Directive;
import models.nodes.AttributeNode;
import models.nodes.MustachNode;
import models.statements.ArrayLoopStatement;
import models.statements.ObjectLoopStatement;

public class Visitor<T> extends HTMLParserBaseVisitor<T> {
	static public  String ErrorFile =".\\error.txt";
	static  public String Result_File =".\\result.html";
    static boolean  visited =false  ;
    static int ElementDirective_number = 0;
	static  public boolean  a_tag=false;
	static public boolean is_herf=false;
    static String Element ="";
    static  String Element_Directive_name="";
	 static boolean is_src = false;
    static SemanticCheck semanticCheck = new SemanticCheck() ;
    static public SymboleTable symboletable = new SymboleTable();
    static int cppapp_number =0;
    static  public int  line_number=0;
    static  public  boolean  pipElement = false;
	static DocumentVisitor documentVisitor = new DocumentVisitor();
	static ExpressionVisitor expressionVisitor = new ExpressionVisitor();
	public static codegeneration generation_object = new codegeneration();
	static public String filter_method="";
	static public String  Element_Directive_Value="";
	static public String  Element_id_Value="noid";

	protected int ulolNumber = 0;
	public static Stack<Scope> scopesStack = new Stack<>();

	public static void showSymboleTable() {
		System.out.println("scopes stored :" + symboletable.getScopes().size());
		System.out.println("_______________");
		for (int i = 0; i < symboletable.getScopes().size(); i++) {
			System.out.println("scope: " + symboletable.getScopes().get(i).getId());
			if(symboletable.getScopes().get(i).getParent()==null)
			{
				System.out.println("father :  null " );
			}
			else{
				System.out.println("father : " + symboletable.getScopes().get(i).getParent().getId());
			}


		}
	}
	public static  void print_symbole_linked_list(){
		for (int i=0;i<symboletable.getSymboles().size();i++)
		{
			System.out.println(" symbole name   :"+symboletable.getSymboles().get(i).getName());
			System.out.println(" scope id for the symbole is "+symboletable.getSymboles().get(i).getSymbole_scope().getId());
		}
	}

	public void write_error(String errorMsg,int line_number)
	{
		try {
			FileWriter fw = new FileWriter(ErrorFile , true);
			BufferedWriter error = new BufferedWriter(fw);
			error.write("error in line:"+line_number +" ");
			error.write(errorMsg);
			error.newLine();
			error.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public String getElementId(List<AbstractASTNode> attributes)
	{
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i) instanceof AttributeNode) {
				AttributeNode attributeNode = (AttributeNode) attributes.get(i);
				if (attributeNode.getAttribute().equals("id")) {
					return attributeNode.getValue().replaceAll("\"","");
				}
			}
		}
		return "";
	}

	public void semanticCheck(String tagName,List<AbstractASTNode> attributes,int line_number)
	{
		int ElementDirective_counter = 0;
		String elementId = getElementId(attributes);

		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i) instanceof Directive) {
				Directive directive = (Directive) attributes.get(i);
				String Directive_name = directive.getName();

				if (Directive_name.equals("cp-for")) {
					Scope scope = new Scope(scopesStack.peek());
					scope.setId(tagName + "_" + scope.hashCode());
					symboletable.addScope(scope);
					scopesStack.push(scope);
				}
			}
		}
		/*----------------------------- Semantic Check ------------------------*/

		/*---------Number 1 : id attr must be unique------------*/
		if(!elementId.equals("") ) {
			if (CheckIdValue(elementId) == false) {
				symboletable.addId(elementId);
			} else {
				write_error("id attribute must be uniqe on document level",line_number);
			}
		}
		/*---------id attr must be unique------------*/

		/*---------Number 2 : loop element------------*/
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i) instanceof Directive) {
				Directive directive = (Directive) attributes.get(i);
				String Directive_name = directive.getName();

				if (Directive_name.equals("cp-for")) {
					if(!(directive.getValue() instanceof ObjectLoopStatement)) {
						ArrayLoopStatement arrayLoopStatement = (ArrayLoopStatement) directive.getValue();
						isVariableExist(arrayLoopStatement.getLoopVariable(), scopesStack.peek(), line_number);
						if (arrayLoopStatement.getIndexVariable() != null)
							isVariableExist(arrayLoopStatement.getIndexVariable(), scopesStack.peek(), line_number);
					}
				}
			}
		}
		/*---------loop element------------*/

		/*---------Number 3: li tag-------*/
		if (tagName.equals("li") && ulolNumber < 1) {
			write_error("li tag should not be outside ul/ol",line_number);
		}
		/*---------li tag-------*/

		/*---------Number 4: cp-app nested-------*/
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i) instanceof Directive) {
				Directive directive = (Directive) attributes.get(i);
				String Directive_name = directive.getName();

				if (Directive_name.equals("cp-app")) {
					if (cppapp_number > 0) {
						write_error("nested cp-app is forbidden",line_number);
					}
					cppapp_number++;
				}
			}
		}
		/*---------li tag-------*/

		/*---------Number 5: directive number check-------*/
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i) instanceof Directive) {
				String Directive_name = ((Directive) attributes.get(i)).getName();
				if (!Directive_name.equals("cp-model")) {
					ElementDirective_counter++;
				}
			}
		}
		if (ElementDirective_counter > 1) {
			write_error("Each html element has at most one structural attribute",line_number);
		}
		/*---------directive number check-------*/

		/*---------Number 6: img src-------*/
		if (tagName.equals("img")) {
			boolean is_src = false;
			for (AbstractASTNode attr : attributes) {
				if (attr instanceof AttributeNode) {
					AttributeNode attributeNode = (AttributeNode) attr;
					if (attributeNode.getAttribute().equals("src")) {
						if (!attributeNode.getValue().equals("\"\"")) {
							is_src = true;
						}
					}
				}
			}
			if (!is_src) write_error("Img tag must has src attribute",line_number);
		}
		/*---------img src-------*/

		/*---------Number 7: tag href-------*/
		if (tagName.equals("a")) {
			boolean is_herf = false;
			for (AbstractASTNode attr : attributes) {
				if (attr instanceof AttributeNode) {
					AttributeNode attributeNode = (AttributeNode) attr;
					if (attributeNode.getAttribute().equals("href")) {
						if (!attributeNode.getValue().equals("\"\"")) {
							is_herf = true;
						}
					}
				}
			}
			if (!is_herf) write_error("Anchor tag must has href attribute",line_number);
		}
		/*---------tag href-------*/

		/*----------------------------- Semantic Check ------------------------*/
	}

	public void semanticCheckMustaches(String tagName,List<AbstractASTNode> attributes,int line_number,List<MustachNode> mustaches)
	{
		/*---------Number 8 - 9 - 10: mustache filter-------*/
		if(mustaches != null)
		{
			for (MustachNode mustache: mustaches) {
				if(mustache.getMethod().equals("date"))
				{
					boolean is_ok = false;
					for (String par: mustache.getParameter_value()) {
						if(par.equals("dd/mm/yyyy h:m:s")) is_ok = true;
						if(par.equals("dd/mm/yyyy h:m")) is_ok = true;
						if(par.equals("mm/dd/yyyy h:m:s")) is_ok = true;
						if(par.equals("mm/dd/yyyy h:m")) is_ok = true;
						if(par.equals("dd-mm-yyyy h:m:s")) is_ok = true;
						if(par.equals("dd-mm-yyyy h:m")) is_ok = true;
						if(par.equals("mm-dd-yyyy h:m:s")) is_ok = true;
						if(par.equals("mm-dd-yyyy h:m")) is_ok = true;
						if(par.equals("mm-yyyy")) is_ok = true;
						if(par.equals("mm-yy")) is_ok = true;
					}
					if(!is_ok) write_error("(date) pipe should receive date format",line_number);
				}

				if(mustache.getMethod().equals("currency"))
				{
					boolean is_ok = true;
					for (String par: mustache.getParameter_value()) {
						if(par.length() > 1)is_ok = false;
					}
					if(!is_ok) write_error("(currency) pipe should receive one character only",line_number);
				}

				if(mustache.getMethod().equals("lower") || mustache.getMethod().equals("upper"))
				{
					if(mustache.getParameter_value() == null)
					{
						write_error("(upper/lower) pipe should not receive any parameter",line_number);
						continue;
					}
					if(!mustache.getParameter_value().isEmpty()) write_error("(upper/lower) pipe should not receive any parameter",line_number);
				}
			}
		}
		/*---------mustache filter-------*/
	}

	public boolean CheckIdValue(String id) {
		boolean exits = false;
		for (int i = 0; i < symboletable.getids().size(); i++) {
			if (id.equals(symboletable.getids().get(i))) {
				exits = true;
			}
		}
		return exits;
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
	public void  store_symbole_scope(String value , Scope scope) {

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
	public void isVariableExist(String variableName , Scope currentScope,int line_number){

		if (isSymboleExist(variableName , currentScope)){
			write_error(variableName +" is decelerated before, the iterator variable should not repeat",line_number);
		}else {
			// add variableName to symboletable
			Symbole symboleScope = new Symbole(variableName);
			symboleScope.setSymbole_scope(currentScope);
			symboletable.addSymbole(symboleScope);
		}

	}
	public boolean isSymboleExist(String variableName , Scope currentScope){

		boolean _isVariableFound = false;

		for (Symbole _symbole : symboletable.getSymboles()) {
			if(_symbole.getName().equals(variableName)
			){
				if(isVariableWithSameScope(currentScope,_symbole.getSymbole_scope())){

					_isVariableFound =  true;
				}
			}
		}
		return _isVariableFound;
	}
	private ArrayList<Scope> getScopeFatherList(Scope scope){
		ArrayList<Scope> _scopes = new ArrayList<Scope>();
		while (scope.getParent()!= null){
			_scopes.add(scope);
			scope = scope.getParent();
		}
		return _scopes;
	}
	private boolean isVariableWithSameScope( Scope variableScope , Scope newVariableScope){
		ArrayList<Scope> fatherScopeList = getScopeFatherList(variableScope);
		for (Scope scope:fatherScopeList
		) {
			if(scope.getId().equals(newVariableScope.getId())){
				return true;
			}
		}
		return false;
	}
}