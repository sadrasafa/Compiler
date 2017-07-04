import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by safa on 7/4/17.
 */
public class ItemSet {
    private ArrayList<Item> items;
    private HashMap<Symbol, ItemSet> map;
    private boolean checked;
    private int number;
    public ItemSet(ArrayList<Item> items) {
        this.items = items;
        map = new HashMap<>();
        checked = false;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addToMap(Symbol X, ItemSet I) {
        map.put(X, I);
    }
    public boolean isChecked() {
        return checked;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void check() {
        checked = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemSet itemSet = (ItemSet) o;

        return items != null ? items.equals(itemSet.items) : itemSet.items == null;
    }

    @Override
    public int hashCode() {
        return items != null ? items.hashCode() : 0;
    }


    public void printItemSet() {
        System.out.println("TO " + number);
        for (Item item: items) {
            item.printItem();
        }
        for (Map.Entry<Symbol, ItemSet> pair: map.entrySet()) {
            System.out.println("FROM "+number);
            System.out.println("__________________");
            System.out.println(pair.getKey().getName());
            pair.getValue().printItemSet();
        }
    }

    public HashMap<Symbol, ItemSet> getMap() {
        return map;
    }
}
