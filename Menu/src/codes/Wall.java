package codes;

/**
 * This class is used to create the wall objects in TankRoyale's map
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */
 
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends GameEntity {
    private double width;
    private double height;
	private int alignment; 
	
	/**
	 *@Param double width, double height, int alignment
	 *vertical wall alignment is 0, horizontal wall alignment is 1
	 */
    public Wall(double width, double height, int alignment) {
        super(new Rectangle(width, height, Color.gray(0.5)));
        this.width = width;
        this.height = height;
		this.alignment = alignment;
    }

	/**
	 *@return width
	 */
    public double getWidth() {
        return width;
    }
	
	/**
	 *@return height
	 */
    public double getHeight() {
        return height;
    }
	
	/**
	 *@return alignment
	 */
	public int getAlignment(){
		return alignment;
	}

}