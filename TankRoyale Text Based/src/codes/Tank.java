package codes;

public class Tank extends GameEntity {
	public static int tankCount = 0;
	private char ID;
	private boolean isAlive = true;
	
	public Tank(int x, int y) {
		super(x, y);
		tankCount++;
		ID = (char)(tankCount + '0');
	}
	
	public char getID() {
		return ID;
	}
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dies() {
		isAlive = false;
		System.out.println("\nTank " + ID + " died!");
	}
	
	public void failedMove() {
		System.out.println("Tank " + ID + " was blocked from moving in the specified direction!");
	}
	
	public void performCommand(String command, Map map) {
		switch (command) {
		case "MOVEUP" :
			if(getY() - 1 > 0 && map.getCharMap()[getY()-1][getX()] == ' ') {
				map.getCharMap()[getY()-1][getX()] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setY(getY()-1);
			}
			else {
				failedMove();
			}
			break;
		case "MOVEDOWN" :
			if(getY() + 1 < map.getHeight() - 1 && map.getCharMap()[getY()+1][getX()] == ' ') {
				map.getCharMap()[getY()+1][getX()] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setY(getY()+1);
			}
			else {
				failedMove();
			}
			break;
		case "MOVELEFT" :
			if(getX() - 1 > 0 && map.getCharMap()[getY()][getX()-1] == ' ') {
				map.getCharMap()[getY()][getX()-1] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setX(getX()-1);
			}
			else {
				failedMove();
			}
			break;
		
		case "MOVERIGHT" :
			if(getX() + 1 < map.getWidth() && map.getCharMap()[getY()][getX()+1] == ' ') {
				map.getCharMap()[getY()][getX()+1] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setX(getX()+1);
			}
			else {
				failedMove();
			}
			break;
		case "SHOOTUP" :
			break;
		case "SHOOTDOWN" :
			break;
		case "SHOOTLEFT" :
			break;
		case "SHOOTRIGHT" :
			break;
		
		}
	}
	
}
