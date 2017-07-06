/**
 * Created by safa on 7/4/17.
 */
public class ParseAction {
    public static final int SHIFT = 0;
    public static final int REDUCE = 1;
    public static final int GOTO = 2;
    public static final int ACC = 3;
    private int type;
    private int dest;

    public ParseAction(int type, int dest) {
        this.type = type;
        this.dest = dest;
    }
    public void printAction() {
        if (type==SHIFT) {
            System.out.print("SHIFT");
        }
        else if (type == REDUCE) {
            System.out.print("REDUCE");
        }
        else if (type==GOTO){
            System.out.print("GOTO");
        }
        else {
            System.out.print("ACC ");
        }
        System.out.print(" "+ dest);
    }
    public String getActionText() {
        String toReturn = "";
        if (type==SHIFT) {
            toReturn += "SHIFT";
        }
        else if (type == REDUCE) {
            toReturn += "REDUCE";
        }
        else if (type==GOTO){
            toReturn += "GOTO";
        }
        else {
            toReturn += "ACC";
        }
        toReturn += (" "+dest);
        return toReturn;
    }

    public int getType() {
        return type;
    }

    public int getDest() {
        return dest;
    }
}
