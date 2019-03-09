package codes;


import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game implements EventHandler<ActionEvent>{ 
    
    private Pane root = new Pane();
    private GameEntity tank = new Tank();
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private Bullet bullet1, bullet2;
    private Tank tank1, tank2;

    Game(Pane root){
        this.root = root;
    }

    public Pane getRoot(){
        return new Pane(root);
    }

    public void addGameEntity(GameEntity object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    //
    public void gameUpdate(){
        root.getChildren().add(createMap); //Place holder

    }

    public void collisionUpdate(){
        if(bullet.isColliding(tank1)){
            bullet.isAlive(false);
            tank1.isAlive(false);
        }
        if(bullet.isColliding(tank2)){
            bullet.isAlive(false);
            tank2.isAlive(false);
        }
        for(Wall wall : walls){
            if(bullet.isColliding(wall))
                bullet.isAlive(false);            
        }
        //remove bullet
    }

    public void handle(KeyEvent key){
        if(key.getCode() == KeyCode.W){
            tank1.moveForward();
        }
        else if(key.getCode() == keyCode.A){
            tank1.rotateLeft();
        }
        else if(key.getCode() == keyCode.S){
            tank1.moveBackward();
        }
        else if(key.getCode() == KeyCode.D){
            tank1.rotateRight();
        }
        else if(key.getCode() == KeyCode.UP){
            tank2.moveForward();
        }
        else if(key.getCode() == KeyCode.LEFT){
            tank2.rotateLeft();
        }
        else if(key.getCode() == KeyCode.DOWN){
            tank2.moveBackward();
        }
        else if(key.getCode() == KeyCode.RIGHT){
            tank2.rotateRight();
        }
    }   
}