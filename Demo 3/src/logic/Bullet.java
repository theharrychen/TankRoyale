package logic;

/**
 * This class is used to generate a bullet or projectile to be shot by the tank.
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bullet extends KinematicEntity {
	//Makes the bullet temporary
   private int lifeTime = 300; // in milliseconds

	/**
	 *Constructs a bullet object
	 */
   public Bullet(){
        super(new Circle(5, 5, 5, Color.BLACK));
    }

	/**
	 * @return lifeTime of bullet
	 */
    public int getLifeTime(){
        return lifeTime;
    }

	/**
	 *set the lifetime of the bullet
	 *@param int lifeTime
	 */
    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

	/**
	 * reduces the Lifetime of the bullet
	 */
    public void reduceLifeTime(){
        lifeTime--;
    }
}
