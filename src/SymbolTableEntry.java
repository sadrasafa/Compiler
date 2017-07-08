import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by aarash on 05/07/17.
 */
public class SymbolTableEntry {
    private String name;
    private int value;
    private int scope;
    private String type;
    private int address;
    private boolean function;
    private int functionAddressPB;
    private boolean intReturnType; //true : int , fasle : void
    private int returnValueAddress;
    private int returnAddr;
    private int limit = -1;
    private ArrayList<Integer> parameterAddresses = null;

    public int getReturnValueAddress() {
        return returnValueAddress;
    }

    public void setReturnValueAddress(int returnValueAddress) {
        this.returnValueAddress = returnValueAddress;
    }

    public boolean isIntReturnType() {
        return intReturnType;
    }

    public void setIntReturnType(boolean intReturnType) {
        this.intReturnType = intReturnType;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getReturnAddr() {
        return returnAddr;
    }

    public void setReturnAddr(int returnAddr) {
        this.returnAddr = returnAddr;
    }

    public int getFunctionAddressPB() {
        return functionAddressPB;
    }

    public void setFunctionAddressPB(int functionAddressPB) {
        this.functionAddressPB = functionAddressPB;
    }

    public void addParam(int address){
        parameterAddresses.add(address);
    }

    public ArrayList<Integer> getParameterAddresses(){
        return parameterAddresses;
    }

    public int getParamCount(){
        return parameterAddresses.size();
    }


    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public SymbolTableEntry(String name, String type){
        this.name = name;
        this.type = type;
        this.function = false;
    }

    public boolean isFunction() {
        return function;
    }

    public void setFunction(boolean function) {
        if(function){
            parameterAddresses = new ArrayList<>();
        }
        this.function = function;
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
        return "" + name + " " + type + " " + address + " " ;
    }
}
