public class Item {
    // This class is to make each item, such as weapons, armor, potions ect...
    // Since I have 1 class for all items, it means that items will often have unused data
    // This is fine however, I label all items as a itemType like "weapon", "armor" and so on

    private String name;
    private String itemType;
    private int damage;
    private int healing;
    private int durability;
    private int defence;
    private double value;

    // Item constructor
    public Item() {
        this.name = "Nan";
        this.itemType = "Nan";
        this.damage = 0;
        this.healing = 0;
        this.durability = 0;
        this.defence = 0;
        this.value = 0.0;
    }

    // Function to make a weapon type item
    public Item makeWeapon(String name, int damage, int healing, int durability, double value){
        this.name = name;
        this.itemType = "weapon";
        this.damage = damage;
        this.healing = healing;
        this.durability = durability;
        this.value = value;

        return this;
    }

    // Function to make a armor type item
    public Item makeArmor(String name, int durability, int defence, boolean shield, double value){
        this.name = name;
        if (shield){
            this.itemType = "shield";
        }
        else {
            this.itemType = "armor";
        }
        this.durability = durability;
        this.defence = defence;
        this.value = value;

        return this;
    }

    // Function to make a potion type item
    public Item makePotion(String name, int healing, int durability, double value) {
        this.name = name;
        this.itemType = "potion";
        this.durability = durability;
        this.healing = healing;
        this.value = value;

        return this;
    }

    public void printItemStats() {
        System.out.println("====| " + this.name + " |====");
        System.out.println("Type: " + this.itemType);
        System.out.println("Damage: " + this.damage);
        System.out.println("Healing: " +  this.healing);
        System.out.println("Defence: " + this.defence);
        System.out.println("Durability: " + this.durability);
        System.out.println("Value: " + this.value);
        System.out.println("===============");
        System.out.println();
    }

    // getName() is inherited by all subclasses
    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealing() {
        return healing;
    }

    public String getItemType() {
        return itemType;
    }

}
