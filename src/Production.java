import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by safa on 7/4/17.
 */
public class Production {
    private Symbol LHS;
    private Symbol[] RHS;
    int number;
    static int No = 0;

    public Production(Symbol LHS, Symbol[] RHS, int number) {
        this.LHS = LHS;
        this.RHS = RHS;
        this.number = number;
    }
    public Production(Symbol LHS, Symbol[] RHS) {
        this.LHS = LHS;
        this.RHS = RHS;
        this.number = No++;
    }
    public Production(Symbol LHS, int number) {
        this.LHS = LHS;
        RHS = new Symbol[0];
        this.number = number;
    }
    public Production(Symbol LHS) {
        this.LHS = LHS;
        RHS = new Symbol[0];
        this.number = No++;
    }
    public void printProduction() {
        System.out.print(getNumber()+": " +getLHS().getName()+" -> ");
        for (int i = 0; i < getRHS().length; i++) {
            System.out.print(getRHS()[i].getName()+" ");
        }
        System.out.println();
    }

    public Symbol getLHS() {
        return LHS;
    }

    public int getNumber() {
        return number;
    }

    public Symbol[] getRHS() {
        return RHS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Production that = (Production) o;

        if (LHS != null ? !LHS.equals(that.LHS) : that.LHS != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(RHS, that.RHS);
    }

    @Override
    public int hashCode() {
        int result = LHS != null ? LHS.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(RHS);
        return result;
    }
}
