package codes;

/**
 * This class is used to generate a bullet or project to be shot by the tank.
 * The class extends GameEntity which tracks the bullets x and y coordinates in
 * the generated grid/array.
 * 
 * @author Team 7
 * @version 1.0
 * @since 2019-02-19
 */
public class Bullet extends GameEntity {

	public static final char symbol = '*';

	/**
	 * This constructor generates an object of type Bullet, creating the character
	 * '*' at the specified x and y coordinates.
	 * 
	 * @param x the x coordinate of the bullet in the array/grid.
	 * @param y the y coordinate of the bullet in the array/grid.
	 */
	public Bullet(int x, int y) {
		super(x, y);
	}
}
