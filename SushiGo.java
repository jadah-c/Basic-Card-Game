package sushigo;

public class SushiGo {

        public static boolean detailedNames = false;
        
        public static void main(String[] args){
           new SushiGo().run();
		System.exit(0);
	}
	
	public void run() {
	System.out.println("\nSUSHI GO!");
		
            OUTER:
            while (true) {
                int userChoice = Util.intMenu("\nMain menu", new String[]{"New game", "Options", "Score", "Exit"})[0];
                switch (userChoice) {
                    case 1 -> newGame();
                    case 2 -> {
                        int optionsChoice = Util.intMenu("\nOptions", new String[]{"Select detailed names (" +
                                (detailedNames ? "yes" : "no") + ")", "Back to main menu"})[0];
                        if(optionsChoice == 1)
                            detailedNames = !detailedNames;
                        else
                            continue;
                }
                    case 3 -> {
                }
                    case 4 -> {
                        break OUTER;
                }
                    default -> {
                }
                }
                        }
	}
	// in the future, add code here to incorporate the newGame() method	
        public void newGame() {
		int playerCount = Util.intMenu("\nSelect number of players (1-2):", 1, 2);
		new Game(playerCount);
	}	
		
    }

	