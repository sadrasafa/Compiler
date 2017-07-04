import java.util.ArrayList;

/**
 * Created by safa on 7/4/17.
 */
public class Symbol {
    private String name;
    private boolean terminal;
    private boolean firstChecked;
    private boolean followChecked;

    private ArrayList<Symbol> first;
    private ArrayList<Symbol> follow;
    public Symbol(String name, boolean terminal) {
        this.name = name;
        this.terminal = terminal;
        firstChecked = false;
        followChecked = false;
        first = new ArrayList<>();
        follow = new ArrayList<>();
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
