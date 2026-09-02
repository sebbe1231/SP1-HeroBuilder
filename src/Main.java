import java.util.Scanner;

public class Main {
    void main(){
        Scanner input = new Scanner(System.in);

        System.out.println("Hello hero!\nWhat is thy name?");
        String name = input.nextLine();
        System.out.println("Good to meet you " + name + "!");
        System.out.println("What is thy profession?");
        char classType = input.nextLine().charAt(0);
        System.out.println("What a noble profession!");

        Hero hero1 = new Hero(name, classType);
        Hero hero2 = new Hero("John Doe", 'R');
        hero1.printCharacterSheet();
        hero2.printCharacterSheet();
    }
}