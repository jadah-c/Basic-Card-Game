package sushigo;
import java.util.*;
public class Game {

	final int PLAYER_COUNT;
	final int HAND_SIZE;
	private List<Player> players;

	/**
	 * 
	 * @param playerCount
	 */
	public Game(int playerCount) {
		PLAYER_COUNT = playerCount;
		
		if(PLAYER_COUNT == 2)
			HAND_SIZE = 10;
		else if(PLAYER_COUNT == 3)
			HAND_SIZE = 9;
		else if(PLAYER_COUNT == 4)
			HAND_SIZE = 8;
		else if(PLAYER_COUNT == 5)
			HAND_SIZE = 7;
		else
			HAND_SIZE = -1;
		
		players = new ArrayList<Player>(PLAYER_COUNT);
		// players.add(new Player(Player.HUMAN, Util.strMenu("\nChoose your name:"))); Will need the util class to complete this 
		for(int i = 0; i < PLAYER_COUNT - 2; i++)
			players.add(new Player(Player.PLAYERTWO, "Player " + (i+2)));
		players.add(new Player(Player.PLAYERTWO, "AJ528"));
			
		System.out.println("\nStarting new game with " + PLAYER_COUNT + " players.\n");

		//add code to complete and find the winner after three rounds
	}
	/**
	 * 
	 * @param round
	 */
	public void runRound(int round) {
		//code needed here in order to run the round
	}

	/**
	 * 
	 * @param round
	 */
	public void scoreRound(int round) {
		System.out.println("\nScores:");
		int[] roundScores = new int[PLAYER_COUNT];
		int[] makiSums = new int[PLAYER_COUNT];
		
		//for loops required to find score per round
	}

	/**
	 * 
	 * @param hand
	 */
	public static int scoreHand(List<Card> hand) {
		//core required to label the points made from the Card list in players hands
                return 0;
	}

	/**
	 * 
	 * @param makiSums
	 * @param roundScores
	 */
        //add the scoreMaki method below to find out the maki points earned
	//public void scoreMaki(int[] makiSums, int[] roundScores) {
		//int largestSum = 0;
		//int secondLargestSum = 0;
		//int firstPlaceCount = 0;
		//int secondPlaceCount = 0;
		
		//for loop required to find out who and how many maki cards to find points accordingly
		
}

	//public int[] scoreGame() {
		//score puddings		
		// code required to score and deduct points according to the puddings placed. 
		//need a return array statement at the end
	//}

