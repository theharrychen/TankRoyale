package codes;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Menu extends Application {

    public static final double WIDTH = 1080.0, HEIGHT = 608.0; //16:9 Window Aspect Ratio
    private Game game = new Game();
    private static String maps = "/maze.txt";

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        Pane root = new Pane();

        Image tank = new Image("tanks.jpg");
        ImageView mv = new ImageView();
        mv.setImage(tank);
        root.getChildren().add(mv);

        Label titleLbl = new Label("Tank Royale");
        titleLbl.setFont(Font.loadFont("file:resources/fonts/ToetheLineless.ttf", 50));
        titleLbl.setPrefWidth(400);
        titleLbl.setLayoutX(400 - titleLbl.getWidth() - 157);
        titleLbl.setLayoutY(100);
        root.getChildren().add(titleLbl);

        Button startBtn = new Button("Start");
        startBtn.setStyle("-fx-background-color: #991E1E; -fx-font-size: 2em; -fx-text-fill: #ffffff; -fx-border-color: #ff0000; -fx-border-width: 2px; ");
        startBtn.setPrefWidth(200);
        startBtn.setPrefHeight(45);
        startBtn.setLayoutX(400 - startBtn.getWidth() - 90);
        startBtn.setLayoutY(200);
        root.getChildren().add(startBtn);

        Button selectMapBtn = new Button("Select Map");
        selectMapBtn.setPrefWidth(200);
        selectMapBtn.setLayoutX(400 - selectMapBtn.getWidth() - 90);
        selectMapBtn.setLayoutY(265);
        root.getChildren().add(selectMapBtn);

        Label credits = new Label("Written By: Anjola Adeboye, Harry Chen, Mei Hou, Josh Kim and Andre Staffa");
        credits.setFont(Font.loadFont("file:resources/fonts/RiseofKingdom.ttf", 15));
        credits.setTextFill(Color.WHITE);
        credits.setLayoutX(12);
        credits.setLayoutY(575);
        root.getChildren().add(credits);

        Button mazeBtn = new Button("Maze");
        mazeBtn.setPrefWidth(200);
        mazeBtn.setLayoutX(400 - mazeBtn.getWidth() - 90);
        mazeBtn.setLayoutY(195);
        root.getChildren().add(mazeBtn);

        Button temp1MapBtn = new Button("Temp Map 1");
        temp1MapBtn.setPrefWidth(200);
        temp1MapBtn.setLayoutX(400 - temp1MapBtn.getWidth() - 90);
        temp1MapBtn.setLayoutY(230);
        root.getChildren().add(temp1MapBtn);

        Button temp2MapBtn = new Button("Temp Map 2");
        temp2MapBtn.setPrefWidth(200);
        temp2MapBtn.setLayoutX(400 - temp2MapBtn.getWidth() - 90);
        temp2MapBtn.setLayoutY(265);
        root.getChildren().add(temp2MapBtn);

        mazeBtn.setVisible(false);
        temp1MapBtn.setVisible(false);
        temp2MapBtn.setVisible(false);


        startBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("Start Button Selected");
                Scene gameScene = new Scene(game.getRoot(), WIDTH, HEIGHT);
                startGame(new Stage(), gameScene);
            }
        });


        selectMapBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                startBtn.setVisible(false);
                selectMapBtn.setVisible(false);
                mazeBtn.setVisible(true);
                temp1MapBtn.setVisible(true);
                temp2MapBtn.setVisible(true);

            }
        });

        mazeBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                mazeBtn.setVisible(false);
                temp1MapBtn.setVisible(false);
                temp2MapBtn.setVisible(false);
                startBtn.setVisible(true);
                selectMapBtn.setVisible(true);

                maps = "/maze.txt";

            }
        });

        temp1MapBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                mazeBtn.setVisible(false);
                temp1MapBtn.setVisible(false);
                temp2MapBtn.setVisible(false);
                startBtn.setVisible(true);
                selectMapBtn.setVisible(true);

                // TODO: Change Name
                maps = "/temp1.txt";
            }
        });

        temp2MapBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                mazeBtn.setVisible(false);
                temp1MapBtn.setVisible(false);
                temp2MapBtn.setVisible(false);
                startBtn.setVisible(true);
                selectMapBtn.setVisible(true);

                // TODO: Change Name
                maps = "/temp2.txt";
            }
        });

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Tank Royale");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void startGame(Stage stage, Scene scene)
    {
        stage.setTitle("Tank Royale");
        stage.setResizable(false);

        game.start();

        stage.setScene(scene);
        scene.setOnKeyPressed(game.new PressHandler());
        scene.setOnKeyReleased(game.new ReleaseHandler());

        stage.show();
    }

    public static String getMaps()
    {
        return maps;
    }

}
