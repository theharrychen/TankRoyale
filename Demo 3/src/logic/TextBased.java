package logic;

import visuals.*;

import java.util.Scanner;

public class TextBased {

    private static TextBasedDisplay display = new TextBasedDisplay();
    private static Map gameMap = null;
    private static Tank tank1 = new Tank(5, 5);
    private static Tank tank2 = new Tank(0, 0);
    private static boolean gameStart = false;
    private static boolean endGame = false;
    private static int mapChoice = 1; //Selected map based on menu screen
    private static int turn = 1; //Players turn
    private static Scanner input = new Scanner(System.in);
    private static Scanner selection = new Scanner(System.in);
    private static String userCommand = null; //User input
    
    public static void start() {
        while(!gameStart)
            display.menu();
        try {
            createMap();
        }
        catch (Exception e){
            System.out.println("Was unable to load in the map");
        }
   
        display.spawn(tank1, gameMap);
        display.spawn(tank2, gameMap);

        onUpdate();
    }

    public void setGameStart(boolean start){
        gameStart = start;
    }

    public void setMapChoice(int choice){
        mapChoice = choice;
    }

    public void setEndGame(boolean end){
        endGame = end;
    }

    public int getTurn(){
        return turn;
    }

    public static void onUpdate() {
        while(tank1.isAlive() && tank2.isAlive()){
                display.display(gameMap);
                userCommand = input.nextLine().toUpperCase();

                System.out.println("\n");
                if (turn == 1) {
					tank1.performCommand(userCommand, gameMap, tank2);
					turn = 2;
				} else if (turn == 2) {
					tank2.performCommand(userCommand, gameMap, tank1);
					turn = 1;
				}
            }
        display.results(tank1.isAlive(), tank2.isAlive());
        display.endScreen();
            
        if(endGame)
            input.close();
    }
    
    /**
     * Creates the map for the text based version of the game
     */
    public static void createMap() {
        switch(mapChoice){
            case 1:
            gameMap = new Map("/resources/textbased/maze.txt");
            break;
            case 2:
            gameMap = new Map("/resources/textbased/emptymap.txt");
            break;
            case 3:
            gameMap = new Map("/resources/textbased/cross-section.txt");
            break;
        }
    }

    /**
     * Makes sure player input is valid. If input is invalid an appropriate message is displayed
     * 
     * @param minchoice minimum accepted player input
     * @param maxchoice maximum accepted player input
     * @return returns the players choice as an integer
     */
    public int errorTrap(int minchoice, int maxchoice) {// Integer Error Trap Method
		boolean success;
		int choice = 0;
		do {
			success = true;
			try {
				choice = selection.nextInt();
			} catch (Exception e) {
				selection.nextLine();
				success = false;
			}
			if (choice < minchoice || choice > maxchoice || success == false) {
				System.out.println("Error: Invalid Output!");
			}

        } while (choice < minchoice || choice > maxchoice || success == false);
        
		return choice;
    }
    
    /**
     * Random number generator
     * 
     * @param min lower boundary value
     * @param max upper boundary value
     * @return random integer value between min and max
     */
    public int rng(int min, int max) {
		if (min > max) { 
		// Argument Error Trap
			int temp = min;
			min = max;
			max = temp;
		}
		int number = (int) (Math.random() * (max - min + 1) + min);
		return number;
    }
    
    public void restart(){
        tank1.revive();
        tank2.revive();
        start();
    }
}