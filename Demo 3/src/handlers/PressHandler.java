package handlers;

import logic.*;
import visuals.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PressHandler extends Game implements EventHandler<KeyEvent>{

    private GUI test = new GUI();
    private Bullet b = new Bullet();
    
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
                    setP1Shooting(true);
                    getTanks().get(0).setShooting(true);
                }
				break;
        }
    }

    public void twoPlayer(KeyEvent key){
        onePlayer(key);

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
                setP2Shooting(true);
                getTanks().get(1).setShooting(true);
            }
            break;
        }
    }

    public void threePlayer(KeyEvent key){
        twoPlayer(key);

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
            /*if(getTanks().get(2).getShooting() == false){
                shoot(getTanks().get(2)); //change to p3Shooting
                getTanks().get(2).setShooting(true);
            }*/
            break;
        }
    }

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