package codes;

/**
 * This class is used to generate a bullet or project to be shot by the tank.
 * The class extends KinematicEntity which tracks the bullets x and y coordinates in
 * the generated grid/array.
 * 
 * @author Team 7
 * @version 1.0
 * @since 2019-02-19
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bullet extends KinematicEntity {

    public Bullet(){
        super(new Circle(5, 5, 5, Color.BLACK));
    }
}
