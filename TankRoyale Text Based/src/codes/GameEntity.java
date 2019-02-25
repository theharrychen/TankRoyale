package codes;
/**
 *The base class inherited by all TankRoyale entities
 *@author Team 7
 *@version 0.1
 *@since Feb. 19th, 2019
 */
public class GameEntity {
	private int x, y;
	
	/**
	*Creates a new GameEntity with coordinates x and y
	@param int x
	@param int y
	*/
	public GameEntity(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/**
	*@return int x
	*/
	public int getX() {
		return x;
	}
	
	/**
	*sets the x coordinate
	*/
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	*@return int y
	*/
	public int getY() {
		return y;
	}
	
	/**
	*sets the y coordinate
	*/
	public void setY(int y) {
		this.y = y;
	}
	
}
