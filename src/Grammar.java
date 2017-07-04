import java.time.temporal.Temporal;
import java.util.*;

/**
 * Created by safa on 7/4/17.
 */
public class Grammar {
    private ArrayList<Symbol> symbols;
    private ArrayList<Production> productions;
    private ArrayList<ItemSet> LRcollections;

    public static final Symbol symDollar = new Symbol("$", true);
    public static final Symbol symEps = new Symbol("epsilon", true);
    Symbol symS = new Symbol("S", false);
    public Grammar() {
        createSimpleGrammar();
    }

    public ArrayList<Symbol> getSymbols() {
        return symbols;
    }

    public ArrayList<Production> getProductions() {
        return productions;
    }

    public void createSimpleGrammar() {

        Symbol symE = new Symbol("E", false);
        Symbol symPlus = new Symbol("+", true);
        Symbol symT = new Symbol("T", false);
        Symbol symF = new Symbol("F", false);
        Symbol symMult = new Symbol("*", true);
        Symbol symOpenPar = new Symbol("(", true);
        Symbol symClosePar = new Symbol(")", true);
        Symbol symID = new Symbol("id", true);
        symbols = new ArrayList<>(Arrays.asList(symS, symE, symPlus, symT, symF, symMult, symOpenPar, symClosePar, symID, symDollar, symEps));

        Production p0 = new Production(symS, new Symbol[]{symE}, 0);
        Production p1 = new Production(symE, new Symbol[]{symE, symPlus, symT}, 1);
        Production p2 = new Production(symE, new Symbol[]{symT}, 2);
        Production p3 = new Production(symT, new Symbol[]{symT, symMult, symF}, 3);
        Production p4 = new Production(symT, new Symbol[]{symF}, 4);
        Production p5 = new Production(symF, new Symbol[]{symOpenPar, symE, symClosePar}, 5);
        Production p6 = new Production(symF, new Symbol[]{symID}, 6);

        productions = new ArrayList<>(Arrays.asList(p0, p1, p2, p3, p4, p5, p6));

        computeFirst();
        computeFollow();
    }
    public ItemSet getInitialItem() {
        return new ItemSet(closure(new Item(productions.get(0))) );
    }
    public ArrayList<Item> closure(Item item) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        return closure(items);
    }

    public ArrayList<Item> closure(ArrayList<Item> items) {
        ArrayList<Item> closure = new ArrayList<>();
        closure.addAll(items);
        boolean repeat;
        ListIterator<Item> itemsInClosure = closure.listIterator();
        while (itemsInClosure.hasNext()) {
            repeat = false;
            Item item = itemsInClosure.next();
            Symbol B = item.getAfterDot();
            if (B==null || B.isTerminal())
                continue;
            for (Production production : productions) {
                if (!production.getLHS().equals(B))
                    continue;
                Item newItem = new Item(production);
                if (!closure.contains(newItem)) {
                    itemsInClosure.add(newItem);
                    repeat = true;
                }
            }
            if (repeat) {
                itemsInClosure = closure.listIterator();
            }
        }

//        for (Item item: closure)
//            item.printItem();

        return closure;
    }

    public ArrayList<Item> goTo(ArrayList<Item> items, Symbol X) {
        ArrayList<Item> toReturn = new ArrayList<>();
        for (Item item: items) {
            int dotPlace = item.getDotPlace();
            if (X.equals(item.getAfterDot())) {
                Item newItem = new Item(item.getProduction(), dotPlace + 1);
                toReturn.addAll(closure(newItem));
            }
        }



        return toReturn;
    }
    public ArrayList<Item> goTo(Item item, Symbol X) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(item);
        return goTo(items, X);
    }


    public ArrayList<ItemSet> createLR() {
        int n = 0;
        LRcollections = new ArrayList<>();
        LRcollections.add(getInitialItem());
        LRcollections.get(0).setNumber(0);
        ListIterator<ItemSet> listIterator = LRcollections.listIterator();
        boolean repeat;
        while (listIterator.hasNext()) {
            repeat = false;
            ItemSet itemSet = listIterator.next();
            if (itemSet.isChecked())
                continue;
            for (Symbol X: getSymbols()) {
                ArrayList<Item> items = goTo(itemSet.getItems(), X);
                if (items.size()==0)
                    continue;
                ItemSet newItemSet = new ItemSet(items);

                if (!LRcollections.contains(newItemSet)) {
                    n++;
                    itemSet.addToMap(X, newItemSet);
                    LRcollections.add(newItemSet);
                    newItemSet.setNumber(n);
                    repeat = true;
                }
                else if (LRcollections.contains(newItemSet)) {
                    int k = LRcollections.indexOf(newItemSet);
                    newItemSet.setNumber(k);
                    itemSet.addToMap(X, newItemSet);
                }
            }
            itemSet.check();
            if (repeat) {
                listIterator = LRcollections.listIterator();
            }

        }


//        LRcollections.get(0).printItemSet();
        return LRcollections;
    }
    public ArrayList<Symbol> getTerminals() {
        ArrayList<Symbol> terminals = new ArrayList<>();
        for (Symbol s: symbols) {
            if (s.isTerminal())
                terminals.add(s);
        }
        return terminals;
    }
    public ArrayList<Symbol> getNonTerminals() {
        ArrayList<Symbol> nonTerminals = new ArrayList<>();
        for (Symbol s: symbols) {
            if (!s.isTerminal())
                nonTerminals.add(s);
        }
        return nonTerminals;
    }

    public void computeFirst() {
        for (Symbol symbol: symbols) {
            if (symbol.isFirstChecked())
                continue;
            first(symbol);
        }
//        for (Symbol symbol: symbols) {
//            System.out.println(symbol.getName()+":");
//            for (Symbol firstSymbol: symbol.getFirst()) {
//                System.out.print(firstSymbol.getName()+" ");
//            }
//            System.out.println();
//            System.out.println("____________________");
//        }
    }

    public void computeFollow() {
        for (Symbol symbol: symbols) {
            if (symbol.isFollowChecked())
                continue;
            follow(symbol);
        }
//        System.out.println("FOLLOWWWS:::");
//        for (Symbol symbol: getNonTerminals()) {
//            System.out.println(symbol.getName()+":");
//            for (Symbol followSymbol: symbol.getFollow()) {
//                System.out.print(followSymbol.getName()+" ");
//            }
//            System.out.println();
//            System.out.println("____________________");
//        }

    }

    private void first(Symbol A) {
        ArrayList<Symbol> toReturn = new ArrayList<>();
        if (A.isFirstChecked())
            return;
        A.setFirstChecked();
        if (A.isTerminal()) {
            toReturn.add(A);
        }
        else {

            for (Production production: productions) {
                if (!production.getLHS().equals(A))
                    continue;
                if (production.getRHS().length==0) {
                    toReturn.add(symEps);
                } else {
                    boolean hasEps = false;
                    int i;
                    for (i = 0; i < production.getRHS().length; i++) {
                        first(production.getRHS()[i]);
                        ArrayList<Symbol> temp = production.getRHS()[i].getFirst();
                                hasEps = false;
                        for (Symbol symbol: temp) {
                            if (symbol.equals(symEps))
                                hasEps=true;
                            else {
                                if (!toReturn.contains(symbol)) {
                                    toReturn.add(symbol);
                                }
                            }

                        }
                        if (!hasEps)
                            break;
                    }
                    if (i==production.getRHS().length && hasEps) {
                        toReturn.add(symEps);
                    }
                }
            }
        }
        A.setFirst(toReturn);
    }

    private void follow(Symbol A) {
        ArrayList<Symbol> toReturn = new ArrayList<>();
        if (A.isFollowChecked())
            return;
        A.setFollowChecked();
        if (A.equals(symS)) {
            A.addFollow(new ArrayList<>(Arrays.asList(symDollar)));
        }
        for (Production production: productions) {
            int i;
            for (i = 0; i < production.getRHS().length; i++) {
                Symbol Xi = production.getRHS()[i];
                if (Xi.isTerminal())
                    continue;
                if (i==production.getRHS().length) {
                    follow(production.getLHS());
                    ArrayList<Symbol> temp = production.getLHS().getFollow();
                    Xi.addFollow(temp);
                }
                else {
                    int j;
                    for (j = i+1; j < production.getRHS().length; j++) {
                        Symbol Xj = production.getRHS()[j];
                        Xi.addFollow(Xj.getFirst());
                        if (!Xj.getFirst().contains(symEps))
                            break;
                    }
                    if (j == production.getRHS().length) {
                        follow(production.getLHS());
                        ArrayList<Symbol> temp = production.getLHS().getFollow();
                        Xi.addFollow(temp);
                    }
                }

            }


        }


    }


    public ArrayList<HashMap<Symbol, ParseAction>> createParseTable() {

        ArrayList<ItemSet> states = createLR();
        int size = states.size();
        ArrayList<HashMap<Symbol, ParseAction>> parseTable = new ArrayList<>(size);
        for (ItemSet itemSet: states) {
            HashMap<Symbol, ParseAction> row = new HashMap<>();
            for (Item item: itemSet.getItems()) {
                Symbol a = item.getAfterDot();
                if (a==null) {
                    if (item.getProduction().getLHS().getName().equals("S")) {
                        ParseAction action = new ParseAction(ParseAction.ACC, -1);
                        row.put(Grammar.symDollar, action);
                    }
                    else {
                        
                        int productionNo = item.getProduction().getNumber();
                        for (Symbol z:   item.getProduction().getLHS().getFollow() ) {
                            ParseAction action = new ParseAction(ParseAction.REDUCE, productionNo);
                            row.put(z, action);
                        }
                    }
                }
                else {
                    if (!a.isTerminal())
                        continue;
                    ItemSet J = itemSet.getMap().get(a);
                    if (J==null)
                        continue;
                    int j = J.getNumber();
                    ParseAction action = new ParseAction(ParseAction.SHIFT, j);
                    row.put(a, action);
                }
                for (Symbol X: getNonTerminals()) {
                    ItemSet J = itemSet.getMap().get(X);
                    if (J==null)
                        continue;
                    int j = J.getNumber();
                    ParseAction action = new ParseAction(ParseAction.GOTO, j);
                    row.put(X, action);
                }

            }
            parseTable.add(row);
        }

        int n = 0;
        for (HashMap<Symbol, ParseAction> row: parseTable) {
            System.out.println(n++);
            for (Map.Entry<Symbol, ParseAction> pair: row.entrySet()) {
                System.out.print(pair.getKey().getName()+" ");
                pair.getValue().printAction();
                System.out.print(" , ");
            }
            System.out.println();
            System.out.println("_______________");

        }


        return parseTable;
    }


}
