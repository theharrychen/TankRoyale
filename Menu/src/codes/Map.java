package codes;

/**
 *The map class creates a 2D visual representation of the game space
 *@author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 *@version 1.0
 *@since Feb. 19th, 2019
 */

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Map{
	private char[][] charMap;
	private int height = 0, width = 0;

	/**
	 * Constructs a 2D character array from a specified textfile throws
	 * FileNotFoundException if textfile is not valid # in textfile represents a
	 * wall 0 in textfile represents space
	 * 
	 * @param String textPath
	 * @return character double array representing a 2D map
	 */
	public Map(String textPath) {
		InputStream stream = Game.class.getResourceAsStream(textPath);
		if (stream == null) System.out.println("Map Text file not located");

		Scanner input = null;
		try {
			input = new Scanner (stream);
		} catch (Exception e) {
			System.out.println("Error in loading map text file");
		}

		ArrayList<String> stringMap = new ArrayList<String>();

		while (input.hasNextLine()) {
			stringMap.add(input.nextLine());
			height++;
		}
		input.close();

		width = stringMap.get(0).length();
		charMap = new char[height][width];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (stringMap.get(y).charAt(x) == '0') {
					charMap[y][x] = ' ';
				} else {
					charMap[y][x] = stringMap.get(y).charAt(x);
				}
			}
		}

	}

	/**
	 * @return the 2D character array
	 */
	public char[][] getCharMap() {
		return charMap;
	}

	/**
	 * sets the 2D character array
	 */
	public void setCharMap(char[][] charMap) {
		this.charMap = charMap;
	}

	/**
	 * @return the height of the 2D map
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * sets the height of the 2D map
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return width of 2D map
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * sets the width of the map
	 */
	public void setWidth(int width) {
		this.width = width;
	}


}
