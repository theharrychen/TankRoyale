package codes;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static final double WIDTH = 1080.0, HEIGHT = 608; //16:9 Aspect Ratio

    @Override
    public void start(Stage stage) throws Exception {
        //Placeholder is game.getRoot();
        Pane placeholder = new Pane();
        Scene gameScene = new Scene(placeholder, WIDTH, HEIGHT);
        stage.setScene(gameScene);
        stage.setTitle("Tank Royale");
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }

}
