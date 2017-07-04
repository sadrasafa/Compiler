/**
 * Created by safa on 7/4/17.
 */
public class Item {
    private Production production;
    private int dotPlace;

    public Item(Production production) {
        this.production = production;
        this.dotPlace = 0;
    }
    public Item(Production production, int dotPlace) {
        this.production = production;
        this.dotPlace = dotPlace;
    }
    public int getDotPlace() {
        return dotPlace;
    }

    public Production getProduction() {
        return production;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (dotPlace != item.dotPlace) return false;
        return production != null ? production.equals(item.production) : item.production == null;
    }

    @Override
    public int hashCode() {
        int result = production != null ? production.hashCode() : 0;
        result = 31 * result + dotPlace;
        return result;
    }


    public void printItem() {
        System.out.print(production.getLHS().getName()+" -> ");
        int i;
        for (i = 0; i < dotPlace; i++) {
            System.out.print(production.getRHS()[i].getName() + " ");
        }
        System.out.print(". ");
        for (; i < production.getRHS().length; i++) {
            System.out.print(production.getRHS()[i].getName()+ " ");
        }
        System.out.println();

    }

    public Symbol getAfterDot() {
        Symbol s;
        if (dotPlace == production.getRHS().length) {
            return null;
        }
        return production.getRHS()[dotPlace];
    }

}
