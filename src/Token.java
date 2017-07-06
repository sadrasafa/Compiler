/**
 * Created by aarash on 06/07/17.
 */
public class Token {
    String type;
    int attr;
    Symbol symbol;

    public Token(String type, int attr, String symbolName) {
        this.type = type;
        this.attr = attr;
        symbol = new Symbol(symbolName, true);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String print(){
        return "<" + type + ", " + attr + ">";
    }

    public String getType() {
        return type;
    }

    public int getAttr() {
        return attr;
    }
}
