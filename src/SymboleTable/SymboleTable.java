package SymboleTable;

import java.util.ArrayList;

public class SymboleTable {
    private ArrayList<Scope> scopes = new ArrayList();
    private ArrayList<Id> ids = new ArrayList();
    private  ArrayList<Symbole> symboles = new ArrayList<Symbole>();
    public void addSymbole(Symbole  symbole) {
        this.symboles.add(symbole);
    }

    public ArrayList<Symbole> getSymboles() {
        return symboles;
    }

    public void addScope(Scope scope) {
        this.scopes.add(scope);
    }
    public void addId(Id id) {
        this.ids.add(id);
    }
    public ArrayList<Scope> getScopes() {
        return this.scopes;
    }
    public ArrayList<Id> getids(){return this.ids;}
}
