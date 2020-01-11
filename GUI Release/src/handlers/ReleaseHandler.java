package handlers;


/**
 * This class handles key released events
 *
 * @author Group 7
 * @version 1.0
 * @since 2019-03-20
 */

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Game;

public class ReleaseHandler extends Game implements EventHandler<KeyEvent> {

    /**
     * Player controls for one player
     *
     * @param key KeyEvent key
     */
    public void onePlayer(KeyCode key) {
        switch (key) {
            case W:
                getTanks().get(0).setUp(false);
                break;
            case S:
                getTanks().get(0).setDown(false);
                break;
            case A:
                getTanks().get(0).setLeft(false);
                break;
            case D:
                getTanks().get(0).setRight(false);
                break;
            case Q:
                getTanks().get(0).setShooting(false);
                break;
        }
    }

    /**
     * Player controls for two players
     *
     * @param key KeyEvent key
     */
    public void twoPlayer(KeyCode key) {
        onePlayer(key);
        if (getTanks().size() >= 2)
            switch (key) {
                case UP:
                    getTanks().get(1).setUp(false);
                    break;
                case DOWN:
                    getTanks().get(1).setDown(false);
                    break;
                case LEFT:
                    getTanks().get(1).setLeft(false);
                    break;
                case RIGHT:
                    getTanks().get(1).setRight(false);
                    break;
                case ENTER:
                    getTanks().get(1).setShooting(false);
                    break;
            }
    }

    /**
     * Player controls for three players
     *
     * @param key KeyEvent key
     */
    public void threePlayer(KeyCode key) {
        twoPlayer(key);

        switch (key) {
            case Y:
                getTanks().get(2).setUp(false);
                break;
            case H:
                getTanks().get(2).setDown(false);
                break;
            case G:
                getTanks().get(2).setLeft(false);
                break;
            case J:
                getTanks().get(2).setRight(false);
                break;
            case SPACE:
                //getTanks().get(2).setShooting(false);
                break;
        }
    }

    /**
     * Handles player controls based on number of players
     */
    public void handle(KeyEvent key) {
        int players = getPlayerCount();
        if (players == 1)
            onePlayer(key.getCode());
        else if (players == 2)
            twoPlayer(key.getCode());
        else if (players == 3)
            threePlayer(key.getCode());
    }
}