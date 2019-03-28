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

public class GameEntityTest extends FormatTester  {

  //Testing Class Name
	public static final String CLASSNAME = "GameEntity";
	public GameEntityTest() {
		super(CLASSNAME, false);
	}

  //Instance variable testing
  private void testInstanceVariables(){
		String[] vars1 = {"Node view"};
		assertTrue("Instance variables should be private with correct name [view] and type [Node].", instanceVariablesArePrivate(vars1));

    String[] vars2 = {"boolean alive"};
		assertTrue("Instance variables should be private with correct name [alive] and type [boolean].", instanceVariablesArePrivate(vars2));
	}


  //Method testing
@Test
public void initializationTest() {
  GameEntity t = new GameEntity(new Rectangle(10.0, 10.0));

  //Testing instance variable intialization values
  assertEquals("Alive state should be initialized to true", true, t.isAlive());
}

//Testing setters and getters
@Test
public void setAndGetTest() {
  GameEntity t2 = new GameEntity(new Rectangle(10.0, 10.0));

  //Set and getting temp node
  t2.getView().setTranslateX(5.0);
  t2.getView().setTranslateY(5.0);
  Circle temp = new Circle(4);
  t2.setView(temp);
  assertEquals("Expected view to be changed", temp, t2.getView());

  //Testing the alive and dead of the object
  t2.setAlive(false);
  assertEquals("Expected the object state to be set to dead.", false, t2.isAlive());
  assertEquals("Expected the reverse of object state to be set to alive.", true, t2.isDead());
}

@Test
public void collisionTest() {
  //Creating first object
  GameEntity us = new GameEntity(new Circle(6.0, 6.0, 4.0));

  //Creating second object
  GameEntity them = new GameEntity(new Circle(20.0, 10.0, 4.0));
	GameEntity collid = new GameEntity(new Circle(6.0, 6.0, 4.0));

  //Testing no collision
  assertEquals("Collision should not be occuring", false, us.isColliding(them));

  //Testing a full collision
	them.getView().setTranslateX(10);
  assertEquals("Collision should be occuring", true, us.isColliding(collid));
}








}
