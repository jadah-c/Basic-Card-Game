/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cardgame;

import cardgame.Card.Suit;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Megha Patel
 */
public class CardGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        Card[] hand=CardHandGenerator.generateHand(7);
        
        // print entire hand
        System.out.println("Generated a hand of 7 Cards: ");
        for(Card card:hand){
            System.out.println(card.getValue()+" of "+card.getSuit());
        }
        
        //user card input
        System.out.println("Pick a suit for your card: ");
        for(int i=0;i<Card.Suit.values().length;i++)
        {
            System.out.println((i+1)+": "+Card.Suit.values()[i]);
        }
        int suitPos=input.nextInt()-1;
        
        System.out.println("Enter a value (1 to 13): ");
       // int value=input.nextInt();
        for(int i=0;i<Value.values().length;i++)
        {
            System.out.println((i+1)+" : "+Value.values()[i]);
        }
        int valPos=input.nextInt()-1;
        
        Card userCard= new Card(Value.values()[valPos],Card.Suit.values()[suitPos]);
        
        //check for the match
        boolean match=false;
        for(Card card:hand){
            if(card.getValue()==userCard.getValue() && card.getSuit().equals(userCard.getSuit())){
                match=true; //card match
                break;
            }
        }
        // a>b?a:b;
        if(match)
        {
            System.out.println("You guessed it right...");
         }
        else
        {
            System.out.println("Sorry no match..");
        }
                
        
        
        
        
    }

}
