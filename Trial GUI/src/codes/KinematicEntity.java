package codes;

/**
 * This class is used to define the velocities of Tank and Bullet objects in TankRoyale
 * 
 * @author Team 7
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

    public void update() { // Called once per frame
        getView().setTranslateX(getView().getTranslateX() + velocity.getX());
        getView().setTranslateY(getView().getTranslateY() + velocity.getY());
    }

    public void setVelocity(Point2D velocity) {
        this.velocity = velocity;
    }

    public Point2D getVelocity() {
        return velocity;
    }

}

