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
	
	/**
	private double width;
	private double height;
	
	public void setWidth(double width){
		this.width = width;
	}
	
	public void setHeight(double height){
		this.height = height;
	}

	public double getWidth(){
		return this.width;
	}
	public double getHeight(){
		return this.height;
	}
	*/
	
    //Contructor: Creates a GameEntity for GameGUI version of the game
    public GameEntity(Node view) {
        setView(view);
    }

	 //Constructor for text based version of the game
    public GameEntity(int x, int y){
        this.x = x;
        this.y = y;
    }

	/**
	 * @return int x
	 */
	public int getX() {
		return x;
	}

	/**
	 * sets the x coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return int y
	 */
	public int getY() {
		return y;
	}

	/**
	 * sets the y coordinate
	 */
	public void setY(int y) {
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
        return view;
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
	// This is from https://stackoverflow.com/questions/15013913/checking-collision-of-shapes-with-javafx
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

	/**
	public boolean isCollidingSAT(GameEntity other){
		return SAT.isColliding(this, other);
	}
	
	//May need to move this
	//Can only be used on Tank and Wall
	public Point2D center(){
		double x = this.getView().getTranslateX() + getWidth()/2.0; //center x coordinate of gameentity
		double y = this.getView().getTranslateY() + getHeight()/2.0; // center y coordinate of gameentity
		return new Point2D(x,y);
	}
	
	//Return the corners for the boundary box
	public List<Point2D> cornerVectors(){
		double x = this.getView().getTranslateX();
		double y = this.getView().getTranslateY();
		double w = this.getWidth();
		double h = this.getHeight();
		return Arrays.asList(
			new Point2D(x,y),
			new Point2D( x + w, y),
			new Point2D( x + w, y + h),
			new Point2D( x,y + h)
		)
			.stream()
			.map(v -> v.subtract(center()))//v for vector
			.collect(Collectors.toList());
		
	}
	
	public List<Point2D> corners(){
		return cornerVectors().stream()
			.map(v -> new Point2D(
			v.getX() * cos(getRotation()) - v.getY() * sin(getRotation()),
			v.getX() * sin(getRotation()) + v.getY() * cos(getRotation())
			)
			)
			.map(v -> v.add(center()))
			.collect(Collectors.toList());
			
			// if this doesn't work check out 13:11 of video
	}
	private static double cos(double angle){
		return Math.cos(Math.toRadians(angle));
	}
	
	private static double sin(double angle){
		return Math.sin(Math.toRadians(angle));
	}
	*/
	

	

}
