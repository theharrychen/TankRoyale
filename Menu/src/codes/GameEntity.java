package codes;

/**
 * This class is used to generate a bullet or project to be shot by the tank.
 * The class extends GameEntity which tracks the bullets x and y coordinates in
 * the generated grid/array.
 * 
 * @author Team 7
 * @version 1.0
 * @since 2019-02-19
 */

import javafx.scene.Node;

public class GameEntity { // By default a physically "static" object

    private Node view; //An item in the scene graph 
    private boolean alive = true;

    //Contructor: Creates a GameEntity
    public GameEntity(Node view) {
        setView(view);
    }

    /**
     *Updates the game Node to the new position
     *@param view: intended new view
    */
    public void setView(Node view) {
        this.view = view;
    }

    //Returns the Node (possible privacy leak)
    public Node getView() {
        return view;
    }

    /**
     *Updates current state to alive.
     *@param alive: new intended state
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //Setting the state to alive
    public boolean isAlive() {
        return alive;
    }

    //Returns false if static is dead 
    public boolean isDead() {
        return !alive;
    }

    //Checks if any two objects are at the same position -> therefore colliding. 
    public boolean isColliding(GameEntity other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

}
