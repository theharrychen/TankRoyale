//package codes;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Game implements EventHandler<KeyEvent>{ 
    
    private Pane root = new Pane();
    private ArrayList<GameEntity> tanks = new ArrayList<>();
   // private ArrayList<GameEntity> walls = new ArrayList<>();
    private ArrayList<GameEntity> bullets = new ArrayList<>();
    private boolean restart = false;
    private boolean load = true;
    private boolean is;
    private Button restartBtn = new Button("restart");
    private static int playerCount = 2;

    public Pane getRoot(){
        return root; //Privacy leak??
    }

    public void start(){
       // genertaeMap(" ");
       load = true;
        for(int i = 0; i < playerCount; i++)
            randomSpawn(new Tank());
    }

    public void restart(){
        restartBtn.relocate(490, 304);
        root.getChildren().add(restartBtn);
        restartBtn.setOnAction(e -> {
            root.getChildren().clear();
            start();
            restart = false;
        });
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

    /*public void addWall(GameEntity wall, double x, double y){
        walls.add(wall);
        addGameEntity(wall, x, y);
    }*/

    public void randomSpawn(GameEntity tank){
        /*for(GameEntity wall : walls){
            while(tank.isColliding(wall)){

            }
        }*/
        tank.setVelocity(new Point2D(1, 0));
        addTank(tank, rng(100, 500), rng(100, 500));
    }

    public void gameUpdate(){
        if(!restart){
            for(GameEntity tank : tanks){
                if(tank.isAlive()){
                    collisionUpdate();
                    tank.movement();
                }
                else{
                    restart = true;
                }
            }
        }
        else if(load){
            root.getChildren().clear();
            tanks.clear();
            load = false;
            restart();  
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
        bullets.removeIf(GameEntity :: isDead);
        bullets.forEach(GameEntity :: moveForward);
       /* for(Wall wall : walls){
            if(bullet.isColliding(wall)){
                bullet.setAlive(false); 
                root.getChildren().removeAll(bullet.getView());
            }           
        }*/
    }

    public void shoot(GameEntity tank){
        Bullet bullet = new Bullet();
        double angle = Math.toRadians(tank.getView().getRotate());
        double x = (tank.getView().getTranslateX() + 20) + (30) * Math.cos(angle);
        double y = (tank.getView().getTranslateY() + 10) + (30) * Math.sin(angle);
        bullet.setVelocity(tank.getVelocity().normalize().multiply(3));
        addBullet(bullet, x, y);
    }

    public static int rng(int min, int max) { // Random Number Generator
		if (min > max) { // Argument Error Trap
			int temp = min;
			min = max;
			max = temp;
		}
		int number = (int) (Math.random() * (max - min + 1) + min);
		return number;
	}

    //this method is too big
    public void handle(KeyEvent key){
        if(key.getEventType().equals(KeyEvent.KEY_PRESSED)){
            if(key.getCode() == KeyCode.W){
                tanks.get(0).setUp(true);
            }
            else if(key.getCode() == KeyCode.A){
                tanks.get(0).setLeft(true);
                for(GameEntity bullet : bullets)
                    bullet.setLeft(true);
            }
            else if(key.getCode() == KeyCode.S){
                tanks.get(0).setDown(true);
            }
            else if(key.getCode() == KeyCode.D){
                tanks.get(0).setRight(true);
            }
            else if(key.getCode() == KeyCode.Q){
                shoot(tanks.get(0));
            }
            else if(key.getCode() == KeyCode.UP){
                tanks.get(1).setUp(true);
            }
            else if(key.getCode() == KeyCode.LEFT){
                tanks.get(1).setLeft(true);
            }
            else if(key.getCode() == KeyCode.DOWN){
                tanks.get(1).setDown(true);
            }
            else if(key.getCode() == KeyCode.RIGHT){
                tanks.get(1).setRight(true);
                for(GameEntity bullet : bullets)
                    bullet.setRight(true);
            }
            else if(key.getCode() == KeyCode.SHIFT){
                shoot(tanks.get(1));
            }
        }
        else{
            if(key.getCode() == KeyCode.W){
                tanks.get(0).setUp(false);
            }
            else if(key.getCode() == KeyCode.A){
                tanks.get(0).setLeft(false);
            }
            else if(key.getCode() == KeyCode.S){
                tanks.get(0).setDown(false);
            }
            else if(key.getCode() == KeyCode.D){
                tanks.get(0).setRight(false);
            }
            else if(key.getCode() == KeyCode.UP){
                tanks.get(1).setUp(false);
            }
            else if(key.getCode() == KeyCode.LEFT){
                tanks.get(1).setLeft(false);
            }
            else if(key.getCode() == KeyCode.DOWN){
                tanks.get(1).setDown(false);
            }
            else if(key.getCode() == KeyCode.RIGHT){
                tanks.get(1).setRight(false);
            }
        }           
    }  
    
}