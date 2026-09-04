import java.util.Scanner;

public class Main {
    void main(){

        // User input to create hero
        Scanner input = new Scanner(System.in);

        System.out.println("Hello hero!\nWhat is thy name?");
        String name = input.nextLine();
        System.out.println("Good to meet you " + name + "!");
        System.out.println("What is thy profession?\n(W)arrior\n(R)ogue\n(M)age");
        char classType = input.nextLine().toLowerCase().charAt(0);

        // Print costume text for each class
        if (classType == 'w'){
            System.out.println("A warrior huh? May thy sword slay all foes on your path, and your shield protect you in battle!");
        }
        else if (classType == 'r') {
            System.out.println("A rogue? Your steps as that of a cat, nimble on your feet and quick to think!");
        }
        else if (classType == 'm') {
            System.out.println("A mage? Your spells of destruction and creation, healing and damage, magic is your life!");
        }
        else {
            // End program if input is not valid
            System.out.println("I am yet to hear of such profession");
            return;
        }

        // Making the hero
        Hero hero1 = new Hero(name, classType);
        hero1.printCharacterSheet();

        // Creating new enemy
        Enemy enemy1 = new Enemy("John", "Ogre", 200, 100);
        enemy1.setMainHand(new Item().makeWeapon("Axe", 20, 0, 100, 10));
        enemy1.printEnemySheet();

        System.out.println("You see an " + enemy1.getType() + "!");
        System.out.println("Press ENTER to continue...");
        input.nextLine();
        while (hero1.getIsAlive() && enemy1.getIsAlive()){
            System.out.println("Enemy hit you for " + enemy1.getMainHand().getDamage());
            hero1.setHealth(hero1.getHealth()[0] - enemy1.getMainHand().getDamage());
            System.out.println(hero1.getName() + ": " + hero1.getHealth()[0] + "/" + hero1.getHealth()[1] + " HP");
            if (hero1.getIsAlive() == false) {
                System.out.println("The " + enemy1.getType() + " took your head, you have died...");
            }

            System.out.println("Press ENTER to continue...");
            input.nextLine();
        }
    }
}