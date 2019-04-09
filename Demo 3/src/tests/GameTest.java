package tests;

/**
 * This class is used to perform logic tests on the Game Class
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-02-25
 */

import static org.junit.Assert.*;

import java.util.*;

import logic.Game;
import org.junit.Test;

public class GameTest {

    private static final String CLASSNAME = "Game";
    private static final String FILENAME = CLASSNAME + ".java";


    /**
     * Tests getGameOver and setGameOver methods
     */
    @Test
    public void test_gameOver_SetterAndGetter() {
        Game g = new Game();
        g.setGameOver(true);
        assertEquals("Expected true", true, g.getGameOver());
    }

    /**
     * Tests determineWinner method
     */
    @Test
    public void test_determineWinner() {
        Game g = new Game();
        g.start();
        g.getTanks().get(1).setAlive(false);
        assertEquals("Expected player 1", 1, g.determineWinner());
    }
}