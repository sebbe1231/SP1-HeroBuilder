import java.util.ArrayList;
import java.util.Arrays;

public class Hero {
    private String name;
    private int hp;
    private int maxHealth;
    private int level;
    private int xp;
    private double gold;
    private boolean isAlive;
    private char classType;
    private ArrayList<String> inventory = new ArrayList<String>();

    public Hero(String name, char classType){
        this.name = name;
        this.hp = 100;
        this.maxHealth = 100;
        this.level = 0;
        this.xp = 0;
        this.gold = 20;
        this.isAlive = true;
        this.classType = classType;
        this.inventory.addAll(Arrays.asList("sword", "shield"));
    }

    public void printHeroSheet(){
        System.out.println("Name: " + name);
        System.out.println("HP: " + hp);
        System.out.println("Max Health: " + maxHealth);
        System.out.println("Level: " + level);
        System.out.println("XP: " + xp);
        System.out.println("Gold: " + gold);
        System.out.println("Class: " + classType);
        System.out.println("\n----------------------\n");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.println(inventory.get(i));
        }
    }
}
