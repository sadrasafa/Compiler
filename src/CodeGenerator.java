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
    private int jumpAddr = -1;
    private int funcIndex;
    public static Stack<Integer> scopeStack;


    public CodeGenerator() {
        ss = new Stack<>();
        st = ScannerCompiler.symbolTable;
        functionStack = new Stack<>();
        returnAddr = new Stack<>();
        scopeStack = new Stack<>();
        scopeStack.push(8);
        pb = new ArrayList<>();
        temp = 500;
        address = 100;

        pb.add(new Code("NULL"));
    }


    public Stack<Integer> getScopeStack() {
        return scopeStack;
    }

    public void generateCode(String action, Token lastToken) {

        System.out.println("***************************************************************************************");
        System.out.println("action = " + action);
        System.out.println("lastToken = " + lastToken.print());


        int top = ss.size() - 1;
        int t, t1, t2, t3;
        int num;
//        int jumpAddr = -1;
        action = action.substring(1);
        SymbolTableEntry ste, ste2, ste3;
        switch (action) {
            case "incScope":
                if(ss.peek().startsWith("!")) {
                    int paramCnt = Integer.parseInt(ss.pop().substring(1));
                    scopeStack.push(st.getSize() - paramCnt);
                } else {
                    scopeStack.push(st.getSize());
                }
                break;
            case "decScope":
                int toRemove = scopeStack.pop();
                int size = scopeStack.size();
                st.remove(toRemove);
                break;
            case "pid":
                int p;
                p = ScannerCompiler.symbolTable.getEntry(lastToken.getAttr()).getAddress();
                if (p == -1) {
                    System.out.println("ERROR");
                }
                ss.push("" + p);

                break;
            case "aid":
                String stringNumber;
                int arrIndex;
                if (ss.get(top).contains("#")) {
                    stringNumber = ss.get(top).substring(1);
                    arrIndex = Integer.parseInt(stringNumber);

//                    if(arrIndex > )


                }

                p = Integer.parseInt(ss.get(top - 1)) + (Integer.parseInt(ss.get(top)) * 4); //base + displacement

                ss.pop();
                ss.push("" + p);

                break;
            case "addOrSub":

                t = gettemp();

                if (ss.get(top - 1).equals("11111"))
                    pb.add(new Code(giveCode("ADD", ss.get(top), ss.get(top - 2), "" + t)));
                else if (ss.get(top - 1).equals("22222"))
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
                else if (ss.get(top - 1).equals("44444"))
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
                System.out.println("pb.size() = " + pb.size());
                pb.add(new Code("NULL"));

                break;
            case "while":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", (ss.get(top - 1)), "" + (pb.size() + 1)));
                pb.add(new Code(giveCode("JP", (ss.get(top - 2)))));
                ss.pop();
                ss.pop();
                ss.pop();

                break;
            case "jpf_save":

                printSS();
                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", ss.get(top - 1), "" + (pb.size() + 1)));
                ss.pop();
                ss.pop();
                ss.push("" + pb.size());
                pb.add(new Code("NULL"));

                break;
            case "jp":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JP", "" + (pb.size()))); //i == pb.size() - 1
                ss.pop();

                break;
            case "jpf":

                pb.get(Integer.parseInt(ss.get(top))).setCode(giveCode("JPF", ss.get(top - 1), "" + (pb.size())));
                ss.pop();
                ss.pop();

                break;
            case "defVar":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
                if (ss.get(top - 1).equals("000")) {
                    System.out.println("ERROR!!!!");
                    return; // TODO: 07/07/17 return false
                }
//                ste = st.findWithID(input);
                ste.setAddress(getAddress());
                ss.pop();
                ss.pop();
                break;
            case "defVarForFunc":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
//                ste = st.findWithID(input);
                if (ss.get(top - 1).equals("000")) {
                    System.out.println("ERROR!!!!");
                    return; // TODO: 07/07/17 return false
                }


                ste.setAddress(getAddress());

                ste2 = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top - 2))); //function's entry
                ste2.addParam(ste.getAddress());
                ste2.addParamType("var");
                ss.pop();
                ss.pop();
                break;
            case "defArrForFunc":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
//                ste = st.findWithID(input);
                if (ss.get(top - 1).equals("000")) {
                    System.out.println("ERROR!!!!");
                    return; // TODO: 07/07/17 return false
                }


                ste.setAddress(getAddress());

                ste2 = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top - 2))); //function's entry
                ste2.addParam(ste.getAddress());
                ste2.addParamType("arr");
                ss.pop();
                ss.pop();
                break;
            case "defArr":
                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top - 1)));
                num = Integer.parseInt(ss.get(top).substring(1));

                if (ss.get(top - 2).equals("000")) {
                    System.out.println("ERROR!!!!");
                    return; // TODO: 07/07/17 return false
                }

                ste.setLimit(num - 1);
                ste.setAddress(getAddress());
                for (int i = 0; i < num - 1; i++) {
                    getAddress();
                }
                ss.pop();
                ss.pop();
                ss.pop();
                break;
            case "defFunc":

                ste = ScannerCompiler.symbolTable.getEntry(Integer.parseInt(ss.get(top)));
//                scopeStack.push(Integer.parseInt(ss.get(top)));
//                pb.add(new Code(giveCode("JP", "@" + ste.getReturnAddr())));

                if (pb.get(0).getCode().equals("NULL") && ste.getName().equals("main")) {
                    pb.get(0).setCode(giveCode("JP", "" + pb.size()));
                }

                if (ss.get(top - 1).equals("000")) {

                    ste.setIntReturnType(false);

                } else if (ss.get(top - 1).equals("321")) {

                    ste.setIntReturnType(true);
                    ste.setReturnValueAddress(getAddress());

                }
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

                ste = st.getEntry(Integer.parseInt(ss.get(top)));

                ste.setFunctionAddressPB(pb.size());

                ste.setReturnAddr(getAddress());

                ss.push("!" + ste.getParamCount());


                System.out.println("SORRY");


//                ss.pop();
                break;

            case "returnToMain":

                ste = st.getEntry(Integer.parseInt(ss.get(top)));
                pb.add(new Code(giveCode("JP", "@" + ste.getReturnAddr())));
                ss.pop();
                ss.pop();

                break;
            case "pushAddrForJump":

                int temp2 = pb.size();
                ste = ScannerCompiler.symbolTable.getEntry(lastToken.getAttr());
                funcIndex = lastToken.getAttr();
                jumpAddr = ste.getFunctionAddressPB();

                pb.add(new Code(giveCode("ASSIGN", "" + (temp2 + ste.getParamCount() + 2), "" + ste.getReturnAddr())));


                break;
            case "call":

                ste = st.getEntry(funcIndex);

                for (int i = 0; i < ste.getParamCount(); i++) {

                    if (ste.getParamType(i).equals("var")) {
                        pb.add(new Code(giveCode("ASSIGN", ss.pop(), "" + ste.getParameterAddresses().get(i))));
                        System.out.println("THAT'S " + i);
                    } else if (ste.getParamType(i).equals("arr")) {
                        pb.add(new Code(giveCode("ASSIGN", "#" + ss.pop(), "" + ste.getParameterAddresses().get(i))));
                        System.out.println("THAT'S " + i);
                    }
                }

                t2 = gettemp();
//                ss.push("" + ste.getReturnValueAddress());

                pb.add(new Code(giveCode("JP", "" + jumpAddr)));
                pb.add(new Code(giveCode("ASSIGN", "" + ste.getReturnValueAddress(), "" + t2)));

                ss.push("" + t2);

//                returnAddr.push(pb.size());


                break;
            case "return":
//                t2 = gettemp();
                ste = st.getEntry(Integer.parseInt(ss.get(top - 1)));
                pb.add(new Code(giveCode("ASSIGN", ss.get(top), "" + ste.getReturnValueAddress())));
//                pb.add(new Code(giveCode("ASSIGN", "" + ste.getReturnValueAddress() , "" + t2)));
                ss.pop();
                break;

            case "pushInt":
//                System.out.println("FUCK");
                ss.push("321");
                break;
            case "pushVoid":
                ss.push("000");
                break;
            case "output":
                pb.add(new Code(giveCode("PRINT", ss.pop())));
                break;
            // TODO: 06/07/17 More actions
        }

        System.out.println("SCOPE STACK: ");
        for (int i = 0; i < scopeStack.size(); i++) {
            System.out.println("scopeStack " + i + " = " + scopeStack.get(i));
        }

        ScannerCompiler.symbolTable.print();

        System.out.println("SS: ");
        for (int i = 0; i < ss.size(); i++) {
            System.out.println("ss.get(" + i + ") = " + ss.get(i));
        }

        System.out.println("PB till now:---------------------------");
        for (int i = 0; i < pb.size(); i++) {
            System.out.println("" + i + ". " + pb.get(i).getCode());
        }
        System.out.println("---------------------------------");

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

    private void printSS() {
        System.out.println("SS: ");
        for (int i = 0; i < ss.size(); i++) {
            System.out.println("ss.get(" + i + ") = " + ss.get(i));
        }
    }

    public ArrayList<Code> getPb() {
        return pb;
    }

}
