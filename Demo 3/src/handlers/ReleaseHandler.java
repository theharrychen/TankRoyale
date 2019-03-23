package handlers;

import logic.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ReleaseHandler implements EventHandler<KeyEvent>{

    Game game = new Game();

    public void onePlayer(KeyCode key){
        switch (key) {
			case UP:
				game.getTanks().get(0).setUp(false);
				break;
			case DOWN:
				game.getTanks().get(0).setDown(false);
				break;
			case LEFT:
				game.getTanks().get(0).setLeft(false);
				break;
			case RIGHT:
				game.getTanks().get(0).setRight(false);
				break;
			case ENTER:
				game.getTanks().get(0).setShooting(false);
				break;
        }
    }

    public void twoPlayer(KeyCode key){
        onePlayer(key);

        switch (key) {
            case W:
                game.getTanks().get(1).setUp(false);
                break;
            case S:
                game.getTanks().get(1).setDown(false);
                break;
            case A:
                game.getTanks().get(1).setLeft(false);
                break;
            case D:
                game.getTanks().get(1).setRight(false);
                break;
            case Q:
                game.getTanks().get(1).setShooting(false);
                break;
        }
    }

    public void threePlayer(KeyCode key){
        twoPlayer(key);

        switch (key){
            case Y:
                game.getTanks().get(2).setUp(false);
                break;
            case H:
                game.getTanks().get(2).setDown(false);
                break;
            case G:
                game.getTanks().get(2).setLeft(false);
                break;
            case J:
                game.getTanks().get(2).setRight(false);
                break;
            case SPACE:
                game.getTanks().get(2).setShooting(false);
                break;
        }
    }

    public void handle(KeyEvent key){
        int players = game.getPlayerCount();
        if(players == 1)  
            onePlayer(key.getCode());
        else if(players == 2)
            twoPlayer(key.getCode());
        else if(players == 3)
            threePlayer(key.getCode());   
    }
}