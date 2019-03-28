package logic;

import drivers.*;

import static org.junit.Assert.*;
import java.util.*;
import logic.*;
import org.junit.Test;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class WallTest{
	
	//Tests proper construction of wall object
	@Test
	public void test_wallconstructor(){
			Wall w = new Wall(5,5,0);
			Wall w2 = new Wall(5,5,1);
			assertEquals("Unexpected width of created wall",5, w.getWallWidth(),0.001);
			assertEquals("Unexpected height of created wall",5, w.getWallHeight(),0.001);
			assertEquals("Unexpected vertical alignment of created wall",0, w.getWallAlignment());
			assertEquals("Unexpected horizontal alignment of created wall",1, w2.getWallAlignment());
	}
	
	//Testing getter and setters
	@Test
	public void test_setWallWidth(){
			Wall w = new Wall(5,5,0);
			w.setWallWidth(7.0);
			assertEquals("Set wall width to 7.0. Unexpected wall width value",7.0, w.getWallWidth(),0.001);
			w.setWallWidth(MainGUI.WIDTH + 100);
			assertEquals("Set invalid width of wall. Wall width should be less than the width of the game window",7.0, w.getWallWidth(),0.001);
			
	}
	
	@Test
	public void test_setWallHeight(){
		Wall w = new Wall(5,5,0);
			w.setWallHeight(7.0);
			assertEquals("Set wall height to 7.0. Unexpected wall height value",7.0, w.getWallHeight(),0.001);
			w.setWallHeight(MainGUI.HEIGHT);
			assertEquals("Set invalid height of wall. Wall height should be 100 pixels less than the height of the game window",7.0, w.getWallHeight(),0.001);
	}
	
	@Test
	public void test_setAlignment(){
		Wall w = new Wall(5,5,0);
			w.setWallAlignment(1);
			assertEquals("Unexpected wall alignment",1, w.getWallAlignment());
			w.setWallAlignment(0);
			assertEquals("Unexpected wall alignment",0, w.getWallAlignment());
			w.setWallAlignment(8);
			assertEquals("Set invalid wall alignment",0, w.getWallAlignment());
	}
	
	@Test
	public void test_getWallWidth(){
		Wall w = new Wall(5,5,0);
		assertEquals("Unexpected return value for wall width",5, w.getWallWidth(),0.001);
	}
	
	@Test
	public void test_getWallHeight(){
		Wall w = new Wall(5,5,0);
		assertEquals("Unexpected return value for wall height",5, w.getWallHeight(),0.001);
	}
	
	@Test
	public void test_getAlignment(){
		Wall w = new Wall(5,5,1);
		assertEquals("Unexpected return value for wall alignment",1, w.getWallAlignment());
	}
	
	//Tests 
	@Test
	public void test_invalidwallalignment(){
			Wall w = new Wall(5,5,8);
			assertEquals("Invalid alignment of the wall. Wall alignment must be 0 or 1",0, w.getWallAlignment());
	}
	
	@Test
	public void test_invalidwidth(){
			Wall w = new Wall(MainGUI.WIDTH + 100,5,8);
			assertEquals("Invalid width of the wall. Width must be less than the game window's width",0, w.getWallWidth(),0.001);
	}
	
	@Test
	public void test_invalidheight(){
			Wall w = new Wall(MainGUI.HEIGHT,5,8);
			assertEquals("Invalid height of the wall. Height must be 100 pixles less than the game window's height",0, w.getWallHeight(),0.001);
	}
	
	/**
	@Test
	public void test_checkParameters(){
		Wall w = new Wall(5,5,0);
		Rectangle valid = new Rectangle(5, 5, Color.gray(0.5));
		Rectangle invalid = new Rectangle(0, 0, Color.gray(0.5));
		assertTrue("Unexpected rectangle returned after checking valid wall constructor parameters", valid.equals(w.checkParameters(5.0,5.0,0)));
		assertTrue("Unexpected rectangle returned after checking invalid width in wall constructor parameters ", invalid.equals(w.checkParameters(MainGUI.WIDTH + 100,5,0)));
		assertTrue("Unexpected rectangle returned after checking invalid height in wall constructor parameters", invalid.equals(w.checkParameters(5,MainGUI.HEIGHT,0)));
		assertTrue("Unexpected rectangle returned after checking invalid alignment wall constructor parameters", invalid.equals(w.checkParameters(5,5,8)));
	}
	*/
}