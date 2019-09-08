package visuals;
/**
 * This class is used to display the Main Menu Screen
 *
 * @author Group 7
 * @version 3.0
 * @since 2019-03-21
 */


import drivers.MainGUI;
import handlers.PressHandler;
import handlers.ReleaseHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Game;

/**
 *
 */
public class MenuGUI {

    private Pane root = new Pane();
    private Game game;
    private static int endRound = 4;
    private static String mapFilePath = "/resources/gui/maze.txt";
    private static boolean sickoMode = false;
    private static boolean coloured = false;

    /**
     * Starts up the Menu and initializes the Game
     *
     * @param stage
     */
    public void start(Stage stage) {
        game = new Game();

        Scene gameScene = new Scene(game.getVisual().getRoot(), MainGUI.WIDTH, MainGUI.HEIGHT);
        gameScene.setOnKeyPressed(new PressHandler());
        gameScene.setOnKeyReleased(new ReleaseHandler());

        createMenu(stage, gameScene);
        Scene menuScene = new Scene(root, MainGUI.WIDTH, MainGUI.HEIGHT);
        stage.setScene(menuScene);
        
    }

    /**
     * @param stage
     * @param gameScene
     */
    private void createMenu(Stage stage, Scene gameScene) {
        root.getChildren().add(createTitle());
        root.getChildren().add(createStartBtn(stage, gameScene));
        root.getChildren().add(createMapBtnBox());
        root.getChildren().add(createWinBtnBox());
        root.getChildren().add(createCredits());
        root.setBackground(createBackground("/resources/images/tanks.jpg"));

        root.getChildren().add(createModeBox());

        root.getChildren().add(createControlsBtn(stage));
    }

    /**
     * Creates the Game Logo Title with a font
     *
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
     *
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
        startBtn.setLayoutX(MainGUI.WIDTH / 2.0 - startBtn.getPrefWidth() / 2.0);
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
     *
     * @param name
     * @param mapFilePath
     * @param game
     * @return ToggleButton
     */
    public static ToggleButton createMapBtn(String name, String mapFilePath) {
        ToggleButton selectMapBtn = new ToggleButton(name);
        selectMapBtn.setPrefWidth(100);

        selectMapBtn.setOnAction(e -> {
            setMapFilePath(mapFilePath);
            setColoured(false);
        });
        return selectMapBtn;
    }

    /**
     * Creates a Horizontal container of map buttons
     *
     * @param game
     * @return HBox
     */
    public static HBox createMapBtnBox() {
        int mapCount = 4;
        ToggleButton mapBtn1 = createMapBtn("Maze", "/resources/gui/maze.txt");
        ToggleButton mapBtn2 = createMapBtn("Empty", "/resources/gui/empty.txt");
        ToggleButton mapBtn3 = createMapBtn("Oddity", "/resources/gui/oddity.txt");
        ToggleButton mapBtn4 = createColourMapBtn();

        //Only one map can be selected, hence the use of a toggling system
        final ToggleGroup group = new ToggleGroup();
        mapBtn1.setToggleGroup(group);
        mapBtn2.setToggleGroup(group);
        mapBtn3.setToggleGroup(group);
        mapBtn4.setToggleGroup(group);
        //Default
        if (isColoured()){
            setColoured(true);
            mapBtn4.setSelected(true);
        }
        else {
            setColoured(false);
            mapBtn1.setSelected(true);
        }
        setMapFilePath("/resources/gui/maze.txt");

        HBox hbox = new HBox(mapBtn1, mapBtn2, mapBtn3, mapBtn4);
        hbox.setLayoutX(MainGUI.WIDTH / 2.0 - mapBtn1.getPrefWidth() * mapCount / 2.0);
        hbox.setLayoutY(MainGUI.HEIGHT / 2.0 + 5);
        return hbox;
    }

    /**
     * @param mapFilePath
     */
    public static void setMapFilePath(String mapFilePath) {
        MenuGUI.mapFilePath = mapFilePath;
    }

    /**
     * @return mapFilePath
     */
    public static String getMapFilePath() {
        return mapFilePath;
    }

    /**
     * Creates a button for the user to decide what the score should go up to
     *
     * @param rounds
     * @return Button
     */
    public static ToggleButton createRoundWinBtn(int rounds) {
        ToggleButton roundWinBtn = new ToggleButton("Rounds: " + rounds);
        roundWinBtn.setPrefWidth(100);

        roundWinBtn.setOnAction(e -> {
            endRound = rounds - 1;
        });
        return roundWinBtn;
    }

    /**
     * Creates a Horizontal container of round win buttons
     *
     * @return HBox
     */
    public static HBox createWinBtnBox() {
        int numWinBtns = 3;
        ToggleButton winBtn1 = createRoundWinBtn(5);
        ToggleButton winBtn2 = createRoundWinBtn(10);
        ToggleButton winBtn3 = createRoundWinBtn( 20);

        //Only one map can be selected, hence the use of a toggling system
        final ToggleGroup group = new ToggleGroup();
        winBtn1.setToggleGroup(group);
        winBtn2.setToggleGroup(group);
        winBtn3.setToggleGroup(group);
        winBtn1.setSelected(true);
        endRound = 4; // default

        HBox hbox = new HBox(winBtn1, winBtn2, winBtn3);
        hbox.setLayoutX(MainGUI.WIDTH / 2.0 - winBtn1.getPrefWidth() * numWinBtns / 2.0);
        hbox.setLayoutY(MainGUI.HEIGHT / 2.0 + 40);
        return hbox;
    }

    /**
     * @return endRound
     */
    public static int getEndRound()
    {
        return endRound;
    }


    /**
     * Creates the credits label
     *
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
     *
     * @return Background
     */
    public static Background createBackground(String filePath) {
        BackgroundImage backdrop = new BackgroundImage(new Image(
                MenuGUI.class.getResourceAsStream(filePath),
                1080, 680, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backdrop);
    }

    /**
     * @return sickoMode
     */
    public static boolean isSickoMode() {
        return sickoMode;
    }

    /**
     * @param sickoMode
     */
    public static void setSickoMode(boolean sickoMode) {
        MenuGUI.sickoMode = sickoMode;
    }

    /**
     * @return HBox for normal and sicko mode buttons
     */
    public static HBox createModeBox() {
        ToggleButton normalButton = new ToggleButton("Normal Mode");
        normalButton.setPrefWidth(150);
        normalButton.setOnAction(e -> {
            setSickoMode(false);
        });

        ToggleButton sickoButton = new ToggleButton("Sicko Mode");
        sickoButton.setPrefWidth(150);
        sickoButton.setOnAction(e -> {
            setSickoMode(true);
        });

        if (isSickoMode()) {
            sickoButton.setSelected(true);
        }
        else {
            normalButton.setSelected(true);
        }

        ToggleGroup group = new ToggleGroup();
        normalButton.setToggleGroup(group);
        sickoButton.setToggleGroup(group);

        HBox modeBox = new HBox(normalButton, sickoButton);
        modeBox.setLayoutX(800);
        modeBox.setLayoutY(100);
        return modeBox;
    }

    public static ToggleButton createColourMapBtn() {
        ToggleButton colourMapBtn = new ToggleButton("Colour");
        colourMapBtn.setPrefWidth(100);

        colourMapBtn.setOnAction(e -> {
            setColoured(true);
        });
        return colourMapBtn;
    }

    /**
     * @return isColored
     */
    public static boolean isColoured() {
        return coloured;
    }

    /**
     * @param coloured
     */
    public static void setColoured(boolean coloured) {
        MenuGUI.coloured = coloured;
    }

    /**
     * Creates a button that displays the player controls
     *
     * @param stage
     * @return
     */
    public static Button createControlsBtn(Stage stage) {
        Button btn = new Button();
        btn.setText("CONTROLS");
        btn.setOnAction(event -> {
                    final Stage popUp = new Stage();
                    popUp.initModality(Modality.APPLICATION_MODAL);
                    popUp.initOwner(stage);
                    VBox popUpBox = new VBox();
                    ImageView controlImg = new ImageView(new Image("/resources/images/keyboard-layout.png"));
                    popUpBox.getChildren().add(controlImg);
                    Scene dialogScene = new Scene(popUpBox, 1080, 608);
                    popUp.setScene(dialogScene);
                    popUp.show();
                }
        );
        return btn;
    }
}