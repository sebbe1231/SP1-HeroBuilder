import java.util.Scanner;

public class Entity {

    private String name;
    private int health;
    private int maxHealth;
    private double gold;
    private boolean isAlive;
    private int level;
    private int xp;
    private String type;
    private char classType;
    private Inventory inventory;

    private Scanner input = new Scanner(System.in);

    public Entity(String name, int maxHealth, double gold, int level, String type, char classType) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.gold = gold;
        this.isAlive = true;
        this.level = level;
        this.xp = 0;
        this.type = type;
        this.classType = classType;
        this.inventory = new Inventory();
    }

    public String getName(){
        return name;
    }

    public String getType() {
        return type;
    }

    public void setMaxHealth(int health) {
        this.maxHealth = health;
        this.health = health;
    }

    public Inventory getInventory() {
        return this.inventory;
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

    public double getGold() {
        return this.gold;
    }

    public void setGold(double gold) {
        this.gold += gold;
    }

    // Make entity take damage
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

    // Heal entity, if health ends up being over maxHealth, make health = maxHealth
    public void heal(int healAmount) {
        this.health += healAmount;
        if (this.health > this.maxHealth) {
            this.health = maxHealth;
        }
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

    // Function to print character sheet
    public void printCharacterSheet(){
        System.out.println("Name: " + name);
        System.out.println("HP: " + health + "/" + maxHealth);
        System.out.println("Level: " + level);
        System.out.println("XP: " + xp);
        System.out.println("Gold: " + gold);
        System.out.println("Class: " + classType);
        System.out.println("Type: " + type);
        System.out.println("\n----------------------\n");

        this.inventory.printInventory();
    }
}
