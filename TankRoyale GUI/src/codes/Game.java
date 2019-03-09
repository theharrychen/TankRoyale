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
    private ArrayList<GameEntity> tanks = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private ArrayList<GameEntity> bullets = new ArrayList<>();

    public Pane getRoot(){
        return root; //Privacy leak??
    }

    public void addGameEntity(GameEntity object, double x, double y){
        object.getView().setTranslateX(x);
        object.getView().setTranslateY(y);
        root.getChildren().add(object.getView());
    }

    public void addBullet(GameEntity bullet, double x, double y){
        bullets.add(bullet);
        addGameEntity(bullet, x, y);
    }

    public void addTank(GameEntity tank, double x, double y){
        tanks.add(tank);
        addGameEntity(tank, x, y);
    }

    public void spawn(){

    }
    
    public void gameUpdate(){
        for(GameEntity tank : tanks){
            if(tank.isAlive()){
                root.getChildren().add(createMap); //Place holder
                collisionUpdate();
            }
            else{
                //end game screen
                //restart button
            }
        }

    }

    public void collisionUpdate(){
        for(GameEntity bullet : bullets){
            for(GameEntity tank : tanks){
                if(bullet.isColliding(tank)){
                    bullet.setAlive(false);
                    tank.setAlive(false);
                    root.getChildren().removeAll(bullet.getView(), tank.getView());
                }
            }
        }
        for(Wall wall : walls){
            if(bullet.isColliding(wall)){
                bullet.setAlive(false); 
                root.getChildren().removeAll(bullet.getView());
            }           
        }
    }

    public void shoot(GameEntity tank){
        Bullet bullet = new Bullet();
        bullet.setVelocity(tank.getVelocity().normalize().multiply(5));
        addBullet(bullet, tank.getView().getTranslateX(), tank.getView().getTranslateY());
    }

    public void handle(KeyEvent key){
        if(key.getCode() == KeyCode.W){
            tanks.get(0).moveForward();
        }
        else if(key.getCode() == keyCode.A){
            tanks.get(0).rotateLeft();
        }
        else if(key.getCode() == keyCode.S){
            tanks.get(0).moveBackward();
        }
        else if(key.getCode() == KeyCode.D){
            tanks.get(0).rotateRight();
        }
        else if(key.getCode() == KeyCode.Q){
            shoot(tanks.get(0));
        }
        else if(key.getCode() == KeyCode.UP){
            tanks.get(1).moveForward();
        }
        else if(key.getCode() == KeyCode.LEFT){
            tanks.get(1).rotateLeft();
        }
        else if(key.getCode() == KeyCode.DOWN){
            tanks.get(1).moveBackward();
        }
        else if(key.getCode() == KeyCode.RIGHT){
            tanks.get(1).rotateRight();
        }
        else if(key.getCode() == KeyCode.SHIFT){
            shoot(tanks.get(1));
        }
    }   
}