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
    private Inventory inventory;

    private Scanner input = new Scanner(System.in);

    // Hero object constructor
    public Hero(String name, char classType){
        this.name = name;
        this.classType = classType;
        this.inventory = new Inventory();

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
        this.inventory.makeWarriorInv();

        this.health = 150;
        this.maxHealth = 150;
    }

    public void makeRogue() {
        this.inventory.makeRogueInv();

        this.health = 100;
        this.maxHealth = 100;
    }

    public void makeMage() {
        this.inventory.makeMageInv();

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

        this.inventory.printInventory();
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

    public Inventory getInventory() {
        return this.inventory;
    }

    // Make hero take damage
    public int takeDamage(int damage, boolean blocking) {

        // If hero is blocking, take damage equal to damage - defense
        if (blocking) {
            // Only take damage if damage is bigger than 0
            if (damage - this.inventory.getOffHand().getDefence() > 0){

                // Make damage equal to damage - defense
                damage -= this.inventory.getOffHand().getDefence();
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

    public void setGold(double gold) {
        this.gold += gold;
    }

    public void setXP(int xp) {
        this.xp += xp;
        if (this.xp >= 1000) {
            this.levelUp();
        }
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
