public class Hero extends Entity {
    // This is the Hero class. Extends entity class

    // Hero object constructor
    public Hero(String name, char classType){
        super(name, 1, 20, 0, "Human", classType);

        // Adding inventory items based on chosen class
        if(classType == 'w') {
            makeWarrior();
        } else if (classType == 'r') {
            makeRogue();
        } else if (classType == 'm') {
            makeMage();
        }
    }

    public void makeWarrior() {
        getInventory().makeWarriorInv();

        setMaxHealth(150);
    }

    public void makeRogue() {
        getInventory().makeRogueInv();

        setMaxHealth(100);
    }

    public void makeMage() {
        getInventory().makeMageInv();

        setMaxHealth(80);
    }


}
