import java.util.Scanner;

public class ChoiceMaker {
    private String[] choices;
    private Scanner input = new Scanner(System.in);

    public ChoiceMaker (String[] choices) {
        this.choices = choices;
    }

    public String choiceResult() {
        for (int i = 0; i < choices.length; i++){
            System.out.println((i + 1) + ") " + choices[i]);
        }
        while (true) {
            try {
                int choice = Integer.parseInt(input.nextLine());
                if (choice > 0 && choice <= choices.length) {
                    System.out.println(choices[choice - 1]);
                    System.out.println();
                    return choices[choice - 1];
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
