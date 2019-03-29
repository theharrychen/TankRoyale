package visuals;
/**
 * This class is used to display the Main Menu Screen
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-21
 */


import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import logic.*;
import drivers.*;
import handlers.*;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuGUI {

    private Pane root = new Pane();
    private Game game;

    /**
     * Starts up the Menu and initializes the Game
     * @param stage
     */
    public void start(Stage stage){
        game = new Game();

        Scene gameScene = new Scene(game.getVisual().getRoot(), MainGUI.WIDTH, MainGUI.HEIGHT);
        gameScene.setOnKeyPressed(new PressHandler());
        gameScene.setOnKeyReleased(new ReleaseHandler());

        createMenu(stage, gameScene);
        Scene menuScene = new Scene(root, MainGUI.WIDTH, MainGUI.HEIGHT);
        stage.setScene(menuScene);
    }

    /**
     *
     * @param stage
     * @param gameScene
     */
    private void createMenu(Stage stage, Scene gameScene){
        root.getChildren().add(createTitle());
        root.getChildren().add(createStartBtn(stage, gameScene));
        root.getChildren().add(createMapBtnBox(game));
        root.getChildren().add(createCredits());
        root.setBackground(createBackground());
    }

    /**
     * Creates the Game Logo Title with a font
     * @return Label
     */
    private Label createTitle() {
        Label titleLbl = new Label("Tank Royale");
        titleLbl.setFont(Font.loadFont(getClass().getResourceAsStream("/resources/fonts/ToetheLineless.ttf"), 50));
        titleLbl.setPrefWidth(400);
        titleLbl.setLayoutX(545 - titleLbl.getWidth() - 157);
        titleLbl.setLayoutY(125);
        return titleLbl;
    }

    /**
     * Creates the start game button that changes the scene to the gameScene when clicked
     * @param stage
     * @param gameScene
     * @return Button
     */
    private Button createStartBtn(Stage stage, Scene gameScene) {
        Button startBtn = new Button("Start");
        startBtn.setStyle("-fx-background-color: #991E1E; -fx-font-size: 2em; -fx-text-fill: #ffffff;" +
                            " -fx-border-color: #ff0000; -fx-border-width: 2px; ");
        startBtn.setPrefWidth(200);
        startBtn.setPrefHeight(45);
        startBtn.setLayoutX(MainGUI.WIDTH / 2.0 - startBtn.getPrefWidth()/2.0);
        startBtn.setLayoutY(225);

        DropShadow shadow = new DropShadow();
        startBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> startBtn.setEffect(shadow));
        //Removing the shadow when the mouse cursor is off
        startBtn.addEventHandler(MouseEvent.MOUSE_EXITED, e -> startBtn.setEffect(null));
        startBtn.setOnAction(e -> {
            stage.setScene(gameScene);
            game.start();
        });

        return startBtn;
    }

    /**
     * Creates a toggleable Map button to choose the map for the game
     * @param name
     * @param mapFilePath
     * @param game
     * @return ToggleButton
     */
    public static ToggleButton createMapBtn(String name, String mapFilePath, Game game) {
        ToggleButton selectMapBtn = new ToggleButton(name);
        selectMapBtn.setPrefWidth(100);

        selectMapBtn.setOnAction(e -> {
            game.setMapFilePath(mapFilePath);
        });
        return selectMapBtn;
    }

    /**
     * Creates a Horizontal container of map buttons
     * @param game
     * @return HBox
     */
    public static HBox createMapBtnBox(Game game){
        int mapCount = 3;
        ToggleButton mapBtn1 = createMapBtn("Maze", "/resources/gui/maze.txt", game);
        ToggleButton mapBtn2 = createMapBtn("Empty", "/resources/gui/empty.txt", game);
        ToggleButton mapBtn3 = createMapBtn("Test", "/resources/gui/heart.txt", game);

        //Only one map can be selected, hence the use of a toggling system
        final ToggleGroup group = new ToggleGroup();
        mapBtn1.setToggleGroup(group);
        mapBtn2.setToggleGroup(group);
        mapBtn3.setToggleGroup(group);
        mapBtn1.setSelected(true);

        HBox hbox = new HBox(mapBtn1, mapBtn2, mapBtn3);
        hbox.setLayoutX(MainGUI.WIDTH / 2.0 - mapBtn1.getPrefWidth()*mapCount / 2.0);
        hbox.setLayoutY(MainGUI.HEIGHT/2.0 + 5);
        return hbox;
    }

    /**
     *  Creates the credits label
     * @return Label
     */
    private Label createCredits() {
        Label credits = new Label("Created By: Anjola Adeboye, Harry Chen, Mei Hou, Josh Kim and Andre Staffa");
        credits.setFont(Font.loadFont(MenuGUI.class.getResourceAsStream("/resources/fonts/RiseofKingdom.ttf"), 15));
        credits.setTextFill(Color.WHITE);
        credits.setLayoutX(12);
        credits.setLayoutY(575);
        return credits;
    }

    /**
     * Loads in and creates the Main Menu Screen Background
     * @return Background
     */
    private Background createBackground() {
        BackgroundImage backdrop = new BackgroundImage(new Image(
                MenuGUI.class.getResourceAsStream("/resources/images/tanks.jpg"),
                1080,680,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backdrop);
    }

}