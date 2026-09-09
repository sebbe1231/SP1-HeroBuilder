import java.util.*;

public class Hero {
    // This is the Hero class. This is where all the hero logic is run

    private String name;
    private int health;
    private int maxHealth;
    private int level;
    private int xp;
    private double gold;
    private boolean isAlive;
    private char classType;

    private Scanner input = new Scanner(System.in);

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

    // Hero object constructor
    public Hero(String name, char classType){

        // Adding inventory HashMap keys, making the HashMap "tree" in a way
        this.inventory.put("mainHand", new ArrayList<Item> ());
        this.inventory.put("offHand", new ArrayList<Item> ());
        this.inventory.put("potions", new ArrayList<Item> ());
        this.inventory.put("armor", new ArrayList<Item> ());
        this.inventory.put("other", new ArrayList<Item> ());

        this.name = name;
        this.classType = classType;

        // Adding inventory items based on chosen class
        if(classType == 'w') {
            makeWarrior();
        } else if (classType == 'r') {
            makeRogue();
        } else if (classType == 'm') {
            makeMage();
        }
        this.level = 0;
        this.xp = 0;
        this.gold = 20.0;
        this.isAlive = true;
    }

    public void makeWarrior() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Sword", 20, 0, 10, 15)
        );
        this.inventory.get("offHand").add(
                new Item().makeArmor("Shield", 10, 10, true, 30)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Heavy Armor", 15, 10, false, 30)
        );

        this.health = 150;
        this.maxHealth = 150;
    }

    public void makeRogue() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Dagger", 20, 0, 10, 15)
        );
        this.inventory.get("offHand").add(
                new Item().makeWeapon("Dagger", 20, 0, 10, 15)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Light Armor", 10, 5, false, 20)
        );

        this.health = 100;
        this.maxHealth = 100;
    }

    public void makeMage() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Staff", 15, 25, 10, 50)
        );
        this.inventory.get("potions").add(
                new Item().makePotion("Health Potion", 25, 1, 20)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Magical Robe", 10, 2, false, 50)
        );

        this.health = 80;
        this.maxHealth = 80;
    }

    // Function to print character sheet
    public void printCharacterSheet(){
        System.out.println("Name: " + name);
        System.out.println("HP: " + health + "/" + maxHealth);
        System.out.println("Level: " + level);
        System.out.println("XP: " + xp);
        System.out.println("Gold: " + gold);
        System.out.println("Class: " + classType);
        System.out.println("\n----------------------\n");

        // One line IF statement '((inventory.get("mainHand").isEmpty()) ? "" : inventory.get("mainHand").getFirst().getName())'
        // This line goes (condition) ? ifExpressionIsTrue : ifExpressionIsFalse
        // In this case I'm asking if the hero has an mainHand/offHand item, if isEmpty() is true, i simply print nothing
        // If it's false, I print the main hand item name. This is done to avoid the index out of range error
        System.out.println("Main Hand: " +
                ((inventory.get("mainHand").isEmpty()) ? "" : inventory.get("mainHand").getFirst().getName()));
        System.out.println("Off Hand: " +
                ((inventory.get("offHand").isEmpty()) ? "" : inventory.get("offHand").getFirst().getName()));
        System.out.println("Armor: " +
                ((inventory.get("armor").isEmpty()) ? "" : inventory.get("armor").getFirst().getName()));
        System.out.println("Potions:");
        for (int i = 0; i < inventory.get("potions").size(); i++) {
            System.out.println("    " + (i+1) + ":" + inventory.get("potions").get(i).getName());
        }
        System.out.println("Other:");
        for (int i = 0; i < inventory.get("other").size(); i++){
            System.out.println("    " + (i+1) + ":" + inventory.get("other").get(i).getName());
        }
        System.out.println("----| " + this.getInventoryArray().size() + " total items |----");
        System.out.println();
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
        // I return health as a Health Array, simply to get both values in one call, so I can avoid making a new function
        return new int[] {health, maxHealth};
    }

    public int setHealth(int newHealth) {
        health = newHealth;
        if (health <= 0) {
            this.isAlive = false;
        }
        return health;
    }

    public String getName(){
        return name;
    }

    // Make hero take damage
    public int takeDamage(int damage, boolean blocking) {

        // If hero is blocking, take damage equal to damage - defense
        if (blocking) {
            // Only take damage if damage is bigger than 0
            if (damage - this.getOffHand().getDefence() > 0){

                // Make damage equal to damage - defense
                damage -= this.getOffHand().getDefence();
                this.health -= damage;
            }
        }
        else {
            this.health -= damage;
        }

        if (this.health <= 0) {
            this.isAlive = false;
        }

        // Return damage
        return damage;
    }

    // Heal hero, if health ends up being over maxHealth, make health = maxHealth
    public void heal(int healAmount) {
        this.health += healAmount;
        if (this.health > this.maxHealth) {
            this.health = maxHealth;
        }
    }

    // Get main hand item, if no item equipped, return null
    public Item getMainHand() {
        if (!inventory.get("mainHand").isEmpty()) {
            return inventory.get("mainHand").getFirst();
        }
        else {
            return null;
        }
    }

    // Get offhand item, if no item equipped, return null
    public Item getOffHand() {
        if (!inventory.get("offHand").isEmpty()) {
            return inventory.get("offHand").getFirst();
        }
        else {
            return null;
        }
    }

    public ArrayList<Item> getPotions() {
        return this.inventory.get("potions");
    }

    public Item getPotion(int index) {
        return this.inventory.get("potions").get(index);
    }

    public void setGold(double gold) {
        this.gold += gold;
    }

    public void setXP(int xp) {
        this.xp += xp;
        if (this.xp >= 1000) {
            this.levelUp();
        }
    }

    public void addInventoryOther(ArrayList<Item> items) {
        this.inventory.get("other").addAll(items);
    }

    public void levelUp () {
        if (this.xp >= 1000) {
            System.out.println("You're ready to level up!");
            System.out.println("Press ENTER to level up...");
            input.nextLine();
            System.out.println("You leveled up!");
            System.out.println("Level " + this.level + " -> " + (this.level + 1));
            this.level++;
        }
    }
}
