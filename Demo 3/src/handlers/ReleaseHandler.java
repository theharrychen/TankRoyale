package handlers;

import logic.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ReleaseHandler extends Game implements EventHandler<KeyEvent>{

    public void onePlayer(KeyCode key){
        switch (key) {
			case UP:
				getTanks().get(0).setUp(false);
				break;
			case DOWN:
				getTanks().get(0).setDown(false);
				break;
			case LEFT:
				getTanks().get(0).setLeft(false);
				break;
			case RIGHT:
				getTanks().get(0).setRight(false);
				break;
			case ENTER:
                getTanks().get(0).setShooting(false);
				break;
        }
    }

    public void twoPlayer(KeyCode key){
        onePlayer(key);

        switch (key) {
            case W:
                getTanks().get(1).setUp(false);
                break;
            case S:
                getTanks().get(1).setDown(false);
                break;
            case A:
                getTanks().get(1).setLeft(false);
                break;
            case D:
                getTanks().get(1).setRight(false);
                break;
            case Q:
                getTanks().get(1).setShooting(false);
                break;
        }
    }

    public void threePlayer(KeyCode key){
        twoPlayer(key);

        switch (key){
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

    public void handle(KeyEvent key){
        int players = getPlayerCount();
        if(players == 1)  
            onePlayer(key.getCode());
        else if(players == 2)
            twoPlayer(key.getCode());
        else if(players == 3)
            threePlayer(key.getCode());   
    }
}