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


    // Making Arrays for starting inventory for different classes using Item objects
    private Item[] warriorItems = {
            new Item().makeWeapon("Sword", 20, 0, 10, 15),
            new Item().makeArmor("Shield", 10, 10, 30),
            new Item().makeArmor("Heavy Armor", 15, 10, 30)
    };
    private Item[] rogueItems = {
            new Item().makeWeapon("Dagger", 20, 0, 10, 15),
            new Item().makeWeapon("Dagger", 20, 0, 10, 15),
            new Item().makeArmor("Light Armor", 10, 5, 20)
    };
    private Item[] mageItems = {
            new Item().makeWeapon("Staff", 15, 25, 10, 50),
            new Item().makePotion("Health Potion", 25, 1, 20),
            new Item().makeArmor("Magical Robe", 10, 2, 50)
    };

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
            this.inventory.get("mainHand").add(warriorItems[0]);
            this.inventory.get("offHand").add(warriorItems[1]);
            this.inventory.get("armor").add(warriorItems[2]);
        } else if (classType == 'r') {
            this.inventory.get("mainHand").add(warriorItems[0]);
            this.inventory.get("offHand").add(warriorItems[1]);
            this.inventory.get("armor").add(warriorItems[2]);
        } else if (classType == 'm') {
            this.inventory.get("mainHand").add(warriorItems[0]);
            this.inventory.get("potions").add(warriorItems[1]);
            this.inventory.get("armor").add(warriorItems[2]);
        }
        this.health = 100;
        this.maxHealth = 100;
        this.level = 0;
        this.xp = 0;
        this.gold = 20.0;
        this.isAlive = true;
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
        System.out.println("Potions:");
        for (int i = 0; i < inventory.get("potions").size(); i++) {
            System.out.println("    " + i + ":" + inventory.get("potions").get(i).getName());
        }
        System.out.println("Other:");
        for (int i = 0; i < inventory.get("other").size(); i++){
            System.out.println("    " + i + ":" + inventory.get("other").get(i).getName());
        }
    }

    public HashMap<String, ArrayList<Item>> getInventory() {
        return inventory;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int[] getHealth() {
        // I return health as a Health Array, simply to get both values in one call, so i can avoid making a new function
        int[] healthArray = {health, maxHealth};
        return healthArray;
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

    public Item getMainHand() {
        return inventory.get("mainHand").get(0);
    }
}
