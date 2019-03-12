package codes;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;

public class Game {

    private Pane root = new Pane();
    private AnimationTimer timer;

    // Assume 2 players for now until we have an AI, might move change to globally main
    private static int playerCount = 2;
    private boolean gameOver = false;
    private Button restartBtn = new Button("Restart");

    //All Game Entities
    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    //Border Walls, temporary hard code
    private Wall vertWall1;
    private Wall vertWall2;
    private Wall horizWall1;
    private Wall horizWall2;

    public Game(){
        root.setPrefSize(Main.WIDTH, Main.HEIGHT);
    }

    //Potential privacy leak that might be need to be addressed - Josh
    public Pane getRoot(){
        return root;
    }

    public void start(){
        //Temporary hard code, not using player count
        addTank(new Tank(), (double)rng(40, (int)Main.WIDTH-40), (double)rng(40, (int)Main.HEIGHT-40));
        addTank(new Tank(), (double)rng(40, (int)Main.WIDTH-40), (double)rng(40, (int)Main.HEIGHT-40));

        //Temp hardcode for border walls
        vertWall1 = new Wall(10, Main.HEIGHT);
        vertWall2 = new Wall(10,Main.HEIGHT);
        horizWall1 = new Wall(Main.WIDTH,10);
        horizWall2 = new Wall(Main.WIDTH,10);

        addWall(vertWall1, 0,0);
        addWall(vertWall2, Main.WIDTH-10, 0);
        addWall(horizWall1, 0, 0);
        addWall(horizWall2, 0, Main.HEIGHT-10);

        //TODO: add map elements, valid random spawn

        // Game Loop
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) { // Runs each frame
                onUpdate();
            }
        };
        timer.start();
    }

    //Might need to split into more pieces, not everything is a for the "restart"
    public void restart(){
        timer.stop();

        restartBtn.setTranslateX(Main.WIDTH / 2.0 );
        restartBtn.setTranslateY(Main.HEIGHT / 2.0);

        Label winText = new Label("IT WAS A TIE");
        for (int x = 0; x < tanks.size(); x++){
            if (tanks.get(x).isAlive()) {
                winText.setText("PLAYER " + (x+1) + " WON!");
                break;
            }
        }
        winText.setTranslateX(Main.WIDTH / 2.0 );
        winText.setTranslateY(Main.HEIGHT / 2.0 - 30);

        root.getChildren().addAll(restartBtn, winText);

        restartBtn.setOnAction(e -> {
            root.getChildren().clear();
            tanks.clear();
            bullets.clear();
            walls.clear();
            gameOver = false;
            start();
        });
    }

    // Adds the view of a GameEntity to the Pane
    private void addGameEntity(GameEntity entity, double x, double y){
        entity.getView().setTranslateX(x);
        entity.getView().setTranslateY(y);
        root.getChildren().add(entity.getView());
    }

    private void addBullet(Bullet bullet, double x, double y){
        bullets.add(bullet);
        addGameEntity(bullet, x, y);
    }

    private void addTank(Tank tank, double x, double y){
        tanks.add(tank);
        addGameEntity(tank, x, y);
    }

    private void addWall(Wall wall, double x, double y){
        walls.add(wall);
        addGameEntity(wall, x, y);
    }

    //TODO a valid randomSpawn, if we have more walls
    private void randomSpawn(GameEntity tank){
        //addTank(tank, randomX, randomY)
    }

    private void onUpdate(){
        if(!gameOver){
            detectCollisions();
            clearDeadBullets();
            checkGameOver();

            // A type of lambda expression: parameter -> expression
            bullets.forEach(bullet -> bullet.update());
            tanks.forEach(tank -> tank.update());
        }
        else {
            restart();
        }      

    }

    private void detectCollisions(){
        for(Bullet bullet : bullets){
            for(Tank tank : tanks){
                if(bullet.isColliding(tank)){
                    bullet.setAlive(false);
                    tank.setAlive(false);
                    //Removes collided entities from the layout
                    root.getChildren().removeAll(bullet.getView(), tank.getView());
                }
            }

	        for(Wall wall : walls){
               if(bullet.isColliding(wall)){
                    ricochet(wall, bullet);
               }
       	    }
        }

        for (GameEntity wall : walls) {
            for(Tank tank : tanks){
                if (tank.isColliding(wall)) {
                    //Work in progress, doesn't work well against rotations

                    //tank.getView().setTranslateX(tank.getView().getTranslateX() -tank.getVelocity().multiply(3).getX() - Math.cos(Math.toRadians(tank.getView().getRotate())));
                    //tank.getView().setTranslateY(tank.getView().getTranslateY() -tank.getVelocity().multiply(3).getY() - Math.sin(Math.toRadians(tank.getView().getRotate())));

                    tank.setVelocity(new Point2D(0,0));
                    tank.getView().setTranslateX(tank.getView().getTranslateX() - tank.getFacing().getX()*tank.getMoveDir() );
                    tank.getView().setTranslateY(tank.getView().getTranslateY() - tank.getFacing().getY()*tank.getMoveDir());

                }
            }
        }
    }

    private void ricochet(Wall wall, Bullet bullet){
        //HARDCODE only works for border walls
        if (bullet.isColliding(wall)) {
            if(wall.equals(vertWall1) || wall.equals(vertWall2)){
                bullet.setVelocity(new Point2D(-1*bullet.getVelocity().getX(), bullet.getVelocity().getY()));
            }
            else if(wall.equals(horizWall1) || wall.equals(horizWall2)){
                bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1*bullet.getVelocity().getY()));
            }
        }



        //Trying to do it based on orientation to the wall, can't figure it fully out

        //System.out.println("X: " + bullet.getView().getTranslateX() + " Y: " + bullet.getView().getTranslateY());
        //System.out.println("Wall Height " + wall.getHeight());
        //System.out.println(bullet.getView().getTranslateY() <= wall.getHeight());
        /*
        if (bullet.getView().getTranslateX() >= wall.getView().getTranslateX()
            || bullet.getView().getTranslateX() <= wall.getView().getTranslateX()){
            if(bullet.getView().getTranslateY() > wall.getView().getTranslateY()
                && bullet.getView().getTranslateY() < wall.getView().getTranslateY() + wall.getHeight()){
                System.out.println(1);

                bullet.setVelocity(new Point2D(-1*bullet.getVelocity().getX(), bullet.getVelocity().getY()));
            }
        }/

        /*
        if (bullet.getView().getTranslateY() >= wall.getView().getTranslateY()
                || bullet.getView().getTranslateY() <= wall.getView().getTranslateY()){
            if(bullet.getView().getTranslateX() > wall.getView().getTranslateX()
                    && bullet.getView().getTranslateX() < wall.getView().getTranslateX() + wall.getWidth()){
                System.out.println(2);
                bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1*bullet.getVelocity().getY()));
            }
        }*/


        /*
        if (bullet.getView().getTranslateY() >= wall.getView().getTranslateY()
                || bullet.getView().getTranslateY() <= wall.getView().getTranslateY()){
            if(bullet.getView().getTranslateX() > 10
                    && bullet.getView().getTranslateX() < wall.getWidth() - 10){
                System.out.println("BOY");

                bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1*bullet.getVelocity().getY()));
            }
        }
        */

    }



    // Removes dead entities from the arraylists
    private void clearDeadBullets(){
        bullets.removeIf(bullet -> bullet.isDead());
        //Dead tanks must be replaced, so that the arraylist order doesn't change
        //and tanks have to be removed from the arraylist so that the non-visual bounds of it are removed completely from the Pane
        //Extremely bad way of doing things, should replace later, all to keep the arraylist compatible with the keys
        //Is okay for 2 players since the game ends immediately with 1 death, but for 3 players, it will keep replacing the dead tank
        for(int x = 0; x < tanks.size(); x++){
            if(tanks.get(x).isDead()){
                Tank temp = new Tank();
                temp.setAlive(false);
                tanks.set(x, temp);
            }
        }
    }

    private void checkGameOver(){
        int count = 0;
        for(GameEntity tank : tanks){
            if(tank.isAlive()){
                count++;
            }
        }
        if (count <= 1) {
            gameOver = true;
        }
    }

    //Might move to tank class, and return a Bullet object to be passed into addGameEntity()
    private void shoot(Tank tank){
        Bullet bullet = new Bullet();
        bullet.setVelocity(tank.getFacing().normalize().multiply(5));
        //hardcoded adjustment to center bullet on tank
        //addBullet(bullet, tank.getView().getTranslateX() + 15, tank.getView().getTranslateY()  + 10);
        //the above but the bullet is always in front of the tank, so it doesn't self-destruct
        addBullet(bullet, tank.getView().getTranslateX()
                        + tank.getFacing().normalize().multiply(40).getX() + 15,
                        tank.getView().getTranslateY()
                        + tank.getFacing().normalize().multiply(40).getY() + 10);
                
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

	//TODO CHANGE THE KEYS AVAILABLE BASED ON PLAYER COUNT
    public class PressHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent key) {

            switch (key.getCode()) {
                case UP:
                    tanks.get(0).setUp(true);
                    break;
                case DOWN:
                    tanks.get(0).setDown(true);
                    break;
                case LEFT:
                    tanks.get(0).setLeft(true);
                    break;
                case RIGHT:
                    tanks.get(0).setRight(true);
                    break;
                case ENTER:
                    shoot(tanks.get(0));
                    break;
                case W:
                    tanks.get(1).setUp(true);
                    break;
                case S:
                    tanks.get(1).setDown(true);
                    break;
                case A:
                    tanks.get(1).setLeft(true);
                    break;
                case D:
                    tanks.get(1).setRight(true);
                    break;
                case Q:
                    shoot(tanks.get(1));
                    break;

            }
        }
    }

    public class ReleaseHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent e) {
            switch (e.getCode()) {
                case UP:
                    tanks.get(0).setUp(false);
                    break;
                case DOWN:
                    tanks.get(0).setDown(false);
                    break;
                case LEFT:
                    tanks.get(0).setLeft(false);
                    break;
                case RIGHT:
                    tanks.get(0).setRight(false);
                    break;
                case W:
                    tanks.get(1).setUp(false);
                    break;
                case S:
                    tanks.get(1).setDown(false);
                    break;
                case A:
                    tanks.get(1).setLeft(false);
                    break;
                case D:
                    tanks.get(1).setRight(false);
                    break;
            }
        }
    }

}
