package handlers;


/**
 * This class handles key pressed events
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-20
 */

import logic.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PressHandler extends Game implements EventHandler<KeyEvent>{
    
    /**
     * Player controls for one player
     * @param key KeyEvent key
     */
    public void onePlayer(KeyEvent key){
        switch (key.getCode()) {
			case UP:
				getTanks().get(0).setUp(true);
				break;
			case DOWN:
				getTanks().get(0).setDown(true);
				break;
			case LEFT:
				getTanks().get(0).setLeft(true);
				break;
			case RIGHT:
				getTanks().get(0).setRight(true);
				break;
            case ENTER:
				if(getTanks().get(0).getShooting() == false){
					shoot(getTanks().get(0));
                    getTanks().get(0).setShooting(true);
                }
				break;
        }
    }

    /**
     * Player controls for two players
     * @param key KeyEvent key
     */
    public void twoPlayer(KeyEvent key){
        onePlayer(key); //For player 1's controls

        switch (key.getCode()) {
            case W:
                getTanks().get(1).setUp(true);
                break;
            case S:
                getTanks().get(1).setDown(true);
                break;
            case A:
                getTanks().get(1).setLeft(true);
                break;
            case D:
                getTanks().get(1).setRight(true);
                break;
            case Q:
            if(getTanks().get(1).getShooting() == false){
				shoot(getTanks().get(1));
                getTanks().get(1).setShooting(true);
            }
            break;
        }
    }

    /**
     * Player controls for three players
     * @param key KeyEvent key
     */
    public void threePlayer(KeyEvent key){
        twoPlayer(key); //For player 1 and 2's controls

        switch (key.getCode()) {
            case Y:
                getTanks().get(2).setUp(true);
                break;
            case H:
                getTanks().get(2).setDown(true);
                break;
            case G:
                getTanks().get(2).setLeft(true);
                break;
            case J:
                getTanks().get(2).setRight(true);
                break;
            case SPACE:
                //Future: shoot(getTanks().get(1));
                //getTanks().get(1).setShooting(true);
            break;
        }
    }

    /**
     * Handles player controls based on number of players
     */
    public void handle(KeyEvent key){
        int players = getPlayerCount();
        if(players == 1)  
            onePlayer(key);
        else if(players == 2)
            twoPlayer(key);
        else if(players == 3)
            threePlayer(key);  
    }
}