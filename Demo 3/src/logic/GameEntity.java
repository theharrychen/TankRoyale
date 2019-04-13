package logic;

/**
 * This class is used to generate a bullet or project to be shot by the tank.
 * The class extends GameEntity which tracks the bullets x and y coordinates in
 * the generated grid/array.
 *
 * @author Team 7
 * @version 1.0
 * @since 2019-02-19
 */

import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.transform.Rotate;
import javafx.geometry.Point2D;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.geom.AffineTransform;
import javafx.scene.shape.Shape;



public class GameEntity { // By default a physically "static" object

    private Node view; //An item in the scene graph
    private boolean alive = true;
	  private int x, y; //Variables for text based version


    //Contructor: Creates a GameEntity for GameGUI version of the game
    public GameEntity(Node view) {
        setView(view);
    }

	 //Constructor for text based version of the game
    public GameEntity(int x, int y){
        setX(x);
        setY(y);
    }

	/**
	 * @return int x
	 */
	public int getX() {
    Integer tempX = new Integer(x); //Peventing leaks, present on GUI as well.
		return tempX.intValue(); //Peventing leaks, present on GUI as well.
	}

	/**
	 * sets the x coordinate
	 */
	public void setX(int x) {
    if(x < 0){
      //S AFE CRASH
    }
		this.x = x;
	}

	/**
	 * @return int y
	 */
	public int getY() {
    Integer tempY = new Integer(y); //Peventing leaks, present on GUI as well.
		return tempY.intValue(); //Preventing leaks, present on GUI as well.
	}

	/**
	 * sets the y coordinate
	 */
	public void setY(int y) {
    if(y < 0){
      //S AFE CRASH
    }
		this.y = y;
	}

    /**
     *Updates the game Node to the new position
     *@param view: intended new view
    */
    public void setView(Node view) {
        this.view = view;
    }

    //Returns the Node (possible privacy leak)
    public Node getView() {
        return view; //Required access as there is no Node copy constructor. Attempt to create was buggy and memory heavy.
    }

	public double getRotation() {
        return this.getView().getRotate();
    }

    /**
     *Updates current state to alive.
     *@param alive: new intended state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //Setting the state to alive
    public boolean isAlive() {
        return alive;
    }

    //Returns false if static is dead
    public boolean isDead() {
        return !alive;
    }

    //Checks if any two objects are at the same position -> therefore colliding.
	// This is adapted from https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx
	public boolean isColliding(GameEntity other) {
		boolean collisionDetected = false;
		Shape object1 = (Shape) this.getView();
		Shape object2 = (Shape) other.getView();
		Shape intersect = Shape.intersect(object1, object2);
        if (intersect.getBoundsInLocal().getWidth() != -1) {
          collisionDetected = true;
        }
		return collisionDetected;
	}



}
