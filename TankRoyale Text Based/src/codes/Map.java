package codes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map {
	private char[][] map;
	private int height = 0, width = 0;

	public Map(String textPath) throws FileNotFoundException {
		File inFile = new File(textPath);
		Scanner input = new Scanner(inFile);
		ArrayList<String> stringMap = new ArrayList<String>();		
		
		while (input.hasNextLine()) {
			stringMap.add(input.nextLine());
			height++;
		}
		input.close();
		
		width = stringMap.get(0).length();
		map = new char[height][width];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if (stringMap.get(y).charAt(x) == '0') {
					map[y][x] = ' ';
				}
				else {
					map[y][x] = stringMap.get(y).charAt(x);
				}
			}
		}
	
	}
	
	public void display() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				System.out.print(map[y][x]);
			}
			System.out.println();
		}
	}
	
	public void spawn(Tank tank) {
		while (map[tank.getY()][tank.getX()] != ' ') {
			tank.setY(Game.rng(1, height-1));
			tank.setX(Game.rng(1, width-1));
		}
		map[tank.getY()][tank.getX()] = tank.getID();
	}

}
