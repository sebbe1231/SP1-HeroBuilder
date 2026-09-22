import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Inventory {
    private HashMap<String, ArrayList<Item>> inventory = new HashMap<>();
    Scanner input = new Scanner(System.in);

    public Inventory (Item[] mainHand, Item[] offHand, Item[] potions, Item[] armor, Item[] other) {
        this.inventory.put("mainHand", new ArrayList<Item> (Arrays.asList(mainHand)));
        this.inventory.put("offHand", new ArrayList<Item> (Arrays.asList(offHand)));
        this.inventory.put("potions", new ArrayList<Item> (Arrays.asList(potions)));
        this.inventory.put("armor", new ArrayList<Item> (Arrays.asList(armor)));
        this.inventory.put("other", new ArrayList<Item> (Arrays.asList(other)));
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
    public void setInventory(ArrayList<Item> items, String inventoryType) {
        System.out.println("What Item from your inventory would you like to equip in your main hand?");

        // Making temp list for all items in Other key of inventory
        ArrayList<String> inventoryOther = new ArrayList<>();

        for (Item item : this.inventory.get("other")) {
            inventoryOther.add(item.getName());
        }

        System.out.println("==========| Items |==========");
        int choice = new ChoiceMaker(inventoryOther).choiceResult();

        if(!inventory.get(inventoryType).isEmpty()) {
            this.addOther(this.inventory.get(inventoryType));
            this.inventory.get(inventoryType).clear();
        }

        this.inventory.get(inventoryType).addAll(items);
        this.inventory.get("other").remove(choice-1);
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
