package logic;

/**
 * This class is used to define the velocities of Tank and Bullet objects in TankRoyale and by extension, 
 any non-Static object.
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-02-19
 */
 
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class KinematicEntity extends GameEntity {

    private Point2D velocity = new Point2D(0,0);

    public KinematicEntity(Node view) {
        super(view);
    }

    /**
     * Constructor for text based version of the game
     */
    public KinematicEntity(int x, int y){
        super(x, y);
    }

    //Continually updates the position of the moving object through the Nodial positions 
    public void update() { // Called once per frame
        getView().setTranslateX(getView().getTranslateX() + velocity.getX());
        getView().setTranslateY(getView().getTranslateY() + velocity.getY());
    }

    /**
     *Updates the velocity to new object 
     *@param velocity: new velocity of the Point2D object 
     */
    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

   //Returns the velocity of the dynamic object.
    public Point2D getVelocity() {
        return velocity;
    }

}

