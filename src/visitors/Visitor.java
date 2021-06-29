package visitors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Stack;


import CodeGeneration.codegeneration;
import SemanticCheck.SemanticCheck;
import SymboleTable.SymboleTable;
import misc.HTMLParserBaseVisitor;
import models.AbstractASTNode;
import models.directive.Directive;
import models.nodes.AttributeNode;

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
	static  codegeneration generation_object = new codegeneration();
	static   public String filter_method="";
	static public String  Element_Directive_Value="";
	static public String  Element_id_Value="noid";

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
		/*----------------------------- Semantic Check ------------------------*/

		/*---------id attr must be unique------------*/
		if(!elementId.equals("") )
		{
			if (CheckIdValue(elementId) == false) {
				symboletable.addId(elementId);
			} else {
				write_error("id attribute must be uniqe on document level",line_number);
			}
		}
		/*---------id attr must be unique------------*/

		/*---------tag href-------*/
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

		/*---------img src-------*/
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

		/*---------directive number check-------*/
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

		/*----------------------------- Semantic Check ------------------------*/
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
}