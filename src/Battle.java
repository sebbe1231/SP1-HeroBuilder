import java.util.Scanner;

public class Battle {
    // Class to handle battle mechanics

    private Hero hero;
    private Enemy enemy;
    private Scanner input = new Scanner(System.in);

    // Constructor
    public Battle(Hero hero, Enemy enemy) {
        this.hero = hero;
        this.enemy = enemy;
    }

    // Function to start fight
    public void fight() {
        System.out.println("You see an " + enemy.getType() + "!");
        System.out.println("Press ENTER to continue...");
        input.nextLine();

        // While loop that ends when someone dies
        while (hero.getIsAlive() && enemy.getIsAlive()){
            System.out.println("Your turn");
            String choice = new ChoiceMaker(new String[] {"Main Hand", "Off Hand", "Both", "Heal", "Potion"}).choiceResult();

            switch (choice) {
                case "Main Hand":
                    System.out.println("You attack with your main hand for " + hero.getMainHand().getDamage() + " damage");
                    this.enemy.takeDamage(this.hero.getMainHand().getDamage());
                    break;
                case "Off Hand":
                    if (this.hero.getOffHand().getItemType() == "shield")
                        System.out.println("You block");
                    else if (this.hero.getOffHand().getItemType() == "weapon") {
                        System.out.println("You attack with your off hand");
                    }
                    break;
                case "Both":
                    System.out.println("You attack with both hands");
                    break;
                case "Heal":
                    System.out.println("You heal for " + this.hero.getMainHand().getHealing() + " HP");
                    this.hero.heal(this.hero.getMainHand().getHealing());
                    break;
                case "Potion":
                    System.out.println("You take a potion");
                    break;
            }

            System.out.println();

            System.out.println(this.hero.getName() + " HP: " + this.hero.getHealth()[0] + "/" + this.hero.getHealth()[1]);
            System.out.println(this.enemy.getType() + " HP: " + this.enemy.getHealth()[0] + "/" + this.enemy.getHealth()[1]);

            System.out.println();

            if (!enemy.getIsAlive()) {
                System.out.println("You won!");
                return;
            }

            System.out.println(enemy.getType() + "'s turn");
            System.out.println("Press ENTER to continue...");
            input.nextLine();

            System.out.println("Enemy hit you for " + enemy.getMainHand().getDamage());
            hero.takeDamage(this.enemy.getMainHand().getDamage());
            System.out.println();

            System.out.println(this.hero.getName() + " HP: " + this.hero.getHealth()[0] + "/" + this.hero.getHealth()[1]);
            System.out.println(this.enemy.getType() + " HP: " + this.enemy.getHealth()[0] + "/" + this.enemy.getHealth()[1]);

            if (!hero.getIsAlive()) {
                System.out.println("The " + enemy.getType() + " took your head, you have died...");
                return;
            }

            System.out.println("Press ENTER to continue...");
            input.nextLine();
        }
    }
}
