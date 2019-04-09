package tests;

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
import javafx.scene.Node;
import javafx.scene.shape.*;

public class KinematicEntityTest extends FormatTester  {

  //Testing Class Name
	public static final String CLASSNAME = "KinematicEntity";
	public KinematicEntityTest() {
		super(CLASSNAME, false);
	}

  //Instance variable testing
  private void testInstanceVariables(){
		String[] vars1 = {"Point2D velocity"};
		assertTrue("Instance variables should be private with correct name [velocity] and type [Point2D].", instanceVariablesArePrivate(vars1));
	}


  //Method testing
@Test
public void initializationTest() {
  KinematicEntity t = new KinematicEntity(new Rectangle(10.0, 10.0));

  Point2D initial = new Point2D(0, 0);

  //Testing instance variable intialization values
  assertEquals("Velocity should be initalizaed to 0,0", initial, t.getVelocity());
}

//Testing setters and getters
@Test
public void setAndGetTest() {
  KinematicEntity t2 = new KinematicEntity(new Rectangle(10.0, 10.0));

  Point2D neww = new Point2D(6,0);

  //Testing the alive and dead of the object
  t2.setVelocity(neww);
  assertEquals("Expected velocity to change", neww, t2.getVelocity());

}

@Test
@SuppressWarnings( "deprecation" ) //not sure why it shows up, just trying to fix

public void updateTest() {
  //Creating first object
  KinematicEntity t3 = new KinematicEntity(new Rectangle(10.0, 10.0));

  //Testing the upate of KinematicEntity
  t3.update();
  double fooX = t3.getView().getTranslateX() + t3.getVelocity().getX();
  double fooY = t3.getView().getTranslateY() + t3.getVelocity().getY();
  assertEquals("Position x should be updated to reflect velocity vectors.", fooX, t3.getView().getTranslateX(), 0.00001);
  assertEquals("Position y should be updated to reflect velocity vectors.", fooY, t3.getView().getTranslateY(), 0.00001);
}



}
