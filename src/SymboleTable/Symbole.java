package SymboleTable;

public class Symbole {
    String name ;
    // value stored in the symbole
    Scope symbole_scope ;

    public Symbole(String name) {
        this.name = name;

    }

    public void setSymbole_scope(Scope symbole_scope) {
        this.symbole_scope = symbole_scope;
    }

    public String getName() {
        return name;
    }

    public Scope getSymbole_scope() {
        return symbole_scope;
    }
}
