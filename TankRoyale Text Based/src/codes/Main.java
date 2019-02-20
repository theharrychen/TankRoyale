package codes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);
		Map map = new Map("src/resources/emptymap.txt");
		
		Tank tank1 = new Tank(5, 3);
		Tank tank2 = new Tank(5, 5);
		map.randomSpawn(tank1);
		map.randomSpawn(tank2);
		
		int turn = 1;

		while (tank1.isAlive() && tank2.isAlive()) {
			System.out.println("Commands: \"MOVEUP\", \"MOVEDOWN\", \"MOVERIGHT\", \"MOVELEFT\",");
			System.out.println("          \"SHOOTUP\", \"SHOOTDOWN\", \"SHOOTRIGHT\", \"SHOOTLEFT\"");
			map.display();
			System.out.println("Player " + turn + "\'s turn: ");
			
			//TODO Check for valid input
			String userCommand1 = input.nextLine().toUpperCase();
			String userCommand2 = input.nextLine().toUpperCase();
			
			System.out.println("\n");
			if (turn == 1) {
				tank1.performCommand(userCommand1, map, tank2);
				tank1.performCommand(userCommand2, map, tank2);
				turn = 2;
			}
			else if (turn == 2) {
				tank2.performCommand(userCommand1, map, tank1);
				tank2.performCommand(userCommand2, map, tank1);
				turn = 1;
			}
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
