public class Armor extends Item {
    // Potion class extends items with the inherited variable "name"
    // This is done to make inventory management easier
    // Inventory is stored as an Items object Array

    private int defence;

    public Armor(String name, int defence) {
        // Inherited variable from Item class
        super(name);
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }
}
