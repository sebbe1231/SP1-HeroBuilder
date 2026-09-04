public class Potion extends Item {
    // Potion class extends items with the inherited variable "name"
    // This is done to make inventory management easier
    // Inventory is stored as an Items object Array

    private int healing;

    public Potion(String name, int healing) {
        super(name);
        this.healing = healing;
    }

    public int getHealing() {
        return healing;
    }
}
