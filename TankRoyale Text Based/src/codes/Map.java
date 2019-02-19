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
		
		width = stringMap.get(0).length();
		map = new char[height][width];
		
	}
	
	public void display() {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				
			}
		}
	}

}
