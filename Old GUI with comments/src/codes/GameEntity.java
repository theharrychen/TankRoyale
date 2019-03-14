package codes;

import javafx.scene.Node;

public class GameEntity { // By default a physically "static" object

    private Node view;
    private boolean alive = true;

    public GameEntity(Node view) {
        setView(view);
    }

    public void setView(Node view) {
        this.view = view;
    }

    public Node getView() {
        return view;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isDead() {
        return !alive;
    }

    public boolean isColliding(GameEntity other) {
        return getView().getBoundsInParent().intersects(other.getView().getBoundsInParent());
    }

}