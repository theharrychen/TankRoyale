package tests;

/**
 * This class is used to test the Bullet Logic Class
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-28
 */

import drivers.*;

import static org.junit.Assert.*;

import java.util.*;

import logic.*;
import org.junit.Test;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.shape.*;

public class BulletTest extends FormatTester {

    //Testing Class Name
    public static final String CLASSNAME = "Bullet";

    public BulletTest() {
        super(CLASSNAME, false);
    }

    //Instance variable testing
    private void testInstanceVariables() {
        String[] vars1 = {"private int lifeTime"};
        assertTrue("Instance variables should be private with correct name [lifeTime] and type [int].", instanceVariablesArePrivate(vars1));
    }

    @Test
// @SuppressWarnings( "deprecation" ) //not sure why it shows up, just trying to fix
    public void hasMethods() {
        Bullet b = new Bullet();

        //Testing setters and getters still exist
        String[] vars2 = {"getLifeTime"};
        assertTrue("Private instance variables should have appropriate getters", instanceVariablesArePrivate(vars2));
        String[] vars3 = {"setLifeTime"};
        assertTrue("Private instance variables should have appropriate setters", instanceVariablesArePrivate(vars3));

        //Testing the accuracy of setters and getters.
        b.setLifeTime(700);
        assertEquals("Expected Lifetime to change", 700, b.getLifeTime());

        b.reduceLifeTime();
        assertEquals("Expected lifetime to reduce", 699, b.getLifeTime());

    }

}
