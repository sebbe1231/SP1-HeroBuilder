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
    // This will make battle mechanics easier, since i don't have to cycle through an Array
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
            System.out.println("    " + i + ":" + inventory.get("potions").get(i).getName());
        }
        System.out.println("Other:");
        for (int i = 0; i < inventory.get("other").size(); i++){
            System.out.println("    " + i + ":" + inventory.get("other").get(i).getName());
        }
        System.out.println();
    }

    public HashMap<String, ArrayList<Item>> getInventory() {
        return inventory;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int[] getHealth() {
        // I return health as a Health Array, simply to get both values in one call, so i can avoid making a new function
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

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.isAlive = false;
        }
    }

    public void heal(int healAmount) {
        this.health += healAmount;
        if (this.health > this.maxHealth) {
            this.health = maxHealth;
        }
    }

    public Item getMainHand() {
        return inventory.get("mainHand").get(0);
    }

    public Item getOffHand() {
        return inventory.get("offHand").get(0);
    }
}
