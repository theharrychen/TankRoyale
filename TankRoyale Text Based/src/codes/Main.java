package codes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	private static Map map;
	private static Scanner selection = new Scanner(System.in);
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		boolean quit = false;
		while (!quit)
		{
			System.out.println(" ");
			System.out.println("TANK ROYAL");
			System.out.println(" ");
			System.out.println("Maps: ");
			System.out.println("1 - 'empty map' \n"
								+ "2 - 'maze map' \n"
								+ "3 - 'cross-section map'");
			System.out.println(" ");
			System.out.print("Enter Selection: ");
			int action = selection.nextInt();
			switch (action)
			{
				case 1:
					map = new Map("src/resources/emptymap.txt");
					quit = true;
					System.out.println(" ");
					System.out.println("Empty Map was selected!");
					System.out.println(" ");
					break;
				case 2:
					map = new Map("src/resources/maze.txt");
					System.out.println(" ");
					System.out.println("Maze Map was selected!");
					System.out.println(" ");
					quit = true;
					break;
				case 3:
					map = new Map("src/resources/cross-section.txt");
					System.out.println(" ");
					System.out.println("Cross-Section Map was selected!");
					System.out.println(" ");
					quit = true;
					break;
			}
		}
		
		Tank tank1 = new Tank(5,5);
		Tank tank2 = new Tank(0,0);
		map.spawn(tank1);
		map.randomSpawn(tank2);
		
		int turn = 1;

		while (tank1.isAlive() && tank2.isAlive()) {
			System.out.println("Commands: \"MOVEUP\", \"MOVEDOWN\", \"MOVERIGHT\", \"MOVELEFT\",");
			System.out.println("          \"SHOOTUP\", \"SHOOTDOWN\", \"SHOOTRIGHT\", \"SHOOTLEFT\"");
			map.display();
			System.out.println("Tank " + turn + "\'s turn: ");
			
			//TODO Check for valid input
			String userCommand1 = input.nextLine().toUpperCase();
			//String userCommand2 = input.nextLine().toUpperCase();
			
			System.out.println("\n");
			if (turn == 1) {
				tank1.performCommand(userCommand1, map, tank2);
				//tank1.performCommand(userCommand2, map, tank2);
				turn = 2;
			}
			else if (turn == 2) {
				tank2.performCommand(userCommand1, map, tank1);
				//tank2.performCommand(userCommand2, map, tank1);
				turn = 1;
			}
		}
		
		if (tank1.isAlive() &&  !tank2.isAlive()) {
			System.out.println("CONGRATULATIONS TANK 1 WON THE GAME!");
		}
		else if (!tank1.isAlive() &&  tank2.isAlive()) {
			System.out.println("CONGRATULATIONS TANK 2 WON THE GAME!");
		}
		
		input.close();
	}
	
    public static int rng(int min, int max){ //Random Number Generator
        if (min > max){ //Argument Error Trap
            int temp = min;
            min = max;
            max = temp;
        }
        int number = (int)(Math.random()*(max - min + 1) + min);
        return number;
    }
}
