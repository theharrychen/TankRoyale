package codes;


/**
 * This game class handles map generation, shoot,and detect collisions. 
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Game {
	
	private boolean shooting = false;
    private Pane root = new Pane();
    private AnimationTimer timer;
	private Map gamemap;
	
    // Assumes 2 players
    private static int playerCount = 2;
    private boolean gameOver = false;
    private Button restartBtn = new Button("Restart");

    //Arraylist of all Game Entities
    private ArrayList<Tank> tanks = new ArrayList<>();
    private ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();
	
	/**
	 *Sets the prefered size of the Pane layout
	 */
    public Game(){
        root.setPrefSize(Main.WIDTH, Main.HEIGHT);
    }

    /**
	 *@return root layout
	 */
    public Pane getRoot(){
        return root;
    }
	
	/**
	 *Starts the game
	 */
    public void start(){
        //Adds two player tanks to the map
        addTank(new Tank(), (double)rng(40, (int)Main.WIDTH-40), (double)rng(40, (int)Main.HEIGHT-40));
        addTank(new Tank(), (double)rng(40, (int)Main.WIDTH-40), (double)rng(40, (int)Main.HEIGHT-40));
		try {
		    createMap();
		}
		catch (Exception e) {
		    System.out.println("Was unable to load in the map");
		}

        // Game Loop
        timer = new AnimationTimer() {
            @Override
			// Runs each frame
            public void handle(long now) { 
                onUpdate();
            }
        };
        timer.start();
    }

    /**
	 *Provide option for restart
	 */
    public void restart(){
        timer.stop();
		
		//Create restart button
        restartBtn.setTranslateX(Main.WIDTH / 2.0 );
        restartBtn.setTranslateY(Main.HEIGHT / 2.0);
        Label winText = new Label("IT WAS A TIE");
		
		// Announce winner of match
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

    /**
	 *Adds the view of a GameEntity to the Pane
	 *@param GameEntity entity, double x, double y
	 */
    private void addGameEntity(GameEntity entity, double x, double y){
        entity.getView().setTranslateX(x);
        entity.getView().setTranslateY(y);
        root.getChildren().add(entity.getView());
    }
	
	/**
	 *Adds a Bullet to the Pane
	 *@param Bullet bullet, double x, double y
	 */
    private void addBullet(Bullet bullet, double x, double y){
        bullets.add(bullet);
        addGameEntity(bullet, x, y);
    }

	/**
	 *Adds a Tank to the Pane
	 @param Tank tank, double x, double y
	 */
    private void addTank(Tank tank, double x, double y){
        tanks.add(tank);
        addGameEntity(tank, x, y);
    }
	
	/**
	 *Adds a Wall to the Pane
	 @param Wall wall, double x, double y
	 */
	private void addWall(Wall wall, double x, double y){
        walls.add(wall);
        addGameEntity(wall, x, y);
    }
	
	/**
	 *Adds a horizontally orientated wall to the Pane
	 *@param int x, int y, double width, double height
	 */
	private void addHorizontalWall(int x, int y, double width, double height){
		if(y == (gamemap.getWidth() - 1)){
			addWall(new Wall(width + 1.0,10.0,1), x * width ,Main.HEIGHT);
		}else{
			addWall(new Wall(width + 1.0,10.0,1),x *width, y *height);
		}
	}
	
	/**
	 *Adds a vertically orientated wall to the Pane
	 *@param int x, int y, double width, double height
	 */
	private void addVerticalWall(int x, int y, double width, double height){
		
		if(x == (gamemap.getHeight()-1)){
			addWall(new Wall(10.0,height + 10.0,0),Main.WIDTH,y * height);
		}else{
			addWall(new Wall(10.0,height + 2.0 ,0),x * width,y * height);
			}
	}
	
	/**
	 * Creates the map of TankRoyale on JavaFX
	 * | are vertical walls
	 * # are horizontal walls
	 * ^ are corners
	 */
	public void createMap() throws FileNotFoundException{
		gamemap = new Map("/maze.txt");
		char[][] map = gamemap.getCharMap();
		//Adjusting the height or width of the text file to fit the size of the javafx screen
		double height = Main.HEIGHT/gamemap.getHeight();
		double width = Main.WIDTH/gamemap.getWidth();
		for (int y = 0; y < gamemap.getHeight(); y++) {
			for (int x = 0; x < gamemap.getWidth(); x++) {
				switch(map[y][x]) {
					case ' ':
						break;
					case '^':
						addVerticalWall(x,y,width,height);
						addHorizontalWall(x,y,width,height);
					break;
					case '|':
						addVerticalWall(x,y,width,height);
						break;
					case '#':
						addHorizontalWall(x,y,width,height);
						break;
					
			}
		}
	}
	}

    /**
	 *Future method to provide valid spawn points
	 */
    private void randomSpawn(GameEntity tank){
        //addTank(tank, randomX, randomY)
    }

	/**
	 *Updates the game state
	 */
    private void onUpdate(){
        if(!gameOver){
            detectCollisions();
            clearDeadBullets();
            checkGameOver();

            // A type of lambda expression: parameter -> expression
            bullets.forEach(bullet -> bullet.update());
			bullets.forEach(bullet -> bullet.reduceLifeTime());
            tanks.forEach(tank -> tank.update());
        }
        else {
            restart();
        }      

    }
	
	
	/**
	 *Detects Collisions between bullets,tanks, and walls walls.
	 */
    private void detectCollisions(){
		
        for(Bullet bullet : bullets){
			//Detects collision of bullet with tank
            for(Tank tank : tanks){
                if(bullet.isColliding(tank)){
                    bullet.setAlive(false);
                    tank.setAlive(false);
                    //Removes collided entities from the layout
                    root.getChildren().removeAll(bullet.getView(), tank.getView());
                }
				else if(bullet.getLifeTime() == 0){
                    bullet.setAlive(false);
                    root.getChildren().removeAll(bullet.getView());
            }
			}
			
			// Detects collision of bullet with wall
	        for(Wall wall : walls){
				// Work in progress, sometimes bullet glitches through
               if(bullet.isColliding(wall)){
                    ricochet(wall, bullet);
               }
       	    }
        }
		
		//Detects collision with walls and tanks
        for (GameEntity wall : walls) {
            for(Tank tank : tanks){
                if (tank.isColliding(wall)) {
                    tank.setVelocity(new Point2D(0,0));
                    tank.getView().setTranslateX(tank.getView().getTranslateX() - tank.getFacing().getX()*tank.getMoveDir() );
                    tank.getView().setTranslateY(tank.getView().getTranslateY() - tank.getFacing().getY()*tank.getMoveDir());

                }
            }
        }
		}

	/**
	 *Richochets bullets off of a wall
	 *@param Wall wall, Bullet bullet
	 */
    private void ricochet(Wall wall, Bullet bullet){
        if (bullet.isColliding(wall)) {
            if(wall.getAlignment()== 0 || wall.getAlignment() == 0){
                bullet.setVelocity(new Point2D(-1*bullet.getVelocity().getX(), bullet.getVelocity().getY()));
            }
            else if(wall.getAlignment() == 1 || wall.getAlignment() == 1){
                bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1*bullet.getVelocity().getY()));
            }
        }
    }



    /**
	 *Removes dead entities from the arraylists
	 *Dead tanks must be replaced, so that the arraylist order doesn't change
     *and tanks have to be removed from the arraylist so that the non-visual bounds of it are removed completely from the Pane
	 */
    private void clearDeadBullets(){
        bullets.removeIf(bullet -> bullet.isDead());
        
        for(int x = 0; x < tanks.size(); x++){
            if(tanks.get(x).isDead()){
                Tank temp = new Tank();
                temp.setAlive(false);
                tanks.set(x, temp);
            }
        }
    }

	/**
	 *Checks if both tanks are still alive
	 */
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

    /**
	 * Tank creates and shoots a bullet
	 * Bullet is currently generated in front of the tank, so it doesn't self-destruct
	 *@param Tank tank
	 */
    private void shoot(Tank tank){
        Bullet bullet = new Bullet();
        bullet.setVelocity(tank.getFacing().normalize().multiply(5));
        addBullet(bullet, tank.getView().getTranslateX()
                        + tank.getFacing().normalize().multiply(40).getX() + 15,
                        tank.getView().getTranslateY()
                        + tank.getFacing().normalize().multiply(40).getY() + 10);
    }

   /**
    * Random number generator for spawn location
    *@param int min, int max
	*@return int
    */
   public static int rng(int min, int max) {
		if (min > max) { 
		// Argument Error Trap
			int temp = min;
			min = max;
			max = temp;
		}
		int number = (int) (Math.random() * (max - min + 1) + min);
		return number;
	}

	/**
	 * Assign keyboard controls for player one and two
	 */
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
                    if(shooting == false){
                        shoot(tanks.get(0));
                        shooting = true;
                    }
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
                    if(shooting == false){
                        shoot(tanks.get(1));
                        shooting = true;
                    }
                    break;

            }
        }
    }

	/**
	 *Set tank velocity when player command is inputted
     */
   
    public class ReleaseHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent key) {
            switch (key.getCode()) {
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
                case ENTER:
                    shooting = false;
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
                case Q:
                    shooting = false;
            }
        }
    }

}
