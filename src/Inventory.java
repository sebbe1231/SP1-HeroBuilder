import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


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

public class Inventory{
    private HashMap<String, ArrayList<Item>> inventory = new HashMap<>();
    Scanner input = new Scanner(System.in);

    public Inventory () {
        // Adding inventory HashMap keys, making the HashMap "tree" in a way
        this.inventory.put("mainHand", new ArrayList<Item> ());
        this.inventory.put("offHand", new ArrayList<Item> ());
        this.inventory.put("potions", new ArrayList<Item> ());
        this.inventory.put("armor", new ArrayList<Item> ());
        this.inventory.put("other", new ArrayList<Item> ());
    }

    public void makeWarriorInv() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Sword", 20, 0, 10, 15)
        );
        this.inventory.get("offHand").add(
                new Item().makeArmor("Shield", 10, 10, true, 30)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Heavy Armor", 15, 10, false, 30)
        );
    }

    public void makeRogueInv() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Dagger", 20, 0, 10, 15)
        );
        this.inventory.get("offHand").add(
                new Item().makeWeapon("Dagger", 20, 0, 10, 15)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Light Armor", 10, 5, false, 20)
        );
    }

    public void makeMageInv() {
        this.inventory.get("mainHand").add(
                new Item().makeWeapon("Staff", 15, 25, 10, 50)
        );
        this.inventory.get("potions").add(
                new Item().makePotion("Health Potion", 25, 1, 20)
        );
        this.inventory.get("armor").add(
                new Item().makeArmor("Magical Robe", 10, 2, false, 50)
        );
    }

    public void printInventory() {
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

    // setInventory sets either mainHand, offHand or armor
    // inventoryType argument must be either mainHand, offHand or armor
    public void setInventory(Item item, String inventoryType) {

        // If there's already an item equipped in slot, remove item before adding new item
        if(!inventory.get(inventoryType).isEmpty()) {
            this.addOther(this.inventory.get(inventoryType));
            this.inventory.get(inventoryType).clear();
        }

        // Add item to slot
        this.inventory.get(inventoryType).add(item);
    }

    public void equipItem() {
        System.out.println("What hand would you like to modify?");
        int slotChoice = new ChoiceMaker(new ArrayList<String>(Arrays.asList("Main Hand", "Off Hand", "Armor"))).choiceResult();

        String slot = "";

        switch (slotChoice) {
            case 1:
                slot = "mainHand";
                break;
            case 2:
                slot = "offHand";
                break;
            case 3:
                slot = "armor";
                break;
        }

        // Making temp list for all items in Other key of inventory
        ArrayList<String> inventoryOther = new ArrayList<>();

        // Add all items in "other" key to array
        for (Item otherItem : this.inventory.get("other")) {
            inventoryOther.add(otherItem.getName());
        }

        System.out.println("What item would you like to equip?");

        // Print choices to add to inventory
        System.out.println("==========| Items |==========");
        int itemChoice = new ChoiceMaker(inventoryOther).choiceResult();

        Item item = this.inventory.get("other").get(itemChoice-1);

        // If there's already an item equipped in slot, remove item before adding new item
        if(!inventory.get(slot).isEmpty()) {
            this.addOther(this.inventory.get(slot));
            this.inventory.get(slot).clear();
        }

        // Add item to slot and remove item from "other" key
        this.inventory.get(slot).add(item);
        this.inventory.get("other").remove(itemChoice-1);
    }

    public void addPotions(ArrayList<Item> items) {
        this.inventory.get("potions").addAll(items);
    }

    public void addOther(ArrayList<Item> items) {
        this.inventory.get("other").addAll(items);
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
}
