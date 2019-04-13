package logic;

/**
 * This tank class is responsible for implementing the behaviour of a tank object
 *
 * @author Group 7
 * @version 2.0
 * @since 2019-03-06
 */

import visuals.*;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.Scanner;

import javafx.scene.shape.Circle;

public class Tank extends KinematicEntity {
    private Point2D facing = new Point2D(1, 0);
    private int moveDir = 1; //1 means it last moved forward, -1, means it last moved backward
    private boolean isRotateRight = false, isRotateLeft = false;
    private boolean up, down, left, right;
    private boolean shooting = false;
    private static int tankradius = 20;
    //Variables for text based version of the game
    private char ID; //ID revealed on screen for each tank
    private static int tankCount; //The number of tanks on screen
    private TextBasedDisplay display = new TextBasedDisplay();

    //Adding the getter methods for the up, down, left, right
    public boolean getUp() {
        return this.up;
    }

    public boolean getDown() {
        return this.down;
    }

    public boolean getRight() {
        return this.right;
    }

    public boolean getLeft() {
        return this.left;
    }

    /**
     * @return Radius of the tank
     */
    public int getRadius() {
        return this.tankradius;
    }

    /**
     * @return boolean shooting
     */
    public boolean getShooting() {
        return this.shooting;
    }

    /**
     * Sets whether the tank is shooting
     * @param shoot
     */
    public void setShooting(boolean shoot) {
        this.shooting = shoot;
    }

    /**
     * Retrives the ID of the tank.
     * @return ID as a char
     */
    public char getID() {
        return ID;
    }


    /**
     *Constructs a Tank object for the GameGUI version
     */
    public Tank() {
        super(new Circle(tankradius));
    }

    /**
     * Constructs a Tank object for the text based version
     */
    public Tank(int x, int y) {
        super(x, y);
        tankCount++; //Increases number of tanks on screen
        ID = (char) (tankCount + '0'); //ID reflects tank count

    }

    /**
     * Changes state of the object to dead for text based version
     */
    public void dies() {
        setAlive(false);
        tankCount--; //Reduces number of tanks alive
        System.out.println("Tank " + ID + " died!");
    }

    /**
     * Changes the state of the object to alive for the text based version
     */
    public void revive() {
        setAlive(true);
        tankCount++;
    }


    /**
     * Updates position
     */
    public void update() {
        if (isRotateRight) {
            rotate(5);
            isRotateRight = false;
        } else if (isRotateLeft) {
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
        setVelocity(getFacing().normalize().multiply(direction * magnitude));
        super.update();
    }

    /**
     * Moves the tank for text based verion of the game
     * @param int xDir the x direction
     * @param int yDir the y direction
     * @param map the current map
     */
    public void move(int xDir, int yDir, Map map) {
        if (map.getCharMap()[getY() + yDir][getX() + xDir] == ' ') { //checks if move is valid
            map.getCharMap()[getY() + yDir][getX() + xDir] = ID;
            map.getCharMap()[getY()][getX()] = ' ';
            setX(getX() + xDir);
            setY(getY() + yDir);
        } else {
            System.out.println("Tank " + ID + " was unable to move in the specified direction!");
        }
    }

    /**
     *Moves the tank forward
     */
    public void moveForward() {
        moveDir = 1;
        move(1, 3);
    }

    /**
     *Moves the tank backwards
     */
    public void moveBackward() {
        moveDir = -1;
        move(-1, 3);
    }

    /**
     *Angle of rotation is measured in degrees
     *@return rotation angle
     */
    // not encapsulated
    public double getRotate() {
        return getView().getRotate();
    }

    /**
     *Angle of rotation is measured in degrees
     *@return horizontal vector for angle of rotation
     */
    public double getRotateToX() {
        return Math.cos(Math.toRadians(getView().getRotate()));
    }

    /**
     *Angle of rotation is measured in degrees
     *@return vertical vector for angle of rotation
     */
    public double getRotateToY() {
        return Math.sin(Math.toRadians(getView().getRotate()));
    }

    /**
     *Rotates the tank in specified degrees
     */
    public void rotate(double degrees) {
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
    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     *Moves the tank based on player input
     */
    public void movement() {
        if (up) {
            moveForward();
        }
        if (down) {
            moveBackward();
        }
        if (right) {
            rotateRight();
        }
        if (left)
            rotateLeft();
    }

    public void stop() {
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
    public Bullet shoot() {
        Bullet bullet = new Bullet();
        bullet.setVelocity(this.getFacing().normalize().multiply(4));
        return bullet;
    }

    /**
     * Creates a bullet and shoots it in the indicated direction.
     * @param xDir: the xdirection the bullet is intended to move.
     * @param yDir: the ydirection the bullet is intended to move.
     * @param map: the map the bullet and tanks are operating in.
     * @param otherTank: the location of the other tank, in the event of it's death.
     */

    @SuppressWarnings("resource")
    public void shoot(int xDir, int yDir, Map map, Tank otherTank) {
        Bullet bull = new Bullet(getX(), getY()); //creates a bullet
        Scanner input = new Scanner(System.in);

        while (map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] == ' ') { //if open space, bullet moves
            if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {// Removes the previous appearance of the bullet
                map.getCharMap()[bull.getY()][bull.getX()] = ' ';
            }
            map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] = Bullet.symbol;

            bull.setX(bull.getX() + xDir);
            bull.setY(bull.getY() + yDir);
            display.display(map);

            System.out.println("Press ENTER to continue...");
            input.nextLine();
        }

        if (map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] == '#') { // Bullet hits a wall
            // When the tank is not beside the wall
            if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {
                map.getCharMap()[bull.getY()][bull.getX()] = ' ';
            }
        } else if (map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] == otherTank.getID()) { // Bullet hits other tank
            otherTank.dies();
            if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {
                map.getCharMap()[bull.getY()][bull.getX()] = ' ';
            }
            map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] = 'X';
            display.display(map);
        }
    }

    /**
     * The actual gameplay. Based on the inputted command, the tank moves the
     * designated spaces and shoots in the intended direction.
     * @param command: the user input, as a string in all caps
     * @param map: the map the tank is operating on
     * @param otherTank: the location of the other tank, in the event that a user
     *        shoots
     */
    public void performCommand(String command, Map map, Tank otherTank) {
        switch (command) {
            case "MOVEUP":
                move(0, -1, map);
                break;
            case "MOVEDOWN":
                move(0, 1, map);
                break;
            case "MOVELEFT":
                move(-1, 0, map);
                break;
            case "MOVERIGHT":
                move(1, 0, map);
                break;
            case "SHOOTUP":
                shoot(0, -1, map, otherTank);
                break;
            case "SHOOTDOWN":
                shoot(0, 1, map, otherTank);
                break;
            case "SHOOTLEFT":
                shoot(-1, 0, map, otherTank);
                break;
            case "SHOOTRIGHT":
                shoot(1, 0, map, otherTank);
                break;
        }
    }
}
