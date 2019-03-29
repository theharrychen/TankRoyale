package visuals;

/**
 * This class is used to display the text-based version of the game
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-20
 */
import logic.*;

public class TextBasedDisplay{

    private int mapChoice;
    private int playChoice;
    private TextBased game = new TextBased();
    
    /**
     * Displays the 2D char array in the console
     */
    public void display(Map gameMap){
        System.out.println("Commands: \"MOVEUP\", \"MOVEDOWN\", \"MOVERIGHT\", \"MOVELEFT\",");
        System.out.println("          \"SHOOTUP\", \"SHOOTDOWN\", \"SHOOTRIGHT\", \"SHOOTLEFT\"");
        for (int y = 0; y < gameMap.getHeight(); y++) {
			for (int x = 0; x < gameMap.getWidth(); x++) {
				System.out.print(gameMap.getCharMap()[y][x]);
			}
			System.out.println();
        }
        System.out.println("Tank " + game.getTurn() + "\'s turn: ");
    }

    /**
     * The menu/map selection screen (currently only 1 choice)
     */
    public void menu(){
        System.out.println(" ");
		System.out.println("-----[TANK ROYALE]-----");
        System.out.println("Maps: ");
        System.out.println("1 - 'Maze Map' \n" + "2 - 'Empty map' \n" + "3 - 'Cross-Section map'");
        System.out.println(" ");
        System.out.print("Enter Selection: ");

        mapChoice = game.errorTrap(1, 3);

        switch (mapChoice){
            case 1:
            game.setMapChoice(1);
            System.out.println(" ");
            System.out.println("Maze Map was selected!");
            System.out.println(" ");
            game.setGameStart(true);
            break;
            case 2:
            game.setMapChoice(2);
            System.out.println(" ");
            System.out.println("Empty Map was selected!");
            System.out.println(" ");
            game.setGameStart(true);
            break;
            case 3:
            game.setMapChoice(3);
            System.out.println(" ");
            System.out.println("Cross-Section Map was selected!");
            System.out.println(" ");
            game.setGameStart(true);
            break;
        }
    }

    /**
     * Displays the results of the game
     */
    public void results(boolean p1, boolean p2){
        if (p1 && !p2) {
            System.out.println("CONGRATULATIONS TANK 1 WON THE GAME!");
        } else if (!p1 && p2) {
            System.out.println("CONGRATULATIONS TANK 2 WON THE GAME!");
        }
    }

    /**
     * End game screen to either restart or end the game
     */
    public void endScreen(){
        System.out.println("\nWould you like to play again? 1. YES 2. NO");
        playChoice = game.errorTrap(1, 2);
        if (playChoice == 1) {
            game.setGameStart(false);
            game.restart();
            for (int x = 0; x < 1000; x++)
                System.out.println();
        } else {
            System.out.println("Game Terminated.");
            game.setEndGame(true);
        }
    }

    /**
	 * Updates the 2D character array to contain a tank object's ID at the tank's
	 * coordinates. If the tank's coordinates are invalid, spawns new coordinates.
	 * 
	 * @param Tank tank
	 */
	public void spawn(Tank tank, Map gameMap) {
		if (gameMap.getCharMap()[tank.getY()][tank.getX()] == ' ') {
			gameMap.getCharMap()[tank.getY()][tank.getX()] = tank.getID();
		} else {
			randomSpawn(tank, gameMap);
		}
    }
    
    /**
	 * Randomly generates the initial coordinates of a Tank object. Tank can only
	 * spawn in an empty(' ') space or else new coordinates are generated.
	 * 
	 * @param Tank tank
	 */
	public void randomSpawn(Tank tank, Map gameMap) {
		tank.setY(game.rng(1, gameMap.getHeight() - 1));
		tank.setX(game.rng(1, gameMap.getWidth() - 1));

		while (gameMap.getCharMap()[tank.getY()][tank.getX()] != ' ') {
			tank.setY(game.rng(1, gameMap.getHeight() - 1));
			tank.setX(game.rng(1, gameMap.getWidth() - 1));
		}
		gameMap.getCharMap()[tank.getY()][tank.getX()] = tank.getID();
	}
}