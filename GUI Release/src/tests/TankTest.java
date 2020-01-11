package tests;

/**
 * This class is used to test the Bullet Logic Class
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-28
 */

import javafx.geometry.Point2D;
import logic.Tank;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TankTest extends FormatTester {

    //Testing Class Name
    public static final String CLASSNAME = "Tank";

    public TankTest() {
        super(CLASSNAME, false);
    }

    //Instance variable testing
    private void testInstanceVariables() {
        String[] vars1 = {"Point2D facing"};
        assertTrue("Instance variables should be private with correct name [facing] and type [Point2D].", instanceVariablesArePrivate(vars1));

        String[] vars2 = {"int moveDir"};
        assertTrue("Instance variables should be private with correct name [moveDir] and type [int].", instanceVariablesArePrivate(vars2));

        String[] vars3 = {"boolean isRotateRight"};
        assertTrue("Instance variables should be private with correct name [isRotateRight] and type [boolean].", instanceVariablesArePrivate(vars3));

        String[] vars4 = {"boolean isRotateLeft"};
        assertTrue("Instance variables should be private with correct name [isRotateLeft] and type [boolean].", instanceVariablesArePrivate(vars4));

        String[] vars5 = {"boolean up, down, left, right"};
        assertTrue("Instance variables should be private with correct name [up, down, left, right] and type [boolean].", instanceVariablesArePrivate(vars5));

        String[] vars6 = {"boolean shooting"};
        assertTrue("Instance variables should be private with correct name [shooting] and type [boolean].", instanceVariablesArePrivate(vars6));

        String[] vars7 = {"int tankRadius"};
        assertTrue("Instance variables should be private with correct name [tankRadius] and type [int].", instanceVariablesArePrivate(vars7));

        String[] vars8 = {"int tankCount"};
        assertTrue("Instance variables should be private with correct name [tankRadius] and type [int].", instanceVariablesArePrivate(vars8));

    }

    //Testing getters and setters for movement
    @Test
    public void test_Movement() {
        Tank t = new Tank();
        t.setUp(true);
        assertEquals("Tank should be moving up", true, t.getUp());
        t.setDown(true);
        assertEquals("Tank should be moving down", true, t.getDown());
        t.setRight(true);
        assertEquals("Tank should be moving up", true, t.getRight());
        t.setLeft(true);
        assertEquals("Tank should be moving down", true, t.getLeft());
    }

    //Testing getters and setters for radius
    @Test
    public void test_Radius() {
        Tank t = new Tank();
        assertEquals("Initial tank radius should be 20", 20, t.getRadius());
        t.setRadius(80);
        assertEquals("Set tank radius to 80", 80, t.getRadius());
    }

    //Testing getters and setters for shooting
    @Test
    public void test_Shooting() {
        Tank t = new Tank();
        t.setShooting(true);
        assertEquals("Tank should be shooting", true, t.getShooting());
        t.setShooting(false);
        assertEquals("Tank should not be shooting", false, t.getShooting());
    }

    //Testing getters and setters for ID
    @Test
    public void test_ID() {
        Tank t = new Tank();
        t.setID('A');
        assertEquals("Character of tank in text based was set to 'A'", 'A', t.getID());
        t.setID('1');
        assertEquals("Character of tank in text based was set to '1'", '1', t.getID());
    }

    //Testing getters and setters for tankcount
    @Test
    public void test_tankCount() {
        Tank t = new Tank();
        t.setTankCount(2);
        assertEquals("number of tanks should be 2", 2, t.getTankCount());
    }

    //Method testing
    @Test
    public void initializationTest() {
        Tank t = new Tank();

        //Testing instance variable intialization values
        t.getFacing();
        t.getMoveDir();
        t.getShooting();

        assertEquals("movDir should be initialized to 1", 1, t.getMoveDir());
        assertEquals("shooting should be initialized to false", false, t.getShooting());
    }

    //test text-based constructor
    @Test
    public void test_TextConstructor() {
        Tank t = new Tank(5, 7);
        t.setTankCount(1);
        assertEquals("Unexpected x coordinate value", 5, t.getX());
        assertEquals("Unexpected y coordinate value", 7, t.getY());
        assertEquals("Unexpected tank count", 1, t.getTankCount());
        assertEquals("Unexpected ID", 3, '1', t.getID());
    }

    //test text-based version die
    @Test
    public void test_dies() {
        Tank t = new Tank(5, 7);
        t.setTankCount(1);
        t.dies();
        assertEquals("Tank should be dead", false, t.getAlive());
        assertEquals("Unexpected tank count. Should have reduced by one", 0, t.getTankCount());

    }

    //test text-based version revive
    @Test
    public void test_revive() {
        Tank t = new Tank(5, 7);
        t.setTankCount(1);
        t.dies();
        t.revive();
        assertEquals("Tank should be alive", true, t.getAlive());
        assertEquals("Unexpected tank count", 1, t.getTankCount());

    }

    @Test
    public void shootingTest() {
        Tank t = new Tank();

        //Shooting Test
        t.setShooting(true);
        assertEquals("Expected true after setting shooting", true, t.getShooting());

        //Testing the position and front of the tank
        Point2D p = new Point2D(3, 5);
        t.setFacing(p);
        assertEquals("Expected the front of the tank to be updated.", p, t.getFacing());

    }

    @Test
    public void movementTest() {
        Tank t = new Tank();

        //Testing the movement commands
        t.setDown(true);
        t.movement(); //if movement occurs, then moveDir, rotateRight, and facing should change
        t.moveBackward();

        assertEquals("moveDir should be -1 because of backwards movement", -1, t.getMoveDir());
    }

    @Test
    public void rotationTest() {
        Tank t = new Tank();

        //Testing the turning Methods
        double foo = t.getView().getRotate();
        t.setDown(true);
        t.update();
        Point2D rotateP = new Point2D(Math.cos(Math.toRadians(foo)), Math.sin(Math.toRadians(foo)));
        assertEquals("Object should be facing a new Point2D that is dependant on the rotation of the movement", rotateP, t.getFacing());


        //Testing there is a way to stop everything
        t.stop();
        assertEquals("Expected flase after stopping all movement", false, t.getUp());
        assertEquals("Expected flase after stopping all movement", false, t.getDown());
        assertEquals("Expected flase after stopping all movement", false, t.getRight());
        assertEquals("Expected flase after stopping all movement", false, t.getLeft());
    }

}
