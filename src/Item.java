public class Item {
    // Item object encapsulates all items in the game
    // This is done to make general handling of items easier in an inventory sense
    // I could have made an object called "Item" which can simply be modeled to fit any item
    // However I expect that this could get messy later on

    private String name;

    public Item(String name) {
        this.name = name;
    }

    // getName() is inherited by all subclasses
    public String getName() {
        return name;
    }

}
