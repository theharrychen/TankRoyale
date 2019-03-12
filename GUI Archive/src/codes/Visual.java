import javafx.application.Application;
import javafx.scene.layout.Pane;

public class Visual extends Application {
  private Pane root;
//Where all the visual stuff of the game occurs

//NOTE: I DID NOT FULLY EDIT THIS BECAUSE @HARRY SAID HE WOULD TAKE CARE OF IT

//Creating the root
private Parent createContent() {
    root = new Pane();
    root.setPrefSize(600, 600);

    //New player/tank
    tank = new Player();
    player.setVelocity(new Point2D(1, 0));
    addGameObject(player, 300, 300);

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            onUpdate();
        }
    };
    timer.start();

    return root;
}


//Visual representation of first player
private static class Player extends GaemEntitiy {
    Tank() {
        super(new Rectangle(40, 20, Color.BLUE));
    }
}

//Visual representation of second player
private static class Enemy extends GameEntity {
    Tank() {
        super(new Rectangle(40, 20, Color.RED));
    }
}

//Visual representation of the bullets
private static class Bullet extends GameEntity {
    Bullet() {
        super(new Circle(5, 5, 5, Color.BROWN));
    }
}

public static void main(String[] args) {
    launch(args);
}
}
