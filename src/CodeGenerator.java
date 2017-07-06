import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by aarash on 06/07/17.
 * Generates Code
 */
public class CodeGenerator {

    private Stack<Integer> ss;
    private ArrayList<Code> pb;
    private int temp;

    public CodeGenerator(){
        ss = new Stack<>();
        temp = 500;
    }


    public void generateCode(String action, String input){

        int top = ss.size() - 1;
        int t;
        switch (action){
            case "pid":
                int p;
                p = ScannerCompiler.symbolTable.findAddr(input);
                if(p == -1){
                    System.out.println("ERROR");
                }
                ss.push(p);

                break;
            case "add":

                t = gettemp();
                pb.add(new Code(giveCode("ADD", ss.get(top), ss.get(top - 1), t)));
                ss.pop();
                ss.pop();
                ss.push(t);

                break;
            case "mult":

                t = gettemp();
                pb.add(new Code(giveCode("MULT", ss.get(top), ss.get(top - 1), t)));
                ss.pop();
                ss.pop();
                ss.push(t);

                break;
            case "assign":

                pb.add(new Code(giveCode("ASSIGN", ss.get(top), ss.get(top - 1))));
                ss.pop();
                ss.pop();

                break;
            case "label":

                ss.push(pb.size());

                break;
            case "save":

                ss.push(pb.size());
                pb.add(new Code("NULL"));

                break;
            case "while":

                pb.get(ss.get(top)).setCode(giveCode("JPF", ss.get(top - 1), pb.size()));
                pb.add(new Code(giveCode("JP", ss.get(top - 2))));
                ss.pop();
                ss.pop();
                ss.pop();

                break;
            case "jpf_save":

                pb.get(ss.get(top)).setCode(giveCode("JPF", ss.get(top - 1), ss.size()));
                ss.pop();
                ss.pop();
                ss.push(pb.size());
                pb.add(new Code("NULL"));

                break;
            case "jp":

                pb.get(ss.get(top)).setCode(giveCode("JP", pb.size() - 1)); //i == pb.size() - 1
                ss.pop();

                break;
            case "jpf":

                pb.get(ss.get(top)).setCode(giveCode("JPF", ss.get(top - 1), pb.size() - 1));
                ss.pop();
                ss.pop();

                break;
            // TODO: 06/07/17 More actions
        }

    }

    private String giveCode(String op, int firstIn, int secondIn, int dest){
        return "(" + op + ", " + firstIn + ", " + secondIn + ", " + dest+ ")";
    }

    private String giveCode(String op, int firstIn, int dest){
        return "(" + op + ", " + firstIn + ", " + dest+ ")";
    }

    private String giveCode(String op, int dest){
        return "(" + op + ", " + dest+ ")";
    }

    private int gettemp(){
        int toReturn = temp;
        temp += 4;
        return temp;
    }

    public ArrayList<Code> getPb(){
        return pb;
    }

}
