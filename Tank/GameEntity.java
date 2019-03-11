package codes;

	import javafx.geometry.Point2D;
	import javafx.scene.Node;

	/** Class is intended to represent dynamic objects that
	exsist on the game console. The subclass handles movement,
	while inheriting all the attributes of a static object.
	*/
public class GameEntity extends StaticGameEntity{
	private boolean alive = true;
	private double front_facing = 1.0;
	private boolean pauseInp = false;

	/** front_facing used to differentiate the position the "front" of the
	moving object is facing based on a coordinate system.
	1.0 = North
	2.0 = East
	3.0 = South
	4.0 = West

	pauseInp used to pause all other inputs within certain methods
	*/

	/** @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
	 */

	 //Constructor
	public GameEntity(Node view) {
		super(view);
		setAlive(true);
		}

			//Alters state of the object.
      public boolean isAlive() {
				this.setState(true);
        return alive;
      }

      public boolean isDead() {
					this.setState(false);
          return !alive;
      }

      public void setState(boolean state) {
          this.alive = state;
      }

			//Movement methods
	    public double getRotate() {
	        return view.getRotate();
	    }

	    public void rotateRight() {
	        view.setRotate(view.getRotate() + 5);
	        setPosition(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
	    }

	    public void rotateLeft() {
	        view.setRotate(view.getRotate() - 5);
	        setPosition(new Point2D(Math.cos(Math.toRadians(getRotate())), Math.sin(Math.toRadians(getRotate()))));
	    }

	    public boolean isColliding(GameObject other) {
	        return super.getView().getBoundsInParent().intersects(super.other.getView().getBoundsInParent());
	    }

			//Moves the object to the next right based on the cartesian plane
			public void turnRight() {
				pauseInp = true;

				while(front_facing != front_facing + 1.0) {
					rotateRight()
				}
				pauseInp = false;
			}

			//Moves the object to the next left based on the cartesian plane
			public void turnLeft() {
				pauseInp = true;

				while(front_facing != front_facing - 1.0) {
					rotateLeft()
				}
				pauseInp = false;
			}

			//Moves the object forward
			public void moveForward() {
				pauseInp = true;
				float move_factor = 3.0; /**Pixels the node will move by, can be
				altered depending on screen resolution
				*/

				if(!pauseInp) { //Ensures movement cannot occur during rotation

					if(front_facing == 1.0) {
						setTranslateY(move_factor); //Moves the node in the Y direction 3 pixels

					} else if(front_facing == 2.0) {
						setTranslateX(move_factor); //Moves the node in the X direction 3 pixels

					} else if(front_facing == 3.0) {
						setTranslateY(-move_factor); //Moves the node in the Y direction 3 pixels down

					} else if(front_facing == 4.0) {
						setTranslateY(-move_factor); //Moves the node in the X direction 3 pixels left
					}
				}

			}

	}
