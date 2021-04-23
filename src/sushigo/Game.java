package sushigo;
import java.util.*;
/**
 *
 * @author mehai
 */
public class Game {

    final int PLAYER_COUNT;
    final int HAND_SIZE;
    private Map<String, Integer> cardGroup;
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
	else
            HAND_SIZE = -1;	
	players = new ArrayList<Player>(PLAYER_COUNT);
	players.add(new Player(Player.HUMAN, Util.strMenu("\nChoose your name:")));
	for(int i = 0; i < PLAYER_COUNT - 1; i++)
            players.add(new Player(Player.PLAYERTWO, "Player " + (i+2)));
	players.add(new Player(Player.PLAYERTWO, "Player 2"));		
	System.out.println("\nStarting new game with " + PLAYER_COUNT + " players.\n");
            initCardGroup();    
            for(int i = 0; i < 3; i++)
                runRound(i); 
            int[] finalScores = scoreGame();
            int maxScore = Util.max(finalScores);
            System.out.println("Final Scores: ");
            for(int i =0; i < PLAYER_COUNT; i++)
                System.out.println(players.get(i) + ": " + finalScores[i] + (finalScores[i] == maxScore ? " (WINNER!)" : ""));
            System.out.println("");
            Util.waitForEnter();
	}
    /**
     * 
     * @param round
     */
    public void runRound(int round) {
        System.out.println("\n Round " + (round + 1) + "! \n");
        players.forEach(player -> {
            player.initDeckHand(HAND_SIZE);
        });
    Map<Integer, List<Card>> hands = initHands();          
        for(int i = 0; i < HAND_SIZE; i++){
            Player curPlayer = players.get(0);			
            List<Card> curHand = hands.get(0);
        //chopsticks card check
	boolean hasChopsticks = false;
        for (Iterator<Card> it = curPlayer.getHand(round).iterator(); it.hasNext();) {
                Card c = it.next();
            if("chopsticks".equals(c.getName())){
                hasChopsticks = true;
                break;
            }
        }
        System.out.println("Player has Chopsticks: " + hasChopsticks);
            int[] choice = Util.intMenu("Choose a card:", Util.cardsToStrings(curHand), hasChopsticks);
            curPlayer.addToDeck(curHand.get(choice[0]-1), round);
            curHand.remove(choice[0]-1);		
	//switching out the chopsticks for 2 new cards in one turn
            if(choice.length == 2){
		curPlayer.addToDeck(curHand.get(choice[1] - 1), round);
		curHand.remove(choice[1] - 1);
		curHand.add(curPlayer.popChopsticks(round));
            }
	//how the compiler (Player2) will select cards for now
            for(int x = 1; x < PLAYER_COUNT; x++) {
		players.get(x).addToDeck(hands.get(x).get(0), round);
		hands.get(x).remove(0);
            }		
		hands = rotateHands(hands);	
	//displaying the player's cards		
		System.out.println("");
		for(int z = 0; z < PLAYER_COUNT; z++) {
                    System.out.println(players.get(z) + ": " + players.get(z).getHand(round));
		}
                System.out.println("");
        }
        scoreRound(round);	
    }
    //switching set of cards between player 2 and user
    /**
     *
     * @param hands
     * @return
     */
    public Map<Integer, List<Card>> rotateHands(Map<Integer, List<Card>> hands) {
    Map<Integer, List<Card>> rotatedHands = new HashMap<>();
    for(int i = 0; i < PLAYER_COUNT; i++) {
	rotatedHands.put(i, hands.get( (i+1) % PLAYER_COUNT ));
    }
	return rotatedHands;
    }	
    //randomly assigning the starting cards for both players
    /**
     *
     * @return
     */
    public HashMap<Integer, List<Card>> initHands() {
	HashMap<Integer, List<Card>> hands = new HashMap<Integer, List<Card>>();	
	for(int i = 0; i < PLAYER_COUNT; i++) {
            List<Card> hand = new ArrayList<Card>(HAND_SIZE);
            for(int j = 0; j < HAND_SIZE; j++) {
		String randomCard = Card.NAMES[(int) (System.nanoTime() % Card.NAMES.length)];
		int copiesLeft = cardGroup.get(randomCard);				
		if(copiesLeft > 0) {
                    hand.add(new Card(randomCard));
                    cardGroup.put(randomCard, copiesLeft-1);
		}
		else
		j--;
            }		
            hands.put(i, hand);
        }	
	return hands;
    }
    /**
     *
     */
    public void initCardGroup() {
	cardGroup = new HashMap<String, Integer>();
	cardGroup.put("sashimi", 14);
        cardGroup.put("tempura", 14);
	cardGroup.put("dumplings", 14);
        cardGroup.put("wasabi", 6);
        cardGroup.put("maki 3", 8);
        cardGroup.put("nigiri egg", 5);
        cardGroup.put("nigiri salmon", 10);
        cardGroup.put("nigiri squid", 5);
        cardGroup.put("chopsticks", 4);
        cardGroup.put("maki 1", 6);
	cardGroup.put("maki 2", 12);
	cardGroup.put("pudding", 10);	
    }	
    /**
     *
     * @return
     */
    public int poolSize() {
	int size = 0;
        Set<String> keys = cardGroup.keySet();
        size = keys.stream().map(card -> cardGroup.get(card)).reduce(size, Integer::sum);
	return size;
    }	
    /**
     *
     * @param round
     */
    public void scoreRound(int round) {
	System.out.println("\nScores:");
        int[] roundScores = new int[PLAYER_COUNT];
        int[] makiSums = new int[PLAYER_COUNT];	
        for(int i = 0; i < PLAYER_COUNT; i++) {
            Player player = players.get(i);
            List<Card> hand = player.getHand(round);	
            for(int j = 0; j < hand.size(); j++) {
                Card card = hand.get(j);
                if(card.getName().contains("maki")) {
                    int amount = Integer.parseInt(card.getName().substring(5));
                    makiSums[i] += amount;
                    hand.remove(j);
                    j--;
                }
                else if(card.getName().equals("pudding"))
                    player.addPudding();
            }			
            roundScores[i] += scoreHand(hand);			
        }
	scoreMaki(makiSums, roundScores);	
        for(int i = 0; i < PLAYER_COUNT; i++) {
            players.get(i).addScore(roundScores[i]);
		System.out.println(players.get(i) + ": " + players.get(i).getScore() + " (+" + roundScores[i] + ")"
                    + " (Maki score: " + makiSums[i] + ") (Total puddings: " + players.get(i).puddingScore() + ")");
            }	
        System.out.println("");
    }
    /**
     *
     * @param hand
     * @return
     */
    public static int scoreHand(List<Card> hand) {
	int score = 0;
	int nextInstance = -1;
	Card card;
	while(hand.size() > 0) {
            card = hand.get(0);		
            switch (card.getName()) {
                case "tempura":
                nextInstance = Util.findCardInHand(hand, card);
                    if(nextInstance != -1) {
                        hand.remove(nextInstance);
                        score += 5;
                    }
                break;
                case "sashimi":
                nextInstance = Util.findCardInHand(hand, card);
                    if(nextInstance != -1) {
                        hand.remove(nextInstance);
                        nextInstance = Util.findCardInHand(hand, card);
                        if(nextInstance != -1) {
                            hand.remove(nextInstance);
                            score += 10;
                        }
                    }
                break;
                case "dumplings":
                nextInstance = Util.findCardInHand(hand, card);
                int count = 1;
                while(nextInstance != -1) {
                    count++;
                    hand.remove(nextInstance);
                    nextInstance = Util.findCardInHand(hand, card);
                }
                int dumplingScore = (count*(count+1)/2);
                if(dumplingScore > 15)
                    dumplingScore = 15;
                score += dumplingScore;
                break;
                case "wasabi":
                    for(int i = 1; i < hand.size(); i++) {
                        if(hand.get(i).getName().contains("nigiri")) {
                            if(hand.get(i).getName().contains("egg"))
                                score += 3;
                            if(hand.get(i).getName().contains("salmon"))
                                score += 6;
                            if(hand.get(i).getName().contains("squid"))
                                score += 9; 
                            hand.remove(i);
                            break;
                        }
                    }
                break;
                case "nigiri egg":
                    score += 1;
                    break;
                case "nigiri salmon":
                    score += 2;
                    break;
                case "nigiri squid":
                    score += 3;
                    break;
                default:
                    break;
            }		
            hand.remove(0);	
	}
	return score;
    }
    /**
     *
     * @param makiSums
     * @param roundScores
     */
    public void scoreMaki(int[] makiSums, int[] roundScores) {
	int largestNum = 0;
	int secondLargestNum = 0;
	int firstPlaceCount = 0;
	int secondPlaceCount = 0;
	for(int sum : makiSums) {
            if(sum > largestNum) {
		secondLargestNum = largestNum;
		largestNum = sum;
            }
            else if(sum > secondLargestNum)
		secondLargestNum = sum;
	}
	for(int i = 0; i < PLAYER_COUNT; i++) {
            if(makiSums[i] == largestNum)
		firstPlaceCount++;
            if(makiSums[i] == secondLargestNum)
		secondPlaceCount++;
	}
	int firstPoints = 0;
	int secondPoints = 0;
	if(largestNum > 0)
            firstPoints = 6/firstPlaceCount;
	if(secondLargestNum > 0 && firstPlaceCount == 1)
            secondPoints = 3/secondPlaceCount;
	for(int i = 0; i < PLAYER_COUNT; i++) {
            if(makiSums[i] == largestNum)
		roundScores[i] += firstPoints;
            else if(makiSums[i] == secondLargestNum)
		roundScores[i] += secondPoints;
        }
    }
    
// additional loop required for scoring puddings, possible tie and final scores
    /**
     *
     * @return
     */
    public int[] scoreGame() {
//scoring puddings		
	int[] puddings = new int[PLAYER_COUNT];
	for(int i = 0; i < PLAYER_COUNT; i++)
            puddings[i] = players.get(i).puddingScore();		
	int maxPudding = Util.max(puddings);
	int minPudding = Util.min(puddings);
	int[] puddingPoints = new int[PLAYER_COUNT];
	List<Integer> maxPuddingPlayers = new ArrayList<>();
	List<Integer> minPuddingPlayers = new ArrayList<>();
	for(int i = 0; i < PLAYER_COUNT; i++) {			
            if(players.get(i).puddingScore() == maxPudding) 
		maxPuddingPlayers.add(i);			
	if(players.get(i).puddingScore() == minPudding)
            minPuddingPlayers.add(i);
        }
        maxPuddingPlayers.forEach(playerIndex -> {
            puddingPoints[playerIndex] = 6/maxPuddingPlayers.size();
        });			
        minPuddingPlayers.stream().filter(playerIndex -> (puddingPoints[playerIndex] == 0)).forEachOrdered(playerIndex -> {
            puddingPoints[playerIndex] = -6/minPuddingPlayers.size();
        }); //helping in case of a tie
	System.out.println("Pudding scores:");
	for(int i = 0; i < PLAYER_COUNT; i++)
            System.out.println(players.get(i) + ": " + (puddingPoints[i] < 0 ? "" : "+") + puddingPoints[i]); 
	System.out.println("");	
    //showing the total scores
	int[] finalScores = new int[PLAYER_COUNT];
            for(int i = 0; i < PLAYER_COUNT; i++) {
		players.get(i).addScore(puddingPoints[i]); 
		finalScores[i] = players.get(i).getScore();
            }
            return finalScores;
    }
}
	

