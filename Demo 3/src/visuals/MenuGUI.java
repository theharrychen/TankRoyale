package visuals;

import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
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

public class MenuGUI {

    private Pane root = new Pane();
    private Game game;

    public void start(Stage stage){
        game = new Game();
        game.start();

        Scene gameScene = new Scene(game.getVisual().getRoot(), MainGUI.WIDTH, MainGUI.HEIGHT);
        gameScene.setOnKeyPressed(new PressHandler());
        gameScene.setOnKeyReleased(new ReleaseHandler());

        createMenu(stage, gameScene);
        Scene menuScene = new Scene(root, MainGUI.WIDTH, MainGUI.HEIGHT);
        stage.setScene(menuScene);
    }

    private void createMenu(Stage stage, Scene gameScene){
        root.getChildren().add(createTitle());
        root.getChildren().add(createStartBtn(stage, gameScene));
        root.getChildren().add(createMapBtn());
        root.getChildren().add(createCredits());
        root.setBackground(createBackground());

    }

    private Label createTitle() {
        Label titleLbl = new Label("Tank Royale");
        titleLbl.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/ToetheLineless.ttf"), 50));
        titleLbl.setPrefWidth(400);
        titleLbl.setLayoutX(545 - titleLbl.getWidth() - 157);
        titleLbl.setLayoutY(125);
        return titleLbl;
    }

    private Button createStartBtn(Stage stage, Scene gameScene) {
        Button startBtn = new Button("Start");
        startBtn.setStyle("-fx-background-color: #991E1E; -fx-font-size: 2em; -fx-text-fill: #ffffff;" +
                            " -fx-border-color: #ff0000; -fx-border-width: 2px; ");
        startBtn.setPrefWidth(200);
        startBtn.setPrefHeight(45);
        startBtn.setLayoutX(545 - startBtn.getWidth() - 90);
        startBtn.setLayoutY(225);

        DropShadow shadow = new DropShadow();
        startBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startBtn.setEffect(shadow));
        //Removing the shadow when the mouse cursor is off
        startBtn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startBtn.setEffect(null));
        startBtn.setOnAction(e -> stage.setScene(gameScene));

        return startBtn;
    }

    private Button createMapBtn() {
        Button selectMapBtn = new Button("Select Map");
        selectMapBtn.setPrefWidth(200);
        selectMapBtn.setLayoutX(545 - selectMapBtn.getWidth() - 90);
        selectMapBtn.setLayoutY(290);

        selectMapBtn.setOnAction(e -> {
            System.out.println("Select Map Button Selected");
        });
        return selectMapBtn;
    }

    private Label createCredits() {
        Label credits = new Label("Created By: Anjola Adeboye, Harry Chen, Mei Hou, Josh Kim and Andre Staffa");
        credits.setFont(Font.loadFont(MenuGUI.class.getResourceAsStream("/resources/fonts/RiseofKingdom.ttf"), 15));
        credits.setTextFill(Color.WHITE);
        credits.setLayoutX(12);
        credits.setLayoutY(575);
        return credits;
    }

    private Background createBackground() {
        BackgroundImage backdrop = new BackgroundImage(new Image(
                MenuGUI.class.getResourceAsStream("/resources/images/tanks.jpg"),
                1080,680,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backdrop);
    }

}