public class Tank extends GameEntity {

	public static int tankCount = 0;
	private char ID;

	/** Created a new tank with the specificed x and y coordinate.
	The tank is reflected in the map by calling on the superclass
	GameEntity. Everytime a tank is created, the tankcount is
	increased to dictate the number of active tanks and the
	ID is derived from that.
	*/
	public Tank(int x, int y) {
		super(x, y);
		tankCount++;
		ID = (char)(tankCount + '0');
	}

	/** Retrives the ID of the tank.
	@return ID as a char
	*/
	public char getID() {
		return ID;
	}

	/** overrides isDead() of GameEntity
	Changes the state of the object to dead.
	Prints state to console.
	*/
	public void isDead() {
		super.isDead();
		System.out.println("Tank " + ID + " died!");
	}


	/**Shooting
	@param tank: the location of the other tank 
	*/
	public void shoot(GameEntity tank) {
			Bullet bullet = new Bullet();
			bullet.setVelocity(tank.getVelocity().normalize().multiply(5));
			addBullet(bullet, tank.getView().getTranslateX(), tank.getView().getTranslateY());
	}

}
