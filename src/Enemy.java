import java.util.ArrayList;
import java.util.HashMap;

public class Enemy {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private double gold;
    private boolean isAlive;

    private Inventory inventory;

    // Enemy object constructor
    public Enemy(String name, String type, int maxHealth, double gold) {

        this.inventory = new Inventory();

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

        this.inventory.printInventory();
    }

    public String getType() {
        return type;
    }

    public Inventory getInventory() {
        return inventory;
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
}
