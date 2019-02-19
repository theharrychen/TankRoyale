package codes;

public class Tank extends GameEntity {
	public static int tankCount = 0;
	private char ID;
	
	public Tank(int x, int y) {
		super(x, y);
		tankCount++;
		ID = (char)(tankCount + '0');
	}
	
	public char getID() {
		return ID;
	}

}
