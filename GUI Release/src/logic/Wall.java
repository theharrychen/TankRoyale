package logic;
/**
 * This class is used to create the wall objects in TankRoyale's map
 *
 * @author Group 7
 * @version 3.0
 * @since 2019-03-06
 */

import drivers.MainGUI;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import visuals.MenuGUI;

import static logic.Game.rng;

public class Wall extends GameEntity {
    private double width = 0;
    private double height = 0;
    // These coordinates are used to locate the corners of the rectangle
    private double x1 = 0;
    private double x2 = 0;
    private double y1 = 0;
    private double y2 = 0;

    /**
     * Checks the parameters given to the super constructor to ensure valid walls are created
     *
     * @Param double width, double height
     */
    public static Rectangle checkParameters(double width, double height) {
        if (width <= MainGUI.WIDTH && height <= MainGUI.HEIGHT - 100){
            if (MenuGUI.isColoured()) {
                return new Rectangle(width, height, Color.rgb(rng(0, 255), rng(0,255), rng(0, 255)));
            }
            else {
                return new Rectangle(width, height, Color.gray(0.5));
            }
        }
        return new Rectangle(0, 0, Color.gray(0.5));
    }

    /**
     * @Param double width, double height
     */
    public Wall(double width, double height) {
        super(checkParameters(width, height));
        if (width <= MainGUI.WIDTH && height <= MainGUI.HEIGHT - 100) {
            setWidth(width);
            setHeight(height);
        }
    }

    /**
     * Used to update the corner coordinates when the wall is translated
     */
    public void updateCorners() {
        setX1(getView().getTranslateX());
        setX2(getView().getTranslateX() + width);
        setY1(getView().getTranslateY());
        setY2(getView().getTranslateY() + height);
    }

    /**
     * Sets the wall width
     *
     * @param double width which must be less than the width of the game screen
     */
    public void setWidth(double width) {
        if (width <= MainGUI.WIDTH) {
            this.width = width;
        }
    }

    /**
     * Sets the wall height
     *
     * @param double height which must be 100 pixels less than the height of the game screen
     */
    public void setHeight(double height) {
        if (height <= MainGUI.HEIGHT - 100) {
            this.height = height;
        }
    }

    /**
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return x1
     */
    public double getX1() {
        return x1;
    }

    /**
     * @param x1
     */
    public void setX1(double x1) {
        this.x1 = x1;
    }

    /**
     * @return x2
     */
    public double getX2() {
        return x2;
    }

    /**
     * @param x2
     */
    public void setX2(double x2) {
        this.x2 = x2;
    }

    /**
     * @return y1
     */
    public double getY1() {
        return y1;
    }

    /**
     * @param y1
     */
    public void setY1(double y1) {
        this.y1 = y1;
    }

    /**
     * @return y2
     */
    public double getY2() {
        return y2;
    }

    /**
     * @param y2
     */
    public void setY2(double y2) {
        this.y2 = y2;
    }

}
