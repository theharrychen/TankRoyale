package codes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 1080.0, HEIGHT = 608; //16:9 Window Aspect Ratio
    private Game game = new Game();

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tank Royale");
        stage.setResizable(false);

        game.start();
        Scene gameScene = new Scene(game.getRoot(), WIDTH, HEIGHT);

        stage.setScene(gameScene);
        gameScene.setOnKeyPressed(game.new PressHandler());
        gameScene.setOnKeyReleased(game.new ReleaseHandler());

        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
