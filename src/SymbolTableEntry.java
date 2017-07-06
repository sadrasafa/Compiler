/**
 * Created by aarash on 05/07/17.
 */
public class SymbolTableEntry {
    private String name;
    private int value;
    private int scope;
    private String type;
    private int address;

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public SymbolTableEntry(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String print(){
        return "" + name + " " + type + " ";
    }
}
