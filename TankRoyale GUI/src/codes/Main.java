package codes;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 1080.0, HEIGHT = 608; //16:9 Aspect Ratio
    private Game game = new Game();

    @Override
    public void start(Stage stage) throws Exception {
        
        Scene gameScene = new Scene(game.getRoot(), WIDTH, HEIGHT);
        stage.setScene(gameScene);
        stage.getScene().setOnKeyPressed(game.handle); //does this work?
        stage.setTitle("Tank Royale");
        
        game.start();
        
        new AnimationTimer(){
            public void handle(long now){
                game.gameUpdate();
            }
        }.start();
        
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
