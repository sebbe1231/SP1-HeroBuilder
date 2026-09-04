public class Weapon extends Item {
    // Weapon class extends items with the inherited variable "name"
    // This is done to make inventory management easier
    // Inventory is stored as an Items object Array

    private int damage;
    private int healing;

    public Weapon(String name, int damage, int healing) {
        // Inherited variable from Items class
        super(name);
        this.damage = damage;
        this.healing = healing;
    }

    public int getDamage(){
        return damage;
    }

    public int getHealing() {
        return healing;
    }
}
