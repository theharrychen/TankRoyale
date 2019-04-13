package tests;

/**
 * This class is used to test the Wall logic class
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-21
 */

import drivers.*;

import static org.junit.Assert.*;


import logic.*;
import org.junit.Test;

public class WallTest {

    /**
     *Testing the constructor of Wall
     */
    @Test
    public void test_wallconstructor() {
        Wall w = new Wall(5, 5);
        Wall w2 = new Wall(5, 5);
        assertEquals("Unexpected width of created wall", 5, w.getWidth(), 0.001);
        assertEquals("Unexpected height of created wall", 5, w.getHeight(), 0.001);
    }

    /**
     *Testing the setter method for wall width
     */
    @Test
    public void test_setWallWidth() {
        Wall w = new Wall(5, 5);
        w.setWidth(7.0);
        assertEquals("Set wall width to 7.0. Unexpected wall width value", 7.0, w.getWidth(), 0.001);
        w.setWidth(MainGUI.WIDTH + 100);
        assertEquals("Set invalid width of wall. Wall width should be less than the width of the game window", 7.0, w.getWidth(), 0.001);

    }

    /**
     *Testing the setter method for wall height
     */
    @Test
    public void test_setWallHeight() {
        Wall w = new Wall(5, 5);
        w.setHeight(7.0);
        assertEquals("Set wall height to 7.0. Unexpected wall height value", 7.0, w.getHeight(), 0.001);
        w.setHeight(MainGUI.HEIGHT);
        assertEquals("Set invalid height of wall. Wall height should be 100 pixels less than the height of the game window", 7.0, w.getHeight(), 0.001);
    }

    /**
     *Testing the getter method for wall width
     */
    @Test
    public void test_getWidth() {
        Wall w = new Wall(5, 5);
        assertEquals("Unexpected return value for wall width", 5, w.getWidth(), 0.001);
    }

    /**
     *Testing the getter method for wall height
     */
    @Test
    public void test_getWallHeight() {
        Wall w = new Wall(5, 5);
        assertEquals("Unexpected return value for wall height", 5, w.getHeight(), 0.001);
    }

    /**
     *Testing for invalid wall width
     */
    @Test
    public void test_invalidwidth() {
        Wall w = new Wall(MainGUI.WIDTH + 100, 5);
        assertEquals("Invalid width of the wall. Width must be less than the game window's width", 0, w.getWidth(), 0.001);
    }

    /**
     *Testing for invalid wall height
     */
    @Test
    public void test_invalidheight() {
        Wall w = new Wall(5, MainGUI.HEIGHT);
        assertEquals("Invalid height of the wall. Height must be 100 pixels less than the game window's height", 0, w.getHeight(), 0.001);
    }

}