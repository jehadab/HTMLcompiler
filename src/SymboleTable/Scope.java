package SymboleTable;

public class Scope {
    String  id ;
    Scope parent;

//    public Scope(String  id, Scope parent) {
//        this.id = id;
//        this.parent = parent;
//    }


    public Scope() {
    }

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Scope getParent() {
        return parent;
    }

//map or linkedlist for symbole in the scope ....
}
