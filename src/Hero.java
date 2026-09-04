import java.util.ArrayList;
import java.util.Arrays;

public class Hero {
    private String name;
    private int health;
    private int maxHealth;
    private int level;
    private int xp;
    private double gold;
    private boolean isAlive;
    private char classType;

    // Array is made with the type Item
    // Objects in inventory are individual objects, all inheriting Item object
    private ArrayList<Item> inventory = new ArrayList<Item>();

    // Making Arrays for starting inventory for different classes using Item class subclasses
    private Item[] warriorItems = {
            new Weapon("sword", 20, 0),
            new Armor("shield", 10),
            new Armor("heavy armor", 10)};
    private Item[] rogueItems = {
            new Weapon("dagger", 12, 0),
            new Weapon("dagger", 12, 0),
            new Armor("light armor", 5)};
    private Item[] mageItems = {
            new Weapon("staff", 15, 25),
            new Potion("healing potion", 25),
            new Armor("magical robe", 2)};

    // Hero object constructor
    public Hero(String name, char classType){
        this.name = name;
        this.classType = classType;

        // Adding inventory items based on chosen class
        if(classType == 'w') {
            this.inventory.addAll(Arrays.asList(warriorItems));
        } else if (classType == 'r') {
            this.inventory.addAll(Arrays.asList(rogueItems));
        } else if (classType == 'm') {
            this.inventory.addAll(Arrays.asList(mageItems));
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
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i+1) + ": " + inventory.get(i).getName());
        }
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int[] getHealth() {
        int[] healthArray = {health, maxHealth};
        return healthArray;
    }

    public int setHealth(int newHealth) {
        health = newHealth;
        return health;
    }
}
