package models.expression.value.reference;

import CodeGeneration.codegeneration;
import SymboleTable.Scope;
import SymboleTable.Symbole;
import models.expression.ValueExpression;
import models.util.Formatter;
import visitors.Visitor;

import java.util.ArrayList;

import static visitors.Visitor.scopesStack;
import static visitors.Visitor.symboletable;

public class ReferenceExpression extends ValueExpression {

	protected String name;
	
	public ReferenceExpression(String name) {
		this.name = name;
	}

	@Override
	protected String nodeName() {
		return "ReferenceExpression";
	}

	@Override
	protected Formatter nodeValue(Formatter formatter) {
		return formatter.addProperty("reference", name);
	}

	public String getName() {
		return name;
	}

	@Override
	public String getExpressionForJS() {
		boolean _isVariableFound = false;

		for (Symbole _symbole : symboletable.getSymboles()) {
			if(_symbole.getName().equals(name)
			){
				if(isVariableWithSameScope(scopesStack.peek(),_symbole.getSymbole_scope())){
					_isVariableFound =  true;
				}
			}
		}
		return _isVariableFound?name:codegeneration.cpapp_value + '.' + name;
	}

	private boolean isVariableWithSameScope(Scope variableScope , Scope newVariableScope){
		ArrayList<Scope> fatherScopeList = getScopeFatherList(variableScope);
		for (Scope scope:fatherScopeList
		) {
			if(scope.getId().equals(newVariableScope.getId())){
				return true;
			}
		}
		return false;
	}

	private ArrayList<Scope> getScopeFatherList(Scope scope){
		ArrayList<Scope> _scopes = new ArrayList<Scope>();
		while (scope.getParent()!= null){
			_scopes.add(scope);
			scope = scope.getParent();
		}
		return _scopes;
	}
}
