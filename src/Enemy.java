import java.util.ArrayList;
import java.util.Arrays;

public class Enemy {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private double gold;
    private boolean isAlive;
    private ArrayList<Item> inventory = new ArrayList<Item>();;

//    private ArrayList<Item> inventory = new ArrayList<>();

    public Enemy(String name, String type, int maxHealth, double gold, Item[] inventory) {
        this.name = name;
        this.type = type;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.gold = gold;
        this.isAlive = true;
        this.inventory.addAll(Arrays.asList(inventory));
//        this.inventory.addAll(Arrays.asList(inventory));
    }

    public void printEnemySheet() {
        System.out.println("Name: " + name);
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Gold: " + gold);
        System.out.println("\n-----------------------------\n");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println((i+1) + ": " + inventory.get(i).getName());
        }
    }

    public String getType() {
        return type;
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

//    public int getDamage() {
//
//    }
}
