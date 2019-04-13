package visuals;

/**
 * This class is used to display the GUI visual component
 *
 * @author Group 7
 * @version 3.0
 * @since 2019-02-19
 */

import logic.*;
import drivers.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;

import static visuals.MenuGUI.createMapBtnBox;
import static visuals.MenuGUI.createWinBtnBox;
import static visuals.MenuGUI.createModeBox;

public class GameGUI {

    private Pane root = new Pane();
    private Button restartBtn = new Button("Restart");
    private static int players = 0;

    /**
     * @return restartBtn
     */
    public Button getRestartBtn() {
        return this.restartBtn;
    }

    /**
     * Sets the prefered size of the Pane layout
     */
    public GameGUI() {
        root.setPrefSize(MainGUI.WIDTH, MainGUI.HEIGHT);
    }

    /**
     * @return root layout
     */
    public Pane getRoot() {
        return root;
    }

    /**
     * Creates the display of the player tally
     *
     * @param int p1score, int p2score
     */
    public void displayTally(int p1score, int p2score) {
        Label p1tally = new Label(Integer.toString(p1score));
        Label p2tally = new Label(Integer.toString(p2score));
        Label p1 = new Label("Player One");
        Label p2 = new Label("Player Two");
        p1tally.setTranslateX(MainGUI.WIDTH / 2.0 - 150);
        p2tally.setTranslateX(MainGUI.WIDTH / 2.0 + 150);
        p1.setTranslateX(MainGUI.WIDTH / 2.0 - 170);
        p2.setTranslateX(MainGUI.WIDTH / 2.0 + 130);
        p1tally.setTranslateY(MainGUI.HEIGHT - 75);
        p2tally.setTranslateY(MainGUI.HEIGHT - 75);
        p1.setTranslateY(MainGUI.HEIGHT - 35);
        p2.setTranslateY(MainGUI.HEIGHT - 35);
        p1tally.setFont(new Font(30));
        p2tally.setFont(new Font(30));
        root.getChildren().addAll(p1tally, p2tally, p1, p2);
    }

    /**
     * Clears the display
     *
     * @param int p1score, int p2score
     */
    public void clear() {
        getRoot().getChildren().clear();
        setPlayers(0);
    }

    /**
     * Sets the number of players
     *
     * @param int num
     */
    public void setPlayers(int num) {
        this.players = num;
    }

    /**
     * Adds a visual Game Entity
     *
     * @param GameEntity entity
     */
    public void addVisualGameEntity(GameEntity entity) {
        getRoot().getChildren().add(entity.getView());
    }

    /**
     * Adds the image of the tank
     * player one is black, player two is red.
     *
     * @param Tank tank
     */
    public void addPlayerimage(Tank tank) {
        try {
            Image img = null;
            switch (this.players) {
                case 0:
                    img = new Image("/resources/images/blacktank.png");
                    players++;
                    break;
                case 1:
                    img = new Image("/resources/images/redtank.png");
                    players++;
                    break;
            }
            Circle temptank = (Circle) tank.getView();
            temptank.setFill(new ImagePattern(img));
        } catch (Exception e) {
            System.out.println("Was unable to load in tank image");
        }
    }

    /**
     * Creates the restart button
     */
    public void createRestartButton() {
        //Create restart button
        restartBtn.setTranslateX(MainGUI.WIDTH / 2.0 - 45);
        restartBtn.setTranslateY((MainGUI.HEIGHT - 100) / 2.0);
    }

    /**
     * Announces winner of the match
     *
     * @Param int winner
     */
    public void announceWinner(int winner) {
        Label winText = new Label("");
        winText.setFont(new Font("Arial", 40));
        switch (winner) {
            case 0:
                winText.setText("IT WAS A TIE");
                break;

            case 1:
            case 2:
                winText.setText("PLAYER " + winner + " WON!");
                break;
        }
        winText.setTranslateX(MainGUI.WIDTH / 2.0 - 150);
        winText.setTranslateY((MainGUI.HEIGHT - 100) / 2.0 - 50);
        root.getChildren().addAll(restartBtn, winText);
        root.getChildren().add(createMapBtnBox());
        root.getChildren().add(createWinBtnBox());

        root.getChildren().add(createModeBox());
    }

    /**
     * Removes the visual representation of a bullet from the screen
     *
     * @param Bullet bullet
     */
    public void removeBullet(Bullet bullet) {
        root.getChildren().removeAll(bullet.getView());
    }

    /**
     * Removes the visual representation of a tank from the screen
     *
     * @param Bullet bullet
     */
    public void removeTank(Tank tank) {
        root.getChildren().removeAll(tank.getView());
    }

    /**
     * Removes the visual representations of tanks and bullets from the screen
     *
     * @param Bullet bullet
     */
    public void removeAll(Bullet bullet, Tank tank) {
        root.getChildren().removeAll(bullet.getView(), tank.getView());
    }
}