package sushigo;
import java.util.Scanner;
/**
 *
 * @author mehai
 */
public class SushiGo {
    public static boolean detailedNames = false;

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        boolean newPlayer = true;
        do {
            System.out.println("Welcome to SushiGO! \nHave you ever played before?\n(YES) OR (NO)");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if(isBeginner(input)){
                System.out.println("\nWould you like a reminder of the SushiGo's rules?\n(YES) OR (NO)");
                String inputs = scan.nextLine();
                    ruleReminder(inputs);
            }  
        }
        while(!newPlayer);
        new SushiGo().run();
        System.out.println("Please leave a rating of your SushiGo experience (0-10)");
        Scanner scan = new Scanner(System.in);
        int rating = scan.nextInt();
        quickRating(rating);
            System.exit(0);
    }    
    /**
     *
     * @param input
     * @return
     */
    public static boolean isBeginner(String input){ 
        if (input.equalsIgnoreCase("NO")){
            return true;
        }
        else
            return false;
        }

    /**
     *
     * @param inputs
     * @return
     */
    public static boolean ruleReminder(String inputs){
        if (inputs.equalsIgnoreCase("YES")){
            System.out.println("SushiGo will be played in 3 rounds."
                        + "\nThe winner will be decided based on the highest points once the 3 rounds have been completed."
                        + "\nIf you would like to see how many points each card gives,"
                        + " be sure to turn on detailed names from the Options menu!");
            return true;
        }
        else
            System.out.println("\nIf you would like to see how many points each card gives,"
                        + " be sure to turn on detailed names from the Options menu!");
            return false;
        }

    /**
     *
     * @param rating
     * @return
     */
    public static boolean quickRating(int rating){
        if (rating <= 10 && rating >= 8){
            System.out.println("We are happy you loved SushiGo!\n");
            return true;
        }
        else if(rating < 8 && rating >= 6){
            System.out.println("Thanks for your review!\nWith future updates, "
                            + "we are sure you will love your SushiGo experience even more!");
            return true;
        }
        else if(rating < 6 && rating >=0){
            System.out.println("We are saddened that you did not enjoy your SushiGo experience.\nPlease "
                                + "feel free to try out our other game, PizzaGo!");
            return true;
        }
        return false;       
    }

    /**
     *
     */
    public void run() {
	System.out.println("\nSUSHI GO!");
        while (true) {
            int userChoice = Util.intMenu("\nMain menu", new String[]{"New game", "Options", "Exit"})[0];
            if(userChoice == 1)
                newGame();
            else if(userChoice==2){
                int optionsChoice = Util.intMenu("\nOptions", new String[]{"Select detailed names (" +
                    (detailedNames ? "yes" : "no") + ")", "Back to main menu"})[0];
            if(optionsChoice == 1)
                detailedNames = !detailedNames;
            else
            continue;
            }
            else if(userChoice == 3){
            break;
            }
        }
    }
		
    /**
     *
     */
    public void newGame() {
	int playerCount = Util.intMenu("\nPlease select the total players(2-3) to begin.", 2, 3);
        new Game(playerCount);
    }	
}

	