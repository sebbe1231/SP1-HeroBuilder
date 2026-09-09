import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoiceMaker {
    // Class for making choices based on number of choices given, returns choice string
    // Format:
    /*
    1) choice1
    2) choice2
    3) choice3
    ...
     */

    private ArrayList<String> choices = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    public ChoiceMaker (ArrayList<String> choices) {
        this.choices = choices;
    }

    public int choiceResult() {
        // Prints choices
        for (int i = 0; i < choices.size(); i++){
            System.out.println((i + 1) + ") " + choices.get(i));
        }

        // Loop that runs until a valid input has been given (Must be an int)
        while (true) {
            // Try checks if input is an int
            try {
                int choice = Integer.parseInt(input.nextLine());

                // Checks if input is within array length
                if (choice > 0 && choice <= choices.size()) {
                    System.out.println(choices.get(choice - 1));
                    System.out.println();
                    return choice;
                }
                else {
                    System.out.println("Not a valid choice");
                }

            } catch (Exception e) {
                System.out.println("Not a valid choice");
            }
        }
    }
}
