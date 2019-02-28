package codes;

/**
 *@author Team 7
 *@version 1.0
 *@since Feb. 19th, 2019
 */

import java.util.Scanner;

public class Tank extends GameEntity {

	public static int tankCount = 0; //The number of tanks on screen 
	private char ID; //ID character revealed on screen 
	private boolean isAlive = true; //State of object in game 

	/**
	 @param x: x-coordinate of the created tank
	 @param y: y-coordinate of the created tank 
	 */
	public Tank(int x, int y) {
		super(x, y); 
		tankCount++; //increasing tanks on screen
		ID = (char) (tankCount + '0'); //ID reflects tankcount 
	}

	/**
	 * Retrives the ID of the tank.
	 * 
	 * @return ID as a char
	 */
	public char getID() {
		return ID;
	}

	/**
	 * Retrieves the state of the tank.
	 * 
	 * @return boolean; true = alive; false = dead
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * Changes the state of the object to dead. Prints state to console.
	 */
	public void dies() {
		isAlive = false;
		tankCount--; //Reducing number of alive tanks 
		System.out.println("Tank " + ID + " died!");
	}

	/**
	 * Changes the state of the object to alive.
	 */
	public void revive() {
		isAlive = true;
		tankCount++;
	}

	/**
	 * Alters the position of the specified tank.
	 * 
	 * @param xDir: the intended xDirection of the movement
	 * @param yDir: the intended yDirection of the movement
	 * @param map: the map on which the object movement must take place.
	 *
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
	 * the @SuppressWarnings suppresses the warnings about leaving the shoot scanner
	 * open in order to remove the interference with the main scanner. The method
	 * shoot, creates a bullet based on player input and while there is an open
	 * space in front of it, moves incrementally with each display. If a bullet
	 * meets a tank, the tank's state is altered to dead. If bullet meets a wall,
	 * the bullet is absorbed.
	 * 
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
			map.display();

			System.out.println("Press ENTER to continue...");
			input.nextLine();
		}

		if (map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] == '#') { // Bullet hits a wall
			// When the tank is not beside the wall
			if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {
				map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			}
		} else if (map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] == otherTank.getID()) { // Bullet hits other
																									// tank
			otherTank.dies();
			if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {
				map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			}
			map.getCharMap()[bull.getY() + yDir][bull.getX() + xDir] = 'X';
			map.display();
		}

	}

	/**
	 * The actual gameplay. Based on the inputted command, the tank moves the
	 * designated spaces and shoots in the intended direction.
	 * 
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
