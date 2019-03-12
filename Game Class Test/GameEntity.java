//package codes;


import javafx.geometry.Point2D;
import javafx.scene.Node;

public class GameEntity{

    private Node view;
    private Point2D velocity = new Point2D(0, 0);
    private boolean alive = true;

    private boolean up, down, left, right;

    public GameEntity(Node view){
        this.view = view;
    }

    public void setUp(boolean up){
        this.up = up;
    }

    public void setDown(boolean down){
        this.down = down;
    }

    public void setRight(boolean right){
        this.right = right;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public Node getView(){
        return view;
    }

    public Point2D getVelocity(){
        return velocity;
    }

    public void setAlive(boolean alive){
        this.alive = alive;
    }

    public boolean isAlive(){
        return alive;
    }

    public boolean isDead(){
        return !alive;
    }

    public void setVelocity(Point2D velocity){
        this.velocity = velocity;
    }

    public void moveForward(){
        view.setTranslateX(view.getTranslateX() + velocity.getX());
        view.setTranslateY(view.getTranslateY() + velocity.getY());
    }

    public void moveBackward(){
        view.setTranslateX(view.getTranslateX() - velocity.getX());
        view.setTranslateY(view.getTranslateY() - velocity.getY());
    }

    public void rotateRight(){
        view.setRotate(view.getRotate() + 1);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public void rotateLeft(){
        view.setRotate(view.getRotate() - 1);
        setVelocity(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
    }

    public double getRotate(){
        return view.getRotate();
    }

    public void movement(){
        if(up){
            moveForward();
        }
        if(down){
            moveBackward();
        }
        if(right){
            rotateRight();
        }
        if(left)
            rotateLeft();
    }

    public boolean isColliding(GameEntity other){
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }
}