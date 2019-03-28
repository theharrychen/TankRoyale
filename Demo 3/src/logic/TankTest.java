package logic;

/**
 * This class is used to test the Bullet Logic Class
 *
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-28
 */

import drivers.*;

import static org.junit.Assert.*;
import java.util.*;
import logic.*;
import javafx.scene.shape.*;
import org.junit.Test;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class TankTest extends FormatTester  {

  //Testing Class Name
	public static final String CLASSNAME = "Tank";
	public TankTest() {
		super(CLASSNAME, false);
	}

  //Instance variable testing
  private void testInstanceVariables(){
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

	}


  //Method testing
@Test
public void initializationTest() {
  Tank t = new Tank();

  //Testing instance variable intialization values
  t.getFacing();
  t.getMoveDir();
  t.getShooting();

  assertEquals("Facing should be initialized to (1,0)", new Point2D(1,0), t.getFacing());
  assertEquals("movDir should be initialized to 1", 1, t.getMoveDir());
  assertEquals("shooting should be initialized to false", false, t.getShooting());
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
