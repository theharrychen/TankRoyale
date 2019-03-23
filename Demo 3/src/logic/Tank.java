package logic;

/**
 * This tank class is responsible for implementing the behaviour of a tank object
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */
 
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tank extends KinematicEntity {
    private Point2D facing = new Point2D(1,0);
    private int moveDir = 1; //1 means it last moved forward, -1, means it last moved backward
    private boolean isRotateRight = false, isRotateLeft = false;
	private boolean up, down, left, right;
	private boolean shooting = false;
	
	public boolean getShooting(){
		return this.shooting;
	}
	
	public void setShooting(boolean shoot){
		this.shooting = shoot;
	}

	/**
	 *Constructs a Tank object
	 */
    public Tank(){
        super(new Rectangle(40,30, Color.rgb(Game.rng(0,255),Game.rng(0,255),Game.rng(0,255))));
    }

	/**
	 * Updates position
	 */
    public void update() {
        if(isRotateRight){
            rotate(5);
            isRotateRight = false;
        }
        else if (isRotateLeft){
            rotate(-5);
            isRotateLeft = false;
        }
		movement();
    }

	/**
	 * Sets the direction the front of the tank is facing
	 *@param Point2D facing
	 */
    public void setFacing(Point2D facing) {
        this.facing = facing;
    }

	/**
	 *@return the direction the front of the tank is facing
	 */
    public Point2D getFacing() {
        return facing;
    }

	/**
	 * Moves the tank
	 *@param double direction, double magnitude
	 */
    private void move(double direction, double magnitude) {
        setVelocity(getFacing().normalize().multiply(direction*magnitude));
		super.update();
	}

	/**
	 *Moves the tank forward
	 */
    public void moveForward() {
        moveDir = 1;
        move(1, 2);
    }
	
	/**
	 *Moves the tank backwards
	 */
    public void moveBackward() {
        moveDir = -1;
        move(-1, 2);
    }

    /**
	 *Angle of rotation is measured in degrees
	 *@return rotation angle
	 */
    private double getRotate() {
        return getView().getRotate();
    }

	/**
	 *Angle of rotation is measured in degrees
	 *@return horizontal vector for angle of rotation
	 */
    private double getRotateToX(){
        return Math.cos(Math.toRadians(getView().getRotate()));
    }

	/**
	 *Angle of rotation is measured in degrees
	 *@return vertical vector for angle of rotation
	 */
    private double getRotateToY(){
        return Math.sin(Math.toRadians(getView().getRotate()));
    }

	/**
	 *Rotates the tank in specified degrees
	 */
    private void rotate(double degrees){
        getView().setRotate(getView().getRotate() + degrees);
        setFacing(new Point2D(getRotateToX(), getRotateToY()));
        setVelocity(new Point2D(Math.cos(Math.toRadians(getView().getRotate())), Math.sin(Math.toRadians(getView().getRotate()))));
    }

	/**
	 *Rotates the tank clockwise
	 */
    public void rotateRight() {
        isRotateRight = true; // Makes rotation dependent on timer
    }

	/**
	 *Rotates the tank counter clockwise
	 */
    public void rotateLeft() {
        isRotateLeft = true; // Makes rotation dependent on timer
    }

	/**
	 *@return direction of tank movement
	 */
    public int getMoveDir() {
        return moveDir;
    }
	
	/**
	 *setUp, setDown, setRight,and setLeft account for multiple keyboard inputs
	 */
    public void setUp(boolean up){
        this.up = up;
    }

    public void setDown(boolean down){
        this.down = down;
    }

    public void setRight(boolean right){
        this.right = right;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

	/**
	 *Moves the tank based on player input
	 */
    public void movement(){
        if(up){
            moveForward();
        }
        if(down){
            moveBackward();
        }
        if(right){
            rotateRight();
        }
        if(left)
            rotateLeft();
    }
	public void stop(){
		setUp(false);
		setDown(false);
		setRight(false);
		setLeft(false);
		}
	
	  /**
	 * Tank creates and shoots a bullet
	 * Bullet is currently generated in front of the tank, so it doesn't self-destruct
	 *@param Tank tank
	 */
    public Bullet shoot(){
        Bullet bullet = new Bullet();
        bullet.setVelocity(this.getFacing().normalize().multiply(5));
		return bullet;
    }
	
	
}
