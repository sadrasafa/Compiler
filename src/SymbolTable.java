import java.util.HashMap;

/**
 * Created by aarash on 05/07/17.
 */
public class SymbolTable {
    HashMap<String, SymbolTableEntry> st;

    public SymbolTable(){
        st = new HashMap<>();

//        st.put("if", new SymbolTableEntry("if", "IF"))

    }
}
