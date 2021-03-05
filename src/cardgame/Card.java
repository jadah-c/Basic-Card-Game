package cardgame;

public class Card {

	public enum Suit{
            HEARTS, CLUBS, SPADES,DIAMONDS
        }
    
         private Value value;
         private Suit suit;
        
        //public static final String[] SUITS={"Hearts","Diamonds","Spades","Clubs"};
        
        public Card(Value value,Suit suit)
        {
            this.value=value;
            this.suit=suit;
        }

    /**
     * @return the value
     */
    public Value getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Value value) {
        this.value = value;
    }

    /**
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * @param suit the suit to set
     */
    public void setSuit(Suit suit) {
        this.suit = suit;
    }

	

}