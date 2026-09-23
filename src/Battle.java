import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Class to handle battle mechanics
public class Battle {

    private Hero hero;
    private Enemy enemy;
    private Scanner input = new Scanner(System.in);

    // Constructor
    public Battle(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    // Function to start fight
    public boolean fight() {
        System.out.println("You see an enemy " + enemy.getType() + "!");
        System.out.println("Press ENTER to continue...");
        input.nextLine();

        // Print enemy character sheet
        System.out.println("====| Enemy |====");
        this.enemy.printEnemySheet();

        System.out.println("Press ENTER to continue...");
        input.nextLine();

        System.out.println();

        // While loop that ends when someone dies
        while (true){
            boolean blocking = false;

            //=========================================
            //              HERO TURN
            //=========================================
            System.out.println("Your turn");
            System.out.println();

            // Switch for battle choices
            switch (new ChoiceMaker(new ArrayList<String>(Arrays.asList("Attack", "Block", "Heal", "Potion"))).choiceResult()) {
                // If choice is 'Attack'
                case 1:
                    int damage = 0;

                    // Check if hero has main hand item equipped
                    if (this.hero.getInventory().getMainHand() != null && this.hero.getInventory().getMainHand().getItemType() == "weapon") {
                        System.out.println("You attack with " + this.hero.getInventory().getMainHand().getName()
                                + " for " + this.hero.getInventory().getMainHand().getDamage() + " damage");
                        damage += this.hero.getInventory().getMainHand().getDamage();
                    }

                    // Check if hero has offhand item equipped
                    if (this.hero.getInventory().getOffHand() != null && this.hero.getInventory().getOffHand().getItemType() == "weapon") {
                        System.out.println("You attack with " + this.hero.getInventory().getOffHand().getName()
                                + " for " + this.hero.getInventory().getOffHand().getDamage() + " damage");
                        damage += this.hero.getInventory().getOffHand().getDamage();
                    }

                    // Print total damage, and make enemy take damage
                    System.out.println("You do a total of " + damage + " damage");
                    this.enemy.takeDamage(damage);

                    break;

                // If choice is 'Block'
                case 2:
                    // Check if hero has Offhand item equipped and that item is a type shield
                    if (this.hero.getInventory().getOffHand() != null && this.hero.getInventory().getOffHand().getItemType() == "shield") {

                        // If it's a shield, make 'blocking' true, else just attack
                        System.out.println("You block");
                        blocking = true;
                    }
                    else {
                        System.out.println("You have no shield equipped");
                    }
                    break;

                // If choice is 'Heal'
                case 3:
                    // Check if 'Main Hand' item is equipped
                    if (hero.getInventory().getMainHand() != null) {
                        System.out.println("You heal for " + this.hero.getInventory().getMainHand().getHealing() + " HP");
                        this.hero.heal(this.hero.getInventory().getMainHand().getHealing());
                    }
                    else {
                        System.out.println("You have no main hand item equipped");
                    }
                    break;

                // If choice is 'Potion'
                case 4:
                    if (!this.hero.getInventory().getPotions().isEmpty()) {
                        ArrayList<String> potionChoices = new ArrayList<>();
                        for (Item potion : this.hero.getInventory().getPotions()) {
                            potionChoices.add(potion.getName());
                        }
                        System.out.println("Choose a potion:");
                        Item potion = this.hero.getInventory().getPotion(new ChoiceMaker(potionChoices).choiceResult() - 1);
                        System.out.println("You chose: " + potion.getName());
                        System.out.println("You heal for " + potion.getHealing() + " HP");
                        hero.heal(potion.getHealing());
                    }
                    else {
                        System.out.println("You have no potions...");
                    }
                    break;
            }

            System.out.println();

            // Print hero and enemy HP
            System.out.println(this.hero.getName() + " HP: " + this.hero.getHealth()[0] + "/" + this.hero.getHealth()[1]);
            System.out.println(this.enemy.getType() + " HP: " + this.enemy.getHealth()[0] + "/" + this.enemy.getHealth()[1]);

            System.out.println();


            // Check if enemy is dead, if so, print 'You won!' and return
            if (!enemy.getIsAlive()) {
                System.out.println("You won!");
                System.out.println("For slaying " + this.enemy.getType() + " you got:");

                // Print enemy gold and add that to hero gold
                System.out.println(this.enemy.getGold() + " gold");
                this.hero.setGold(this.enemy.getGold());

                // Print XP won and add it to hero XP
                System.out.println("1000 XP");
                this.hero.setXP(1000);


                // Add winnings array to hero inventory in "other" key
                this.hero.getInventory().addOther(this.enemy.getInventory().getInventoryArray());
                System.out.println("Press ENTER to continue...");
                input.nextLine();
                return true;
            }


            //======================================
            //              ENEMY TURN
            //======================================
            System.out.println(enemy.getType() + "'s turn");
            System.out.println("Press ENTER to continue...");
            input.nextLine();

            // If hero is blocking
            if (blocking) {
                System.out.println("You blocked");
            }

            // Hero takes damage, and if hero is blocking takes less damage
            System.out.println("Enemy hit you for " +
                    hero.takeDamage(this.enemy.getInventory().getMainHand().getDamage(), blocking));
            System.out.println();

            // Print hero and enemy HP
            System.out.println(this.hero.getName() + " HP: " + this.hero.getHealth()[0] + "/" + this.hero.getHealth()[1]);
            System.out.println(this.enemy.getType() + " HP: " + this.enemy.getHealth()[0] + "/" + this.enemy.getHealth()[1]);

            // Check is hero is alive
            if (!hero.getIsAlive()) {
                System.out.println("The " + enemy.getType() + " took your head, you have died...");
                 return false;
            }

            double healthPercent = (double) hero.getHealth()[0] / (double) this.hero.getHealth()[1] * 100;

            if (healthPercent <= 25){
                System.out.println();
                System.out.println("|=========================|");
                System.out.println("|         WARNING!        |");
                System.out.println("| You have " + new DecimalFormat("0.00").format(healthPercent) + "% HP left! |");
                System.out.println("|=========================|");
            }

            System.out.println("Press ENTER to continue...");
            input.nextLine();
        }
    }
}