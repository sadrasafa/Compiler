/**
 * Created by aarash on 06/07/17.
 */
public class Token {
    String type;
    int attr;

    public Token(String type, int attr) {
        this.type = type;
        this.attr = attr;
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
