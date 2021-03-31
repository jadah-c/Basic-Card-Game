package sushigo;
import java.util.List;
import java.util.Scanner;
public class Util {

	public static final int PARSE_ERROR = -1;
	public static Scanner scan;

	/**
	 * 
	 * @param title
	 * @param options
	 * @param numbering
	 * @param hasChopsticks
     * @return 
	 */
	public static int[] intMenu(String title, String[] options, int[] numbering, boolean hasChopsticks) {
		String display = title;
		int[] chopstickChoices = new int[]{-1, -1};
		
		for(int i = 0; i < options.length; i++) {
			display += "\n" + numbering[i] + ". " + options[i];
		}
		
		System.out.println(display);
		
		while(true) {
			
			String userChoice = getUserChoice();
			int userIntChoice = strToInt(userChoice);
			
			
			if(userIntChoice == PARSE_ERROR)
			{
				chopstickChoices = strToTwoInts(userChoice);
				if(!hasChopsticks || chopstickChoices[0] == PARSE_ERROR)
				{
					System.out.println("That's not a number.");
					continue;
				}
				
			}
			
			if(findInArray(numbering, userIntChoice))
			{
				return new int[]{userIntChoice};
			}
			else if(hasChopsticks && findInArray(numbering, chopstickChoices[0]) && findInArray(numbering, chopstickChoices[1]))
			{
				return chopstickChoices;
			}
			
			System.out.println("Not a valid option.");
		}	
	}
        //title, array of strings that act as options to show to user
        public static int[] intMenu(String title, String[] options){
            int[] numbering = new int[options.length];
            for(int i = 0; i < options.length; i++){
                numbering[i] = i+1;
            }
            return intMenu(title,options,numbering,false);
        }
	/**
	 * 
	 * @param title
	 * @param options
	 * @param hasChopsticks
     * @return 
	 */
        //needed to specificy when to consider inputs of two numbers as valid
	public static int[] intMenu(String title, String[] options, boolean hasChopsticks) {
		int[] numbering = new int[options.length];
		for(int i = 0; i < options.length; i++) {
			numbering[i] = i+1;
		}
		return intMenu(title, options, numbering, hasChopsticks);		
	}

	/**
	 * 
	 * @param title
	 * @param numbering
     * @return 
	 */
	public static int intMenu(String title, int[] numbering) {
		return intMenu(title, new String[0], numbering, false)[0];
	}
        
        public static int intMenu(String title, int min, int max) {
		int[] numbering = new int[max-min+1];
		for(int i = 0; i < numbering.length; i++) {
			numbering[i] = min+i;
		}
		return intMenu(title, numbering);
	}
        
	/**
	 * 
	 * @param title
     * @return 
	 */
	public static String strMenu(String title) {
		scan = new Scanner(System.in);
		System.out.println(title);
		return scan.nextLine();
	}

	public static void waitForEnter() {
		scan = new Scanner(System.in);
		System.out.println("Press enter to continue.");
		scan.nextLine();
	}

	public static String getUserChoice() {
		scan = new Scanner(System.in);
		String output = scan.nextLine();
		//scanner.close();
		return output;
	}

	/**
	 * 
	 * @param input
     * @return 
	 */
	public static int strToInt(String input) {
		try {
			int parsed = Integer.parseInt(input);
			return parsed;
		}
		catch(NumberFormatException e) {
			return PARSE_ERROR;
		}
	}
        /**
	 * 
	 * @param arr1
	 * @param arr2
     * @return 
	 */
	public static String[] concat(String[] arr1, String[] arr2) {
	String[] output = new String[arr1.length + arr2.length];
		for(int i = 0; i < arr1.length; i++)
			output[i] = arr1[i];
		for(int i = 0; i < arr2.length; i++)
			output[arr1.length + i] = arr2[i];
		return output;
	}
        public static int findCardInHand(List<Card> hand, Card key) {
		 for(int i = 1; i < hand.size(); i++) {
			 if(hand.get(i).getName().equals(key.getName()))
				 return i;
		 }
		 return -1;
	}
	/**
	 * 
	 * @param input
     * @return 
	 */
	public static int[] strToTwoInts(String input) {
	{
		int[] results = new int[2];
		try
		{
			String[] stringInputs = input.split(" ");
			if(stringInputs.length == 2)
			{
				results[0] = Integer.parseInt(stringInputs[0]);
				results[1] = Integer.parseInt(stringInputs[1]);
			}
		}
		catch(NumberFormatException e)
		{
			results[0] = PARSE_ERROR;
		}
		
		return results;
	}
        }
	public static boolean findInArray(int[] arr, int key) {
		for(int elt : arr)
			if(elt == key)
				return true;
		return false;
	}

	/**
	 * 
	 * @param input
     * @return 
	 */
	public static String[] cardsToStrings(List<Card> input) {
		String[] output = new String[input.size()];
		for(int i = 0; i < input.size(); i++) {
			if(SushiGo.detailedNames)
				output[i] = input.get(i).descriptiveName();
			else
				output[i] = input.get(i).getName();
		}
		return output;			
	}

	
        /**
	 * 
	 * @param arr
     * @return 
	 */
	public static int min(int[] arr) {
		int min = arr[0];
		for(int elt : arr)
			if(elt < min)
				min = elt;
		return min;
	}
	/**
	 * 
	 * @param arr
     * @return 
	 */
	public static int max(int[] arr) {
		int max = arr[0];
		for(int elt : arr) 
			if(elt > max)
				max = elt;
		return max;
	}

	
}