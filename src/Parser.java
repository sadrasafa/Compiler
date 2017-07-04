import com.sun.org.apache.bcel.internal.generic.GOTO;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Created by safa on 7/3/17.
 */
public class Parser {

    Grammar grammar;
    ArrayList<ItemSet> states;
    ArrayList<HashMap<Symbol, ParseAction>> parseTable;
    public Parser() {
        grammar = new Grammar();
        parseTable = grammar.createParseTable();
    }




}
