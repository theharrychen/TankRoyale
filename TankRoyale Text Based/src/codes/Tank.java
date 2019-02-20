package codes;

import java.util.Scanner;

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
		System.out.println("Tank " + ID + " died!");
	}
	
	public void move(int xDir, int yDir, Map map) {
		if(map.getCharMap()[getY()+yDir][getX()+xDir] == ' ') {
			map.getCharMap()[getY()+yDir][getX()+xDir] = ID;
			map.getCharMap()[getY()][getX()] = ' ';
			setX(getX()+xDir);
			setY(getY()+yDir);
		}
		else {
			System.out.println("Tank " + ID + " was unable to move in the specified direction!");
		}
	}
	
	@SuppressWarnings("resource")
	public void shoot(int xDir, int yDir, Map map, Tank otherTank){
	    Bullet bull = new Bullet(getX(), getY());
	    Scanner input = new Scanner(System.in);
	    
	    if (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == ' ') {
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = Bullet.symbol;
			bull.setX(bull.getX()+ xDir);
			bull.setY(bull.getY()+ yDir);
			map.display();
	    }
		System.out.println("Press ENTER to continue...");
		input.nextLine();
	    
	    while (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == ' ') {
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = Bullet.symbol;
			map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			bull.setX(bull.getX()+ xDir);
			bull.setY(bull.getY()+ yDir);
			map.display();
			
			System.out.println("Press ENTER to continue...");
			input.nextLine();
	    }
	    
	    if(map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == '#') { // Bullet hits a wall
	    	// When the tank is not beside the wall
	    	if (map.getCharMap()[bull.getY()][bull.getX()] != ID) { 
		    	map.getCharMap()[bull.getY()][bull.getX()] = ' ';
			}
	    }
	    else if (map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] == otherTank.getID()) { // Bullet hits other tank
	        otherTank.dies();
	        if(map.getCharMap()[bull.getY()][bull.getX()] != ID){
	            map.getCharMap()[bull.getY()][bull.getX()] = ' ';
	        }
	        map.getCharMap()[bull.getY()+yDir][bull.getX()+xDir] = 'X';
	        map.display();
	    }
	    
	}
	
	public void performCommand(String command, Map map, Tank otherTank) {
		switch (command) {
		case "MOVEUP" :
			move(0, -1, map);
			break;
		case "MOVEDOWN" :
			move(0, 1, map);
			break;
		case "MOVELEFT" :
			move(-1, 0, map);
			break;
		case "MOVERIGHT" :
			move(1, 0, map);
			break;
		case "SHOOTUP" :
		    shoot(0, -1, map, otherTank);
			break;
		case "SHOOTDOWN" :
		    shoot(0, 1, map, otherTank);
			break;
		case "SHOOTLEFT" :
		    shoot(-1, 0, map, otherTank);
			break;
		case "SHOOTRIGHT" :
		    shoot(1, 0, map, otherTank);
			break;
		}
	}
	
}
