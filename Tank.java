package codes;

import java.util.Scanner;

/** Creating a class blueprint of the type Tank
that extends from the superclass GameEntity.
*/
public class Tank extends GameEntity {

	/** Instance variables initializing the instance of type
	class as 0, the ID as a character that will be revealed
	on screen and creating the state of the object as alive.
	*/
	public static int tankCount = 0;
	private char ID;
	private boolean isAlive = true;

	/** Created a new tank with the specificed x and y coordinate.
	The tank is reflected in the map by calling on the superclass
	GameEntity. Everytime a tank is created, the tankcount is
	increased to dictate the number of active tanks and the
	ID is derived from that.
	*/
	public Tank(int x, int y) {
		super(x, y);
		tankCount++;
		ID = (char)(tankCount + '0');
	}

	/** Retrives the ID of the tank.
	@return ID as a char
	*/
	public char getID() {
		return ID;
	}

	/** Retrieves the state of the tank.
	@return boolean; true = alive; false = dead
	*/
	public boolean isAlive() {
		return isAlive;
	}

	/** Changes the state of the object to dead.
	Prints state to console.
	*/
	public void dies() {
		isAlive = false;
		System.out.println("Tank " + ID + " died!");
	}

	/** Alters the position of the specified tank.
	@param xDir: the intended xDirection of the movement
	@param yDir: the intended yDirection of the movement
	@param map: the map on which the object movement must take place.

	Checks if the move is valid, based on inputted map
	and intended movements. If move valid, the x and y
	coordinates of the tank are set. Else, an error
	message is printed to the console.
	*/
	public void move(int xDir, int yDir, Map map) {
		if(map.getCharMap()[getY()+yDir][getX()+xDir] == ' ') {
			map.getCharMap()[getY()+yDir][getX()+xDir] = ID;
			map.getCharMap()[getY()][getX()] = ' ';
			setX(getX()+xDir);
			setY(getY()+yDir);
		}
		else {
			System.out.println("Tank " + ID + " was unable to move in the specified direction!");
		}
	}

	/** the @SuppressWarnings suppresses the warnings about leaving
	the shoot scanner open in order to remove the interference with the main scanner.
	The method shoot, creates a bullet based on player input and
	while there is an open space in front of it, moves incrementally with each display.
	If a bullet meets a tank, the tank's state is altered to dead.
	If bullet meets a wall, the bullet is absorbed.

	@param xDir: the xdirection the bullet is intended to move.
	@param yDir: the ydirection the bullet is intended to move.
	@param map: the map the bullet and tanks are operating in.
	@param otherTank: the location of the other tank, in the event of it's death.
	*/
	@SuppressWarnings("resource")
	public void shoot(int xDir, int yDir, Map map, Tank otherTank){
	    Bullet bull = new Bullet(getX(), getY());
	    Scanner input = new Scanner(System.in);

	    if (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == ' ') {
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = Bullet.symbol;
			bull.setX(bull.getX()+ xDir);
			bull.setY(bull.getY()+ yDir);
			map.display();
	    }
		System.out.println("Press ENTER to continue...");
		input.nextLine();

	    while (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == ' ') {
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = Bullet.symbol;
			map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			bull.setX(bull.getX()+ xDir);
			bull.setY(bull.getY()+ yDir);
			map.display();

			System.out.println("Press ENTER to continue...");
			input.nextLine();
	    }

	    if(map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == '#') { // Bullet hits a wall
	    	// When the tank is not beside the wall
	    	if (map.getCharMap()[bull.getY()][bull.getX()] != ID) {
		    	map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			}
	    }
	    else if (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == otherTank.getID()) { // Bullet hits other tank
	        otherTank.dies();
	        if(map.getCharMap()[bull.getY()][bull.getX()] != ID){
	            map.getCharMap()[bull.getY()][bull.getX()] = ' ';
	        }
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = 'X';
	        map.display();
	    }

	}

	/** The actual gameplay. Based on the inputted command,
	the tank moves the designated spaces and shoots in the intended direction.
	@param command: the user input, as a string in all caps
	@param map: the map the tank is operating on
	@param otherTank: the location of the other tank, in the event that a user shoots
	*/
	public void performCommand(String command, Map map, Tank otherTank) {
		switch (command) {
		case "MOVEUP" :
			move(0, -1, map);
			break;
		case "MOVEDOWN" :
			move(0, 1, map);
			break;
		case "MOVELEFT" :
			move(-1, 0, map);
			break;

		case "MOVERIGHT" :
			move(1, 0, map);
			break;
		case "SHOOTUP" :
		    shoot(0, -1, map, otherTank);
			break;
		case "SHOOTDOWN" :
		    shoot(0, 1, map, otherTank);
			break;
		case "SHOOTLEFT" :
		    shoot(-1, 0, map, otherTank);
			break;
		case "SHOOTRIGHT" :
		    shoot(1, 0, map, otherTank);
			break;
		}
	}

}
