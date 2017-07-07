import java.util.ArrayList;

/**
 * Created by safa on 7/4/17.
 */
public class Symbol {
    static int generalPox = '@';
    static int terminalPox = 'a';
    int pox;
    private String name;
    private boolean terminal;
    private boolean actionSymbol;
    private boolean firstChecked;
    private boolean followChecked;

    private ArrayList<Symbol> first;
    private ArrayList<Symbol> follow;
    public Symbol(String name, boolean terminal) {
        this.name = name;
        this.terminal = terminal;
        actionSymbol = false;
        firstChecked = false;
        followChecked = false;
        first = new ArrayList<>();
        follow = new ArrayList<>();
        if(!terminal) {
            pox = generalPox++;
            if (pox ==91) {
                pox = '@';
            }
            else if (pox == 92) {
                pox = '#';
            }
            else if (pox == 93) {
                pox = '$';
            }
            else if (pox == 94) {
                pox = '%';
            }
            else if (pox == 95) {
                pox = '^';
            }
            else if (pox == 96) {
                pox = '&';
            }
            else if (pox == 97) {
                pox = '_';
            }
        }
        else
            pox = terminalPox++;
    }

    public Symbol(String name) {
        this.name = name;
        this.terminal = false;
        this.actionSymbol = true;
        first = new ArrayList<>();
        follow = new ArrayList<>();
    }

    public boolean isActionSymbol() {
        return actionSymbol;
    }

    public String getPox() {
        String s = "";

        return s+=(char)pox;
    }

    public boolean isFirstChecked() {
        return firstChecked;
    }

    public void setFirstChecked() {
        this.firstChecked = true;
    }

    public boolean isFollowChecked() {
        return followChecked;
    }

    public void setFollowChecked() {
        this.followChecked = true;
    }

    public ArrayList<Symbol> getFirst() {
        return first;
    }

    public void setFollow(ArrayList<Symbol> follow) {
        this.follow = follow;
    }

    public void setFirst(ArrayList<Symbol> first) {
        this.first = first;
    }

    public ArrayList<Symbol> getFollow() {
        return follow;
    }

    public void addFollow(ArrayList<Symbol> f) {
        for (Symbol symbol: f)
            if (!follow.contains(symbol))
                follow.add(symbol);
    }

    public boolean isTerminal() {
        return terminal;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        return name != null ? name.equals(symbol.name) : symbol.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
