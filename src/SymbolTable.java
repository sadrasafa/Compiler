import java.util.ArrayList;

/**
 * Created by aarash on 05/07/17.
 */
public class SymbolTable {

    ArrayList<SymbolTableEntry> symbolTable = new ArrayList<>();

    public SymbolTable() {
        symbolTable = new ArrayList<>();

        symbolTable.add(new SymbolTableEntry("if", "IF"));
        symbolTable.add(new SymbolTableEntry("while", "WHILE"));
        symbolTable.add(new SymbolTableEntry("else", "ELSE"));
        symbolTable.add(new SymbolTableEntry("output", "ID"));
        symbolTable.add(new SymbolTableEntry("return", "RETURN"));
        symbolTable.add(new SymbolTableEntry("EOF", "EOF"));
        symbolTable.add(new SymbolTableEntry("int", "INT"));

        // TODO: 05/07/17 fill table
    }

    public int check(String idName, String type) {
        boolean found = false;
        int index = -1;
        for (SymbolTableEntry ste : symbolTable) {
            if (ste.getName().equals(idName)) {
                found = true;
                index = symbolTable.indexOf(ste);
            }
        }

        if (found) {
            return index;
        } else {
            symbolTable.add(new SymbolTableEntry(idName, type));
            return symbolTable.size() - 1;
        }


    }

    public void getID() {

    }

    public int findAddr(String name){
        int address = -1;
        for (SymbolTableEntry ste : symbolTable) {
            if(name.equals(ste.getName())){
                address = ste.getAddress();
            }
        }
        return address;
    }


    public void print(){
        for (SymbolTableEntry ste: symbolTable) {
            System.out.println("ste = " + ste.print());
        }
    }
}
