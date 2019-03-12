package codes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bullet extends KinematicEntity {

    public Bullet(){
        super(new Circle(5, 5, 5, Color.BLACK));
    }
}
