import com.sun.org.apache.bcel.internal.generic.GOTO;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.io.IOException;
import java.util.*;

/**
 * Created by safa on 7/3/17.
 */
public class Parser {



    private Grammar grammar;
    private ScannerCompiler scanner;
    private ArrayList<HashMap<Symbol, ParseAction>> parseTable;
    private Stack<Integer> stack;
    private CodeGenerator codeGenerator;
    public Parser(String filePath) throws IOException{
        grammar = new Grammar();
        parseTable = grammar.createParseTable();
        scanner = new ScannerCompiler(filePath);
        codeGenerator = new CodeGenerator();
        stack = new Stack<>();
        parse();

    }

    private void parse() {
        stack.push(0);
        Integer top;
        Token tkn = scanner.getToken();
        Token lastTkn = null;
        Symbol token = tkn.getSymbol(); //token = a
        ParseAction action;
        System.out.println("PARSING STARTED");
        while (true) {
            System.out.println("_______________");
            System.out.println("STACK: "+stack + "TOKEN: "+token.getName());
            top = stack.peek();
            action = parseTable.get(top).get(token);
            if (action.getType() == ParseAction.SHIFT) {
                Integer t = action.getDest();
                stack.push(t);
                lastTkn = tkn;
                tkn = scanner.getToken();
                token = tkn.getSymbol();
                System.out.println("PUSHED "+t);
            }
            else if (action.getType() == ParseAction.REDUCE) {
                int productionNumber = action.getDest();
                Production production = grammar.getProductions().get(productionNumber);
                int RHSsize = production.getRHS().length;
                for (int i = 0; i < RHSsize; i++) {
                    stack.pop();
                }
                top = stack.peek();
                Symbol A = production.getLHS();
                ParseAction goTo = parseTable.get(top).get(A);
                if (goTo==null) {
                    System.out.println(A.getName()+" **************");
                }
                Integer t =  goTo.getDest(); //todo aya goTo si olmasa neyniyak? ya hammasha olar?
                stack.push(t);
                System.out.print("REDUCE : ");
                production.printProduction();
                if (A.isActionSymbol()) {
//                    codeGenerator.generateCode(A.getName(), lastTkn);
                }
            }
            else if (action.getType() == ParseAction.ACC) {
                System.out.println("_________________");
                System.out.println("PARSING IS DONE");
                break;
            }
            else  {
                //todo error recovery
            }
        }

        System.out.println("codeGenerator = " + codeGenerator.getPb().size());

        ScannerCompiler.symbolTable.print();

        for (int i = 0; i < codeGenerator.getPb().size(); i++) {
            System.out.println("" + i  + " : " + codeGenerator.getPb().get(i));
        }


    }




}
