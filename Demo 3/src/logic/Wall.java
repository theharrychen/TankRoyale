package logic;
/**
 * This class is used to create the wall objects in TankRoyale's map
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */
import drivers.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends GameEntity {
    private double wallwidth = 0;
    private double wallheight = 0;
	private int alignment = 0; 
	
	/**
	 *Checks the parameters given to the super constructor to ensure valid walls are created
	 *@Param double width, double height, int alignment
	 */
	public static Rectangle checkParameters(double width, double height, int alignment){
		if(width <= MainGUI.WIDTH && height <= MainGUI.HEIGHT-100 && (alignment == 0 || alignment == 1))
			return new Rectangle(width, height, Color.gray(0.5));
		return new Rectangle(0, 0, Color.gray(0.5));
	}
	
	/**
	 *@Param double width, double height, int alignment
	 *vertical wall alignment is 0, horizontal wall alignment is 1
	 */
    public Wall(double width, double height, int alignment){
		super(checkParameters(width,height,alignment));
		if(width <= MainGUI.WIDTH && height <= MainGUI.HEIGHT-100 && (alignment == 0 || alignment == 1)){
			setWallWidth(width);
			setWallHeight(height);
			setWallAlignment(alignment);
    }
	}
	
	/**
	 *Sets the wall width
	 *@param double width which must be less than the width of the game screen
	 */
	public void setWallWidth(double width){
		if(width <= MainGUI.WIDTH){
			this.wallwidth = width;
	}
	}
	
	/**
	 *Sets the wall height
	 *@param double height which must be 100 pixels less than the height of the game screen
	 */
	public void setWallHeight(double height){
		if(height <= MainGUI.HEIGHT-100){
			this.wallheight = height;
	}
	}
	
	/**
	 *Sets the wall aligment
	 *@param double alignment which must be 0 or 1
	 * 0 represents vertical wall, 1 represents horizontal wall
	 */
	public void setWallAlignment(int alignment){
		if(alignment == 0 || alignment == 1){
		this.alignment = alignment;
	}
	}
	
	/**
	 *@return width
	 */
    public double getWallWidth() {
        return wallwidth;
    }
	
	/**
	 *@return height
	 */
    public double getWallHeight() {
        return wallheight;
    }
	
	/**
	 *@return alignment
	 */
	public int getWallAlignment(){
		return alignment;
	}

}
