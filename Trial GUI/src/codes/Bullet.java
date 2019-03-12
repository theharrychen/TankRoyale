package codes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Bullet extends KinematicEntity {

    private int lifeTime = 300;

    public Bullet(){
        super(new Circle(5, 5, 5, Color.BLACK));
    }

    public int getLifeTime(){
        return lifeTime;
    }

    public void setLifeTime(int lifeTime){
        this.lifeTime = lifeTime;
    }

    public void reduceLifeTime(){
        lifeTime--;
    }
}
