package handlers;

import logic.*;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PressHandler implements EventHandler<KeyEvent>{

    Game game = new Game();
    
    public void onePlayer(KeyCode key){
        switch (key) {
			case UP:
				game.getTanks().get(0).setUp(true);
				break;
			case DOWN:
				game.getTanks().get(0).setDown(true);
				break;
			case LEFT:
				game.getTanks().get(0).setLeft(true);
				break;
			case RIGHT:
				game.getTanks().get(0).setRight(true);
				break;
			case ENTER:
				if(game.getTanks().get(0).getShooting() == false){
					game.shoot(game.getTanks().get(0));
					game.getTanks().get(0).setShooting(true);
				}
				break;
        }
    }

    public void twoPlayer(KeyCode key){
        onePlayer(key);

        switch (key) {
            case W:
                game.getTanks().get(1).setUp(true);
                break;
            case S:
                game.getTanks().get(1).setDown(true);
                break;
            case A:
                game.getTanks().get(1).setLeft(true);
                break;
            case D:
                game.getTanks().get(1).setRight(true);
                break;
            case Q:
            if(game.getTanks().get(1).getShooting() == false){
                game.shoot(game.getTanks().get(1));
                game.getTanks().get(1).setShooting(true);
            }
            break;
        }
    }

    public void threePlayer(KeyCode key){
        twoPlayer(key);

        switch (key) {
            case Y:
                game.getTanks().get(2).setUp(true);
                break;
            case H:
                game.getTanks().get(2).setDown(true);
                break;
            case G:
                game.getTanks().get(2).setLeft(true);
                break;
            case J:
                game.getTanks().get(2).setRight(true);
                break;
            case SPACE:
            if(game.getTanks().get(2).getShooting() == false){
                game.shoot(game.getTanks().get(2));
                game.getTanks().get(2).setShooting(true);
            }
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