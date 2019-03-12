//package codes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Bullet extends GameEntity{

    Bullet(){
        super(new Circle(0, 0, 4, Color.BLACK));
    }
}