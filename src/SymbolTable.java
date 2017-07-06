import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aarash on 05/07/17.
 */
public class SymbolTable {

    ArrayList<SymbolTableEntry> st = new ArrayList<>();

    public SymbolTable() {
        st = new ArrayList<>();

        st.add(new SymbolTableEntry("if", "IF"));
        st.add(new SymbolTableEntry("while", "WHILE"));
        st.add(new SymbolTableEntry("else", "ELSE"));
        st.add(new SymbolTableEntry("output", "ID"));
        st.add(new SymbolTableEntry("return", "RETURN"));
        st.add(new SymbolTableEntry("EOF", "EOF"));
        st.add(new SymbolTableEntry("int", "INT"));

        // TODO: 05/07/17 fill table
    }

    public int check(String idName, String type) {
        boolean found = false;
        int index = -1;
        for (SymbolTableEntry ste : st) {
            if (ste.getName().equals(idName)) {
                found = true;
                index = st.indexOf(ste);
            }
        }

        if (found) {
            return index;
        } else {
            st.add(new SymbolTableEntry(idName, type));
            return st.size() - 1;
        }


    }

    public void getID() {

    }


    public void print(){
        for (SymbolTableEntry ste: st) {
            System.out.println("ste = " + ste.print());
        }
    }
}
