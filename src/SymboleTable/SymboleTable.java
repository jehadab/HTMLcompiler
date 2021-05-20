package SymboleTable;

import java.util.ArrayList;

public class SymboleTable {
    private ArrayList<Scope> scopes = new ArrayList();
    public void addScope(Scope scope) {
        this.scopes.add(scope);
    }
    public ArrayList<Scope> getScopes() {
        return this.scopes;
    }
}
