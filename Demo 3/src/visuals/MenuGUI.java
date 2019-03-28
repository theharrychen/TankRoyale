package visuals;

import javafx.scene.layout.*;
import logic.*;
import drivers.*;
import handlers.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuGUI extends MainGUI{

    private Pane root = new Pane();
    
    public void start(Stage stage){

        Game game = new Game();

        game.start();

        Scene gameScene = new Scene(game.getVisual().getRoot(), WIDTH, HEIGHT);
        gameScene.setOnKeyPressed(new PressHandler());
        gameScene.setOnKeyReleased(new ReleaseHandler());

        menu(stage, gameScene);

        Scene menuScene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(menuScene);
    }

    public void menu(Stage stage, Scene gameScene){
        Image tank = new Image("tanks.jpg");
        ImageView mv = new ImageView();
        mv.setImage(tank);

        Label titleLbl = new Label("Tank Royale");
        //titleLbl.setFont(Font.loadFont("file:resources/fonts/ToetheLineless.ttf", 50));
        titleLbl.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/ToetheLineless.ttf"), 50));
        titleLbl.setPrefWidth(400);
        titleLbl.setLayoutX(545 - titleLbl.getWidth() - 157);
        titleLbl.setLayoutY(125);
        root.getChildren().add(titleLbl);

        Button startBtn = new Button("Start");
        startBtn.setStyle("-fx-background-color: #991E1E; -fx-font-size: 2em; -fx-text-fill: #ffffff; -fx-border-color: #ff0000; -fx-border-width: 2px; ");
        startBtn.setPrefWidth(200);
        startBtn.setPrefHeight(45);
        startBtn.setLayoutX(545 - startBtn.getWidth() - 90);
        startBtn.setLayoutY(225);
        root.getChildren().add(startBtn);

        Button selectMapBtn = new Button("Select Map");
        selectMapBtn.setPrefWidth(200);
        selectMapBtn.setLayoutX(545 - selectMapBtn.getWidth() - 90);
        selectMapBtn.setLayoutY(290);
        root.getChildren().add(selectMapBtn);

        Label credits = new Label("Created By: Anjola Adeboye, Harry Chen, Mei Hou, Josh Kim and Andre Staffa");
        //credits.setFont(Font.loadFont("file:resources/fonts/RiseofKingdom.ttf", 15));
        credits.setFont(Font.loadFont(MenuGUI.class.getResourceAsStream("/resources/fonts/RiseofKingdom.ttf"), 15));
        credits.setTextFill(Color.WHITE);
        credits.setLayoutX(12);
        credits.setLayoutY(575);
        root.getChildren().add(credits);

        startBtn.setOnAction(e -> {
            stage.setScene(gameScene);
        });

        selectMapBtn.setOnAction(e -> {
            System.out.println("Select Map Button Selected");
        });

        BackgroundImage backdrop = new BackgroundImage(new Image(
                MenuGUI.class.getResourceAsStream("/resources/images/tanks.jpg"),
                1080,680,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(backdrop));
    }

}