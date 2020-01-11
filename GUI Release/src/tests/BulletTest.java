package tests;

/**
 * This class is used to test the Bullet Logic Class
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-28
 */

import logic.Bullet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    /**
     * Testing the constructor of Bullet for the text based version
     */
    @Test
    public void test_bulletconstructor() {
        Bullet b = new Bullet(2, 3);
        assertEquals("Unexpected x coordinate value", 2, b.getX(), 0.001);
        assertEquals("Unexpected y coordinate value", 3, b.getY(), 0.001);

    }

    @Test
    public void testLifeTime() {
        Bullet b = new Bullet();

        //Testing setters and getters still exist
        String[] vars2 = {"getLifeTime"};
        assertTrue("Private instance variables should have appropriate getters", instanceVariablesArePrivate(vars2));
        String[] vars3 = {"setLifeTime"};
        assertTrue("Private instance variables should have appropriate setters", instanceVariablesArePrivate(vars3));

        //Testing the accuracy of setters and getters.
        b.setLifeTime(700);
        assertEquals("Expected Lifetime to change", 700, b.getLifeTime(), 0.001);

        b.reduceLifeTime();
        assertEquals("Expected lifetime to reduce", 699, b.getLifeTime(), 0.001);

    }

    @Test
    public void testRadius() {
        Bullet b = new Bullet();

        //Testing setters and getters still exist
        String[] vars2 = {"getRadius"};
        assertTrue("Private instance variables should have appropriate getters", instanceVariablesArePrivate(vars2));
        String[] vars3 = {"setRadius"};
        assertTrue("Private instance variables should have appropriate setters", instanceVariablesArePrivate(vars3));

        //Testing the accuracy of setters and getters.
        b.setRadius(3);
        assertEquals("Set bullet radius to 3. Unexpected bullet radius", 3, b.getLifeTime());

        b.setRadius(4);
        assertEquals("Unexpected bullet radius", 4, b.getLifeTime());

    }


}

