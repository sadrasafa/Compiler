import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by aarash on 06/07/17.
 * Generates Code
 */
public class CodeGenerator {

    private Stack<String> ss;
    private ArrayList<Code> pb;
    private SymbolTable st;
    private Stack<String> functionStack;
    private Stack<Integer> returnAddr;
    private int temp, address;

    public CodeGenerator() {
        ss = new Stack<>();
        st = ScannerCompiler.symbolTable;
        functionStack = new Stack<>();
        returnAddr = new Stack<>();
        temp = 500;
        address = 100;
    }

    public void generateCode(String action, Token lastToken) {

        int top = ss.size() - 1;
        int t, t1, t2, t3;
        int num;
        int jumpAddr = -1;
        SymbolTableEntry ste, ste2, ste3;
        switch (action) {
            case "pid":
                int p;
                p = ScannerCompiler.symbolTable.getEntry(lastToken.getAttr()).getAddress();
                if (p == -1) {
                    System.out.println("ERROR");
                }
                ss.push("" + p);

                break;
            case "aid":

                p = Integer.parseInt(ss.get(top - 1)) + (Integer.parseInt(ss.get(top))* 4); //base + displacement

                ss.pop();
                ss.push("" + p);

                break;
            case "addOrSub":

                t = gettemp();

                if (ss.get(top - 1).equals("11111"))
                    pb.add(new Code(giveCode("ADD", ss.get(top), ss.get(top - 2), "" + t)));
                else if(ss.get(top - 1).equals("22222"))
                    pb.add(new Code(giveCode("SUB", ss.get(top), ss.get(top - 2), "" + t)));
                ss.pop();
                ss.pop();
                ss.pop();
                ss.push("" + t);

                break;
            case "multOrDiv":

                t = gettemp();
                if (ss.get(top - 1).equals("33333"))
                    pb.add(new Code(giveCode("MULT", ss.get(top), ss.get(top - 2), "" + t)));
                else if(ss.get(top - 1).equals("44444"))
                    pb.add(new Code(giveCode("DIV", ss.get(top), ss.get(top - 2), "" + t)));
                ss.pop();
                ss.pop();
                ss.pop();
                ss.push("" + t);

                break;
            case "assign":

                pb.add(new Code(giveCode("ASSIGN", ss.get(top), ss.get(top - 1))));
                ss.pop();
                ss.pop();

                break;
            case "label":

                ss.push("" + pb.size());

                break;
            case "save":

                ss.push("" + pb.size());
                pb.add(new Code("NULL"));

                break;
            case "while":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", (ss.get(top - 1)), "" + pb.size()));
                pb.add(new Code(giveCode("JP", (ss.get(top - 2)))));
                ss.pop();
                ss.pop();
                ss.pop();

                break;
            case "jpf_save":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", ss.get(top - 1), "" + ss.size()));
                ss.pop();
                ss.pop();
                ss.push("" + pb.size());
                pb.add(new Code("NULL"));

                break;
            case "jp":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JP", "" + (pb.size() - 1))); //i == pb.size() - 1
                ss.pop();

                break;
            case "jpf":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", ss.get(top - 1), "" + (pb.size() - 1)));
                ss.pop();
                ss.pop();

                break;
            case "defVar":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
//                ste = st.findWithID(input);
                ste.setAddress(getAddress());
                ss.pop();
                break;
            case "defVarForFunc":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
//                ste = st.findWithID(input);
                ste.setAddress(getAddress());

                ste2 = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top - 1))); //function's entry
                ste2.addParam(ste.getAddress());
                ss.pop();
                break;
            case "defArrForFunc":
                // TODO: 07/07/17 FILL THIS SHIT UP!
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top - 1)));
                num = Integer.parseInt(ss.get(top));
                ste.setAddress(getAddress());
                for (int i = 0; i < num - 1; i++) {
                    getAddress();
                }
                ss.pop();
                ss.pop();
                break;
            case "defFunc":

                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
                ste.setFunction(true);


                break;
            case "pushID":
                ss.push("" + lastToken.getAttr());
                break;
            case "pushNUM":
                ss.push("#" + lastToken.getAttr());
                break;
            case "equals":
                t = gettemp();
                pb.add(new Code(giveCode("EQ", ss.get(top), ss.get(top - 1), "" + t)));
                ss.pop();
                ss.pop();
                ss.push("" + t);
                break;
            case "lessthan":
                t = gettemp();
                pb.add(new Code(giveCode("LT", ss.get(top), ss.get(top - 1), "" + t)));
                ss.pop();
                ss.pop();
                ss.push("" + t);

                break;
            case "pushAdd":
                ss.push("" + 11111);
                break;
            case "pushSub":
                ss.push("" + 22222);
                break;
            case "pushMult":
                ss.push("" + 33333);
                break;
            case "pushDiv":
                ss.push("" + 44444);
                break;
            case "and":
                t1 = gettemp();
                t2 = gettemp();
                t3 = gettemp();

                pb.add(new Code(giveCode("EQ", "#1", ss.get(top), "" + t1)));
                pb.add(new Code(giveCode("EQ", "#1", ss.get(top - 1), "" + t2)));
                pb.add(new Code(giveCode("EQ", "" + t1, "" + t2, "" + t3)));

                ss.pop();
                ss.pop();
                ss.push("" + t3);

                break;

            case "pop":
                ss.pop();
                break;
            case "assignPars":

                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));

                ste.setFunctionAddressPB(pb.size());

                for (int i = 0; i < ste.getParamCount(); i++) {

                    pb.add(new Code(giveCode("ASSIGN", ss.pop(), "" + ste.getParameterAddresses().get(i))));

                }

                ss.pop();
                break;
            case "pushAddrForJump":

                int temp2 = pb.size();
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));

                jumpAddr = ste.getFunctionAddressPB();

                ste.setReturnAddr(getAddress());


                pb.add(new Code(giveCode("ASSIGN", "" + (temp2 + 2), "" + ste.getReturnAddr())));


                break;
            case "call":

                pb.add(new Code(giveCode("JP", "" + jumpAddr)));



//                returnAddr.push(pb.size());




                break;
            case "return":
                // TODO: 07/07/17
                break;
                // TODO: 06/07/17 More actions
        }

    }

    private String giveCode(String op, String firstIn, String secondIn, String dest) {
        return "(" + op + ", " + firstIn + ", " + secondIn + ", " + dest + ")";
    }

    private String giveCode(String op, String firstIn, String dest) {
        return "(" + op + ", " + firstIn + ", " + dest + ")";
    }

    private String giveCode(String op, String dest) {
        return "(" + op + ", " + dest + ")";
    }

    private int gettemp() {
        int toReturn = temp;
        temp += 4;
        return toReturn;
    }

    private int getAddress() {
        int toReturn = address;
        address += 4;
        return toReturn;
    }

    public ArrayList<Code> getPb() {
        return pb;
    }

}
