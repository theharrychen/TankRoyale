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
	    Bullet bullet = new Bullet(getX(), getY());
	    Scanner input = new Scanner(System.in);
	    
	    if (map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] == ' ') {
	        map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] = Bullet.symbol;
			bullet.setX(bullet.getX()+ xDir);
			bullet.setY(bullet.getY()+ yDir);
			map.display();
	    }
	    
	    while (map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] == ' ') {
	        map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] = Bullet.symbol;
			map.getCharMap()[bullet.getY()][bullet.getX()] = ' ';
			bullet.setX(bullet.getX()+ xDir);
			bullet.setY(bullet.getY()+ yDir);
			map.display();
			
			System.out.println("Press ENTER to continue...");
			input.nextLine();
	    }
	    
	    if(map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] == '#') { // Bullet hits a wall
			map.getCharMap()[bullet.getY()][bullet.getX()] = ' ';
	    }
	    else if (map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] == otherTank.getID()) { // Bullet hits other tank
	        otherTank.dies();
	        if(map.getCharMap()[bullet.getY()][bullet.getX()] != ID){
	            map.getCharMap()[bullet.getY()][bullet.getX()] = ' ';
	        }
	        map.getCharMap()[bullet.getY()+yDir][bullet.getX()+xDir] = 'X';
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
