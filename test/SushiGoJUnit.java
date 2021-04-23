/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;
import sushigo.SushiGo;

/**
 *
 * @author mehai
 */
public class SushiGoJUnit {
    
    public SushiGoJUnit() {
    }
    //Good JUnit test for the isBeginner().
    //Meets the requirements asked, by typing "NO", as stated.
    @Test
    public void isBeginnerGood(){
        System.out.println("isBeginnerGood");
        String input = "NO";
        boolean expResult = true;
        boolean result = SushiGo.isBeginner(input);
        assertEquals(expResult, result);
        
    }
    //Bad JUnit test for the isBeginner().
    //Does not meet the requirements asked, by typing "XYZ" instead of "NO".
    @Test
    public void isBeginnerBad(){
        System.out.println("isBeginnerBad");
        String input = "XYZ";
        boolean expResult = false;
        boolean result = SushiGo.isBeginner(input);
        assertEquals(expResult, result);
    }
    //Boundary JUnit test for the isBeginner().
    //Just barely meets the requirements asked, by typing "No" instead of "NO".
    //This will still prove true, as the isBeginner() ignores case, however it 
    //is pushing the boundaries of the test.
    @Test 
    public void isBeginnerBoundary(){
        System.out.println("isBeginnerBoundary");
        String input = "No"; 
        boolean expResult = true;
        boolean result = SushiGo.isBeginner(input);
        assertEquals(expResult, result); 
    }
    //Good JUnit test for the ruleReminder().
    //Meets the requirements asked, by typing "YES", as stated.
    @Test
    public void ruleReminderGood(){
        System.out.println("ruleReminderGood");
        String inputs = "YES";
        boolean expResult = true;
        boolean result = SushiGo.ruleReminder(inputs);
        assertEquals(expResult, result);
    }
    //Bad JUnit test for the ruleReminder().
    //Does not meet the requirements asked, by typing "XYZ" instead of "YES".
    @Test
    public void ruleReminderBad(){
        System.out.println("ruleReminderBad");
        String inputs = "XYZ";
        boolean expResult = false; 
        boolean result = SushiGo.ruleReminder(inputs);
        assertEquals(expResult, result); 
    }
    //Boundary JUnit test for the ruleReminder().
    //Just barely meets the requirements asked, by typing "yeS" instead of "YES".
    //This will still prove true, as the ruleReminder() ignores case, however it 
    //is pushing the boundaries of the test.
    @Test
    public void ruleReminderBoundary(){
        System.out.println("ruleReminderBoundary");
        String inputs = "yeS";
        boolean expResult = true;
        boolean result = SushiGo.ruleReminder(inputs);
        assertEquals(expResult, result);
    }
    //Good JUnit test for the quickRating().
    //Meets the requirements asked, by typing 8, a valid choice between 0 - 10.
    @Test
    public void quickRatingGood(){
        System.out.println("quickRatingGood");
        int rating = 8;
        boolean expResult = true;
        boolean result = SushiGo.quickRating(rating);
        assertEquals(expResult, result);
       
    }
    //Bad JUnit test for the quickRating().
    //Does not meet the requirements asked, by typing 11, 
    //a non-valid choice, as the valid choice is between 0 - 10.
    @Test
    public void quickRatingBad(){
        System.out.println("quickRatingBad");
        int rating = 11;
        boolean expResult = false;
        boolean result = SushiGo.quickRating(rating);
        assertEquals(expResult, result);
    }
    //Boundary JUnit test for the quickRating().
    //Just barely meet the requirements asked, by typing 10, 
    //as the valid choice is between 0 - 10. This is pushing the boundary,
    //as any number higher than 10 would be invalid.
    @Test
    public void quickRatingBoundary(){
        System.out.println("quickRatingBoundary");
        int rating = 10; 
        boolean expResult = true;
        boolean result = SushiGo.quickRating(rating);
        assertEquals(expResult, result);
    }

}
