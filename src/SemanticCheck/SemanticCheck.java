package SemanticCheck;

import SymboleTable.Scope;
import SymboleTable.Symbole;

import java.util.ArrayList;

import static visitors.Visitor.symboletable;

public class SemanticCheck {


    public void isVariableExist(String variableName , Scope currentScope){

        if (isSymboleExist(variableName , currentScope)){
            System.err.println(variableName + " is declerated before in the before " );

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
        for ( Scope scope : getScopeFatherList(currentScope)){
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
//        fatherScopeList.forEach((s)->{
//        System.out.println(((Scope)s).getId());
//
//        });

        for (Scope scope:fatherScopeList
                ) {
            if(scope.getId().equals(newVariableScope.getId())){
                return true;
            }
        }
        return false;
    }
}
