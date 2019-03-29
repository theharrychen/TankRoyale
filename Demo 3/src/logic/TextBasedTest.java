package logic;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

import logic.TextBased;

/**
 * This class tests the TextBased class
 */

public class TextBasedTest{
    
    private static final String CLASSNAME = "TextBased";
    private static final String FILENAME = CLASSNAME + ".java";


    /**
     * Tests the setGameStart and getGameStart methods
     */
    @Test
    public void test_gameStart_SetterAndGetter(){
        TextBased t = new TextBased();
        t.setGameStart(true);
        assertEquals("Expected true", true, t.getGameStart());
    }

    /**
     * Tests the setMapChoice and getMapChoice methods
     */
    @Test
    public void test_mapChoice_SetterAndGetter(){
        TextBased t = new TextBased();
        t.setMapChoice(2);
        assertEquals("Expected 2", 2, t.getMapChoice(), 0.00001);
    }

    /**
     * Tests the setEndGame and getEndGame methods
     */
    @Test
    public void test_endGame_SetterAndGetter(){
        TextBased t = new TextBased();
        t.setEndGame(true);
        assertEquals("Expected true", true, t.getEndGame());
    }

    /**
     * Tests the rng method
     */
    @Test
    public void test_rng(){
        TextBased t = new TextBased();
        int test;
        for(int i = 0; i < 100; i++){
            test = t.rng(1, 10);
            if(test > 10 || test < 1)
                fail("Value is outside boundary values");
        }
    }
}