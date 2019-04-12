package logic;

/**
 * This game class handles map generation, shoot,and detect collisions.
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-06
 */

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.scene.shape.Circle;
import visuals.*;
import drivers.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Game {
    // Assumes 2 players
    private static int playerCount = 2;
    private boolean gameOver = false;
    private boolean roundOver = false;
    private AnimationTimer timer;
    private Map gameMap;
    private int p1score = 0;
    private int p2score = 0;

    //Arraylist of all Game Entities
    private static ArrayList<Tank> tanks = new ArrayList<>();
    private static ArrayList<Bullet> bullets = new ArrayList<>();
    private ArrayList<Wall> walls = new ArrayList<>();

    //Display variable
    private static GameGUI visual = new GameGUI();

    //not encapsulated
    public GameGUI getVisual() {
        return visual;
    }

    /**
     * @return ArrayList<Tank> temp
     */
    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    /**
     * @return int playerCount
     */
    public int getPlayerCount() {
        return playerCount;
    }

    /**
     * Changes game state
     *
     * @param state
     */
    public void setGameOver(boolean state) {
        this.gameOver = state;
    }

    /**
     * Starts the game
     */
    public void start() {
        if (MenuGUI.isColoured()) {
            createColourMap();
        } else {
            try {
                createMap();
            } catch (Exception e) {
                System.out.println("Was unable to load in the map");
                System.out.println(e.getMessage());
            }
        }

        //Super important for detection collisions
        walls.forEach(wall -> wall.updateCorners());

        visual.displayTally(p1score, p2score);
        //Adds two player tanks to the map
        Tank player1 = new Tank();
        visual.addPlayerimage(player1);
        Tank player2 = new Tank();
        visual.addPlayerimage(player2);
        randomSpawn(player1);
        randomSpawn(player2);

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
     * Updates the game state
     */
    public void onUpdate() {
        if (MenuGUI.isSickoMode()) {
            for (Wall wall : walls) {
                Rectangle r = (Rectangle) wall.getView();
                r.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
            }
            for (Bullet bullet : bullets) {
                Circle c = (Circle) bullet.getView();
                c.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
            }
        }

        if (!roundOver) {
            detectCollisions();
            clearDeadEntities();
            checkRoundOver();

            // A type of lambda expression: parameter -> expression
            bullets.forEach(bullet -> bullet.update());
            bullets.forEach(bullet -> bullet.reduceLifeTime());
            //Checks if a tank is colliding with a wall
            for (Tank tank : tanks) {
                if (!checkPoint(tank))
                    tank.update();
                else {
                    tank.stop(); //Stops tanks movement
                }
            }
        } else {
            restart();
        }

    }

    /**
     * Restarts the game after each round ends
     */
    public void restart() {
        if (!gameOver) {
            timer.stop();
            roundOver = false;
            int winner = determineWinner();
            updateScore(winner);
            visual.clear();
            tanks.clear();
            bullets.clear();
            walls.clear();
            checkGameOver();
            start();
        } else {
            endScreen();
        }
    }

    /**
     * The screen after the game ends
     */
    public void endScreen() {
        timer.stop();
        gameOver = false;
        visual.clear();
        int winner = determineWinner();
        visual.createRestartButton();
        visual.announceWinner(winner);
        p1score = 0;
        p2score = 0;
        visual.getRestartBtn().setOnAction(e -> {
            tanks.clear();
            bullets.clear();
            walls.clear();
            start();
        });
    }

    /**
     * Determines the winner of the round
     *
     * @return int index number of the winner in the arraylist
     */
    public int determineWinner() {
        int winner = 0;
        for (int x = 0; x < tanks.size(); x++) {
            if (tanks.get(x).isAlive()) {
                winner = x + 1;
            }
        }
        return winner;
    }

    /**
     * Updates score based off the winner of the round
     *
     * @param winner
     */
    public void updateScore(int winner) {
        for (Tank tank : tanks) {
            if (tank.isDead()) {
                switch (winner) {
                    case 0:
                        break;
                    case 1:
                        p1score++;
                        break;
                    case 2:
                        p2score++;
                        break;
                }
            }
        }
    }

    /**
     * Checks if both tanks are still alive
     */
    private void checkRoundOver() {
        int count = 0;
        for (GameEntity tank : tanks) {
            if (tank.isAlive()) {
                count++;
            }
        }
        if (count <= 1) {
            roundOver = true;
        }
    }

    /**
     * Checks if any player reached the score limit. If score limit is reached game will end.
     */
    public void checkGameOver() {
        if (p1score == MenuGUI.getEndRound() || p2score == MenuGUI.getEndRound()) //First player to reach the specified points wins
            gameOver = true;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    /**
     * Adds the view of a GameEntity to the Pane
     *
     * @param GameEntity entity, double x, double y
     */
    private void addGameEntity(GameEntity entity, double x, double y) {
        entity.getView().setTranslateX(x);
        entity.getView().setTranslateY(y);
        visual.addVisualGameEntity(entity);
    }

    /**
     * Adds a Bullet to the Pane
     *
     * @param Bullet bullet, double x, double y
     */
    private void addBullet(Bullet bullet, double x, double y) {
        bullets.add(bullet);
        addGameEntity(bullet, x, y);
    }

    /**
     * Adds a Tank to the Pane
     *
     * @param Tank tank, double x, double y
     */
    private void addTank(Tank tank, double x, double y) {
        tanks.add(tank);
        addGameEntity(tank, x, y);
    }

    /**
     * Adds a Wall to the Pane
     *
     * @param Wall wall, double x, double y
     */
    private void addWall(Wall wall, double x, double y) {
        walls.add(wall);
        addGameEntity(wall, x, y);
    }

    /**
     * Adds a horizontally orientated wall to the Pane
     *
     * @param int x, int y, double width, double height
     */
    private void addHorizontalWall(int x, int y, double width, double height) {
        if (y == (gameMap.getWidth() - 1)) {
            addWall(new Wall(width + 10.0, 10.0), x * width, (MainGUI.HEIGHT - 100));
        } else {
            addWall(new Wall(width + 10.0, 10.0), x * width, y * height);
        }
    }

    /**
     * Adds a vertically orientated wall to the Pane
     *
     * @param int x, int y, double width, double height
     */
    private void addVerticalWall(int x, int y, double width, double height) {
        if (x == (gameMap.getHeight() - 1)) {
            addWall(new Wall(10.0, height + 10.0), MainGUI.WIDTH - 10, y * height);
        } else {
            addWall(new Wall(10.0, height + 10.0), x * width, y * height);
        }
    }

    /**
     * Creates the map of TankRoyale on JavaFX
     * | are vertical walls
     * # are horizontal walls
     * ^ are corners
     */
    /*public void createMap() throws FileNotFoundException {
        gamemap = new Map(mapFilePath);
        char[][] map = gamemap.getCharMap();
        //Adjusting the height or width of the text file to fit the size of the javafx screen
        double height = (MainGUI.HEIGHT - 100) / gamemap.getHeight();
        double width = MainGUI.WIDTH / gamemap.getWidth();
        for (int y = 0; y < gamemap.getHeight(); y++) {
            for (int x = 0; x < gamemap.getWidth(); x++) {
                switch (map[y][x]) {
                    case ' ':
                        break;
                    case '^':
                        addVerticalWall(x, y, width, height / 2);
                        addHorizontalWall(x, y, width / 2, height);
                        break;
                    case '|':
                        addVerticalWall(x, y, width, height);
                        break;
                    case '#':
                        addHorizontalWall(x, y, width, height);
                        break;

                }
            }
        }
    }*/
    public void createMap() throws FileNotFoundException {
        gameMap = new Map(MenuGUI.getMapFilePath());
        char[][] charMap = gameMap.getCharMap();
        buildHorizWalls(charMap);
        buildVertWalls(charMap);
    }

    /**
     * @param charMap
     */
    public void buildHorizWalls(char[][] charMap) {
        for (int y = 0; y < charMap.length; y++) {
            int x = 0, endX = charMap[y].length;
            if (y != 0 && y != charMap.length - 1) {
                x = 1;
                endX = charMap[y].length - 1;
            }

            int startX = 0;
            int horizCount = 0;
            for (; x < endX; x++) {
                // Counting char walls
                if (charMap[y][x] == '#') {
                    if (horizCount == 0) {
                        startX = x;
                    }
                    horizCount++;
                }

                // Build and position a new wall when we reach the end of a char wall
                if (horizCount > 0 && (charMap[y][x] == ' ' || x == endX - 1)) {
                    Wall temp = new Wall((double) horizCount / charMap[y].length * MainGUI.WIDTH, 10);

                    // % of char wall dimension * GUI Dimension
                    double guiX = (double) startX / charMap[y].length * MainGUI.WIDTH;
                    double guiY = (double) y / charMap.length * (MainGUI.HEIGHT - 100);

                    if (y == charMap.length - 1) { //Displacement for the bottom horizontal wall
                        double displacement = 1.0 / charMap.length * (MainGUI.HEIGHT - 100);
                        addWall(temp, guiX, guiY + displacement);
                    } else {
                        addWall(temp, guiX, guiY);
                    }

                    horizCount = 0;
                }
            }
        }
    }

    public void buildVertWalls(char[][] charMap) {
        for (int x = 0; x < charMap[0].length; x++) {
            //System.out.println("X: " + x);
            int y = 0, endY = charMap.length;
            if (x != 0 && x != charMap[0].length - 1) {
                y = 1;
                endY = charMap.length - 1;
            }

            //Start of the wall and the wall length count
            int startY = 0;
            int vertCount = 0;
            for (; y < endY; y++) {
                //System.out.println("    Y: " + y);
                //Counting char walls
                if (charMap[y][x] == '|') {
                    if (vertCount == 0) {
                        startY = y;
                    }
                    vertCount++;
                }
                // System.out.println(charMap[y][x] + " vertcount: " + vertCount + " starty " + startY);

                // Build and position a new wall when we reach the end of a char wall
                if (vertCount > 0 && (charMap[y][x] == ' ' || y == endY - 1)) {
                    //Wall temp = new Wall (10, ((double) vertCount / charMap.length * MainGUI.HEIGHT));
                    Wall temp;
                    if (x == charMap[0].length - 1 || x == 0) {
                        temp = new Wall(10, (double) vertCount / charMap.length * MainGUI.HEIGHT);
                    } else {
                        temp = new Wall(10, ((double) vertCount / charMap.length * MainGUI.HEIGHT) * 1.5);
                    }

                    // % of char wall dimension * GUI Dimension
                    double guiX = (double) x / charMap[0].length * MainGUI.WIDTH;
                    double guiY = (double) startY / charMap.length * (MainGUI.HEIGHT - 100) - 1.0 / charMap.length * (MainGUI.HEIGHT - 100);
                    //double guiY = (double)startY / charMap.length * (MainGUI.HEIGHT - 100);
                    //System.out.println("guiX " + guiX + " guiy " + guiY);

                    if (x == charMap[0].length - 1) {
                        double displacement = 1.0 / charMap[0].length * MainGUI.WIDTH - 10; // -10
                        addWall(temp, guiX + displacement, guiY);
                    } else {
                        addWall(temp, guiX, guiY + 10);
                    }

                    vertCount = 0;
                }
            }

        }
    }


    /**
     * Checks if the tank in the argument is colliding with any wall in the map
     *
     * @param tank
     * @return boolean
     */
    private boolean checkPoint(Tank tank) {
        for (Wall wall : walls) {
            if (tank.isColliding(wall))
                return true;
        }
        return false;
    }

    /**
     * Spawns the tank onto the map
     *
     * @param tank the tank that is spawned
     */
    private void spawn(Tank tank) {
        double x = (double) rng(40, (int) MainGUI.WIDTH - 40);
        double y = (double) rng(40, (int) MainGUI.HEIGHT - 140);
        addTank(tank, x, y);
    }

    /**
     * Spawns tank in a random location on the map
     */
    private void randomSpawn(Tank tank) {
        spawn(tank);
        while (checkPoint(tank)) { //Ensures tank does not spawn in a wall
            visual.removeTank(tank);
            tanks.remove(tanks.size() - 1);
            spawn(tank);
        }
    }


    /**
     * Detects Collisions between bullets,tanks, and walls walls.
     */
    private void detectCollisions() {
        for (Bullet bullet : bullets) {
            //Detects collision of bullet with tank
            for (Tank tank : tanks) {
                if (bullet.isColliding(tank) && bullet.getLifeTime() < 591) {
                    bullet.setAlive(false);
                    tank.setAlive(false);
                    //Removes collided entities from the layout
                    visual.removeAll(bullet, tank);
                } else if (bullet.getLifeTime() == 0) {
                    bullet.setAlive(false);
                    visual.removeBullet(bullet);
                }
            }

            // Detects collision of bullet with wall
            for (Wall wall : walls) {
                if (bullet.isColliding(wall)) {
                    ricochet(wall, bullet);
                }
            }
        }

        //Detects collision with walls and tanks
        for (GameEntity wall : walls) {
            for (Tank tank : tanks) {
                if (tank.isColliding(wall)) {
                    tank.setVelocity(new Point2D(0, 0));
                    tank.stop();
                    tank.getView().setTranslateX(tank.getView().getTranslateX() - tank.getFacing().getX() * tank.getMoveDir());
                    tank.getView().setTranslateY(tank.getView().getTranslateY() - tank.getFacing().getY() * tank.getMoveDir());

                }
            }
        }
    }


    /**
     * Richochets bullets off of a wall
     *
     * @param Wall wall, Bullet bullet
     */
    private void ricochet(Wall wall, Bullet bullet) {
        if (MenuGUI.isColoured()) {
            colourRichochet(wall, bullet);
        }

        if (bullet.getView().getTranslateX() < wall.getX1() + 2.0) {
            bullet.setVelocity(new Point2D(-1 * bullet.getVelocity().getX(), bullet.getVelocity().getY()));
            //System.out.println("LEFT");
        }
        if (bullet.getView().getTranslateX() > wall.getX2() - 2.0) {
            bullet.setVelocity(new Point2D(-1 * bullet.getVelocity().getX(), bullet.getVelocity().getY()));
            //System.out.println("RIGHT");
        }

        if (bullet.getView().getTranslateY() < wall.getY1() + 2.0) {
            bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1 * bullet.getVelocity().getY()));
            //System.out.println("UP");
        }
        if (bullet.getView().getTranslateY() > wall.getY2() - 2.0) {
            bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1 * bullet.getVelocity().getY()));
            //System.out.println("DOWN");
        }
    }

    /**
     * Changes colour of wall and bullet to random RGB
     *
     * @param wall
     * @param bullet
     */
    private void colourRichochet(Wall wall, Bullet bullet) {
        Circle c = (Circle) bullet.getView();
        c.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
        Rectangle r = (Rectangle) wall.getView();
        r.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
    }

    /**
     * creates random walls
     */
    public void createColourMap() {
        Wall b1 = new Wall(1080, 10);
        Wall b2 = new Wall(1080, 10);
        Wall b3 = new Wall(10, 508);
        Wall b4 = new Wall(10, 508);

        addWall(b1, 0, 0);
        addWall(b2, 0, 608 - 100);
        addWall(b3, 0, 0);
        addWall(b4, 1080 - 10, 0);

        int numWalls = rng(10, 15);
        for (int x = 0; x < numWalls; x++) {
            Wall temp;
            if (rng(1, 100) <= 30) {
                temp = new Wall(rng(50, 130), rng(50, 130));
            } else if (rng(1, 100) <= 55) {
                temp = new Wall(10, 150);
            } else {
                temp = new Wall(150, 10);
            }
            addWall(temp, rng(20, 1000), rng(20, 450));
        }
    }

    /**
     * Removes dead entities from the arraylists
     * Dead tanks must be replaced, so that the arraylist order doesn't change
     * and tanks have to be removed from the arraylist so that the non-visual bounds of it are removed completely from the Pane
     */
    private void clearDeadEntities() {
        bullets.removeIf(bullet -> bullet.isDead());

        for (int x = 0; x < tanks.size(); x++) {
            if (tanks.get(x).isDead()) {
                Tank temp = new Tank();
                temp.setAlive(false);
                tanks.set(x, temp);
            }
        }
    }

    public void shoot(Tank tank) {
        Bullet bullet = tank.shoot();
        //double x = tank.getView().getTranslateX() + tank.getFacing().normalize().multiply(40).getX();
        //double y = tank.getView().getTranslateY() + tank.getFacing().normalize().multiply(40).getY();
        double x = tank.getView().getTranslateX() + tank.getFacing().normalize().multiply(17).getX();
        double y = tank.getView().getTranslateY() + tank.getFacing().normalize().multiply(17).getY();
        addBullet(bullet, x, y);
    }

    /**
     * Randomly generates a number between the min and max values in the argument.
     *
     * @param min lower boundary value.
     * @param max upper boundary value.
     * @return random integer
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

//    public class Block extends GameEntity {
//        public double width;
//        public double height;
//
//
//        public Block(double width, double height) {
//            super(new Rectangle(width, height, Color.rgb(rng(0, 255), rng(0,255), rng(0, 255))));
//            this.width = width;
//            this.height = height;
//        }
//
//    }

//    public ArrayList<Block> blocks = new ArrayList<>();
//
//    public void placeBlocks() {
//        Block b1 = new Block(1080, 10);
//        Block b2 = new Block(1080, 10);
//        Block b3 = new Block(10, 608);
//        Block b4 = new Block(10, 608);
//
//        blocks.add(b1);
//        blocks.add(b2);
//        blocks.add(b3);
//        blocks.add(b4);
//
//        addGameEntity(b1, 0, 0);
//        addGameEntity(b2, 0, 608 - 10);
//        addGameEntity(b3, 0, 0);
//        addGameEntity(b4, 1080 - 10, 0);
//
//        int numWalls = rng(15, 20);
//        for (int x = 0; x < numWalls; x++) {
//            Block temp;
//            if (rng(1, 100) <= 30) {
//                temp = new Block(rng(100, 200), rng(100, 200));
//            } else if (rng(1, 100) <= 70) {
//                temp = new Block(10, 150);
//            } else {
//                temp = new Block(150, 10);
//            }
//            blocks.add(temp);
//            addGameEntity(temp, rng(20, 1000), rng(20, 590));
//        }
//    }
//
//    private void ricochet(Block block, Bullet bullet) {
//        Circle c = (Circle) bullet.getView();
//        c.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
//        Rectangle r = (Rectangle) block.getView();
//        r.setFill(Color.rgb(rng(0, 255), rng(0, 255), rng(0, 255)));
////        System.out.println("x1: " + block.getView().getTranslateX() + " y1: " + block.getView().getTranslateY());
////        System.out.println("x2: " + (block.getView().getTranslateX() + block.width) + " y1: " + block.getView().getTranslateY());
////        System.out.println("x1: " + block.getView().getTranslateX() + " y2: " + (block.getView().getTranslateY() + block.height));
////        System.out.println("x2: " + (block.getView().getTranslateX() + block.width) + " y2: " + (block.getView().getTranslateY() + block.height));
//
////        System.out.println("x1: " + block.getView().getTranslateX());
////        System.out.println("x2: " + (block.getView().getTranslateX() + block.width));
////        System.out.println("y1: " + block.getView().getTranslateY());
////        System.out.println("y2: " + (block.getView().getTranslateY() + block.height));
//        double x1 = block.getView().getTranslateX();
//        double x2 = block.getView().getTranslateX() + block.width;
//        double y1 = block.getView().getTranslateY();
//        double y2 = block.getView().getTranslateY() + block.height;
//
//        if (bullet.getView().getTranslateX() < x1 + 5.0) {
//            bullet.setVelocity(new Point2D(-1 * bullet.getVelocity().getX(), bullet.getVelocity().getY()));
//            //System.out.println("LEFT");
//        }
//
//        if (bullet.getView().getTranslateX() > x2 - 5.0) {
//            bullet.setVelocity(new Point2D(-1 * bullet.getVelocity().getX(), bullet.getVelocity().getY()));
//            //System.out.println("RIGHT");
//        }
//
//        if (bullet.getView().getTranslateY() < y1 + 5.0) {
//            bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1 * bullet.getVelocity().getY()));
//            //System.out.println("UP");
//        }
//
//        if (bullet.getView().getTranslateY() > y2 - 5.0) {
//            bullet.setVelocity(new Point2D(bullet.getVelocity().getX(), -1 * bullet.getVelocity().getY()));
//            //System.out.println("DOWN");
//        }
//        //bullet.setVelocity(new Point2D(0,0));
//        //bullet.setVelocity(new Point2D(-1 * bullet.getVelocity().getX(), bullet.getVelocity().getY()));
//    }

}