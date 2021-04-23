package sushigo;

/**
 *
 * @author mehai
 */
public class Card {
    private String name;
    public static String[] NAMES = new String[]{"sashimi", "tempura", "dumplings", "wasabi", "nigiri egg", 
                                            "nigiri salmon", "nigiri squid", "chopsticks", "maki 1", "maki 2", "pudding",};
	/**
	 * 
	 * @param name
	 */
    public Card(String name) {
	this.name = name;
    }
    /**
     *
     * @return
     */
    public String getName() {
	return name;
    }
    //points 
    /**
     *
     * @return
     */
    public String descriptiveName() {
	if(name.equals("tempura"))
            return "Tempura (x1 is 0 points, x2 is 5 points)";
	if(name.equals("sashimi"))
            return "Sashimi (x1 is 0 points, x2 is 0 points, x3 is 10 points)";
	if(name.equals("dumplings"))
            return "Dumplings (x1 is 1 point, x2 is 3 points, x3 is 6 points, x4 is 10 points, x5 is 15 points)";
	if(name.equals("wasabi"))
            return "Wasabi (x3 points multiplier for next nigiri selected)";
	if(name.contains("maki")) {
            int num = Integer.parseInt(name.substring(5));
            return "Maki " + num + " (most maki gets 6 points, second most gets 3 points)";
	}
	if(name.equals("nigiri egg"))
            return "Egg Nigiri (1 point)";
	if(name.equals("nigiri salmon"))
            return "Salmon Nigiri (2 points)";
	if(name.equals("nigiri squid"))
            return "Squid Nigiri (3 points)";
	if(name.equals("chopsticks"))
            return "Chopsticks (use these to swap for two cards in one turn)";
	if(name.equals("pudding"))
            return "Pudding (points will be added at end of game)";
	return name;
    }
    /**
     *
     * @return
     */
    @Override
    public String toString() {
	return name;
    }
}