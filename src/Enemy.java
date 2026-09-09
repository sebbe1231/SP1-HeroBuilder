import java.util.ArrayList;
import java.util.HashMap;

public class Enemy {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private double gold;
    private boolean isAlive;

    // I'm thinking inventory can be a HashMap, and then I can go with the structure:
    /*
    {
    "mainHand": {Item},
    "offHand": {Item},
    "potions": {Potion1, Potion2, ...},
    "Armor": {Armor},
    "other": {Item1, Item2, ...} For items not in use
    }
     */
    // This will make battle mechanics easier, since I don't have to cycle through an Array
    // HashMap values are a set value, so everything has to go in an Array, however this won't be a problem
    private HashMap<String, ArrayList<Item>> inventory = new HashMap<>();

    // Enemy object constructor
    public Enemy(String name, String type, int maxHealth, double gold) {

        // Adding inventory HashMap keys, making the HashMap "tree" in a way
        this.inventory.put("mainHand", new ArrayList<Item> ());
        this.inventory.put("offHand", new ArrayList<Item> ());
        this.inventory.put("potions", new ArrayList<Item> ());
        this.inventory.put("armor", new ArrayList<Item> ());
        this.inventory.put("other", new ArrayList<Item> ());

        this.name = name;
        this.type = type;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.isAlive = true;
    }

    public void printEnemySheet() {
        System.out.println("Name: " + this.name);
        System.out.println("Type: " + this.type);
        System.out.println("Health: " + this.health + "/" + this.maxHealth);
        System.out.println("Gold: " + this.gold);
        System.out.println("\n-----------------------------\n");

        // One line IF statement '((inventory.get("mainHand").isEmpty()) ? "" : inventory.get("mainHand").getFirst().getName())'
        // This line goes (condition) ? ifExpressionIsTrue : ifExpressionIsFalse
        // In this case I'm asking if the enemy has an mainHand/offHand item, if isEmpty() is true, i simply print nothing
        // If it's false, I print the main hand item name. This is done to avoid the index out of range error
        System.out.println("Main Hand: " +
                ((inventory.get("mainHand").isEmpty()) ? "" : inventory.get("mainHand").getFirst().getName()));
        System.out.println("Off Hand: " +
                ((inventory.get("offHand").isEmpty()) ? "" : inventory.get("offHand").getFirst().getName()));
        System.out.println("Armor: " +
                ((inventory.get("armor").isEmpty()) ? "" : inventory.get("armor").getFirst().getName()));
        System.out.println("Potions:");
        for (int i = 0; i < inventory.get("potions").size(); i++) {
            System.out.println("    " + i + ":" + inventory.get("potions").get(i).getName());
        }
        System.out.println("Other:");
        for (int i = 0; i < inventory.get("other").size(); i++){
            System.out.println("    " + i + ":" + inventory.get("other").get(i).getName());
        }
    }

    public String getType() {
        return type;
    }

    public void setMainHand(Item item) {
        // This function is to set the main hand ONLY
        // It's easier to make an individual one for each slot in the inventory, so its more readable and easier to handle
        inventory.get("mainHand").add(item);
    }

    public HashMap<String, ArrayList<Item>> getInventory() {
        return inventory;
    }

    public ArrayList<Item> getInventoryArray() {
        // Make array object for the inventory items
        ArrayList<Item> inventoryArray = new ArrayList<>();

        // For each loop, going through inventory keys (It's a HashMap)
        for (String key: this.inventory.keySet()) {

            // For each key, run a for loop to go through every item in that key
            for (int i = 0; i < this.inventory.get(key).size(); i++){

                // Add those items to inventory array
                inventoryArray.add(this.inventory.get(key).get(i));
            }
        }

        return inventoryArray;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int[] getHealth() {
        return new int[] {health, maxHealth};
    }

    public double getGold() {
        return this.gold;
    }

    public int setHealth(int newHealth) {
        health = newHealth;
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.isAlive = false;
        }
    }

    // Get enemy main hand, if main hand not equipped, return null
    public Item getMainHand() {
        if (!inventory.get("mainHand").isEmpty()) {
            return inventory.get("mainHand").getFirst();
        }
        else {
            return null;
        }
    }
}
