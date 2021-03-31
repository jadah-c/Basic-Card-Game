package sushigo;

public class Card {

	private String name;
	public String[] Names = {"sashimi", "tempura", "dumplings", "wasabi", "maki 3", "nigiri egg", "nigiri salmon", "nigiri squid", "chopsticks", "maki 1", "maki 2", "pudding"};

	/**
	 * 
	 * @param name
	 */
	public Card(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
        //points 
	public String descriptiveName() {
		if(name.equals("tempura"))
			return "Tempura (x1 = 0, x2 = 5)";
		if(name.equals("sashimi"))
			return "Sashimi (x1 = 0, x2 = 0, x3 = 10)";
		if(name.equals("dumplings"))
			return "Dumplings (x1 = 1, x2 = 3, x3 = 6, x4 = 10, x5 = 15)";
		if(name.equals("wasabi"))
			return "Wasabi (x3 points for next nigiri)";
		if(name.contains("maki")) {
			int num = Integer.parseInt(name.substring(5));
			return "Maki " + num + " (most maki = 6, second = 3)";
		}
		if(name.equals("nigiri egg"))
			return "Egg Nigiri (1)";
		if(name.equals("nigiri salmon"))
			return "Salmon Nigiri (2)";
		if(name.equals("nigiri squid"))
			return "Squid Nigiri (3)";
		if(name.equals("chopsticks"))
			return "Chopsticks (swap for two cards in one turn)";
		if(name.equals("pudding"))
			return "Pudding (no points until game ends)";
		
		return name;
	}

        @Override
	public String toString() {
		return name;
	}

}