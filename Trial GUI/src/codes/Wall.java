package codes;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends GameEntity {
    private double width;
    private double height;

    public Wall(double width, double height) {
        super(new Rectangle(width, height, Color.gray(0.5)));
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}