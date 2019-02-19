package codes;

import java.io.FileNotFoundException;

public class Game {

	public static void main(String[] args) throws FileNotFoundException {
		Map map = new Map("src/resources/map.txt");
		map.display();
		
		Tank tank1 = new Tank(5, 3);
		Tank tank2 = new Tank(12, 5);
		
		map.spawn(tank1);
		map.spawn(tank2);
		
		map.display();

	}
	
    public static int rng(int min, int max){ //Random Number Generator
        if(min > max){ //Argument Error Trap
            int temp = min;
            min = max;
            max = temp;
        }
        int number = (int)(Math.random()*(max - min + 1) + min);
        return number;
    }
}
