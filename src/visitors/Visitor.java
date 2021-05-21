package visitors;

import java.util.Stack;


import SymboleTable.SymboleTable;
import misc.HTMLParserBaseVisitor;

public class Visitor<T> extends HTMLParserBaseVisitor<T> {
	static public SymboleTable symboletable = new SymboleTable();
	static DocumentVisitor documentVisitor = new DocumentVisitor();
	static ExpressionVisitor expressionVisitor = new ExpressionVisitor();
	public static void showSymboleTable() {
		System.out.println("scopes stored :" + symboletable.getScopes().size());
		System.out.println("_______________");
		for (int i = 0; i < symboletable.getScopes().size(); i++) {
			System.out.println("scope: " + symboletable.getScopes().get(i).getId());
			if(symboletable.getScopes().get(i).getParent().getId()==null)
			{
				System.out.println("father :  null " );
			}
			else{
				System.out.println("father : " + symboletable.getScopes().get(i).getParent().getId());
			}


		}
	}
}