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
	
	public void failedMove(String direction) {
		System.out.println("Tank " + ID + " was blocked from moving " + direction + "!");
	}
	
	public void shoot(int xDir, int yDir, Map map, Tank otherTank){
	    Bullet bullet = new Bullet(getX(), getY());
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
			if(map.getCharMap()[getY()-1][getX()] == ' ') {
				map.getCharMap()[getY()-1][getX()] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setY(getY()-1);
			}
			else {
				failedMove("up");
			}
			break;
		case "MOVEDOWN" :
			if(map.getCharMap()[getY()+1][getX()] == ' ') {
				map.getCharMap()[getY()+1][getX()] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setY(getY()+1);
			}
			else {
				failedMove("down");
			}
			break;
		case "MOVELEFT" :
			if(map.getCharMap()[getY()][getX()-1] == ' ') {
				map.getCharMap()[getY()][getX()-1] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setX(getX()-1);
			}
			else {
				failedMove("left");
			}
			break;
		
		case "MOVERIGHT" :
			if(map.getCharMap()[getY()][getX()+1] == ' ') {
				map.getCharMap()[getY()][getX()+1] = ID;
				map.getCharMap()[getY()][getX()] = ' ';
				setX(getX()+1);
			}
			else {
				failedMove("right");
			}
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
