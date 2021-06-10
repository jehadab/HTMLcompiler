package visitors;

import java.util.Stack;


import SymboleTable.SymboleTable;
import misc.HTMLParserBaseVisitor;

public class Visitor<T> extends HTMLParserBaseVisitor<T> {
    static boolean  visited =false  ;
    static String Element ="";
	static public SymboleTable symboletable = new SymboleTable();
	static DocumentVisitor documentVisitor = new DocumentVisitor();
	static ExpressionVisitor expressionVisitor = new ExpressionVisitor();


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
}