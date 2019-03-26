//package sample;

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

public class Main extends Application {

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
        titleLbl.setFont(Font.loadFont("resources/fonts/ToetheLineless.ttf", 50));
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


        startBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("Start Button Selected");
            }
        });


        selectMapBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                System.out.println("Select Map Button Selected");
            }
        });


        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Tank Royale");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
