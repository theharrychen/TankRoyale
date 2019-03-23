package visuals;

import logic.*;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import javafx.scene.text.Font;
	
public class GUI{
	
	private Pane root = new Pane();
	private Button restartBtn = new Button("Restart");
	
	public Button getRestartBtn(){
		return this.restartBtn;
	}
	
	/**
	 *Sets the prefered size of the Pane layout
	 */
    public GUI(){
        root.setPrefSize(Main.WIDTH, Main.HEIGHT);
    }
	
	 /**
	 *@return root layout
	 */
    public Pane getRoot(){
        return root;
    }
	
	//
	public void displayTally(int p1score, int p2score){
		Label p1tally = new Label(Integer.toString(p1score));
		Label p2tally = new Label(Integer.toString(p2score));
		Label p1 = new Label("Player One");
		Label p2 = new Label("Player Two");
        p1tally.setTranslateX(Main.WIDTH /2.0 - 150);
		p2tally.setTranslateX(Main.WIDTH /2.0+ 150);
		p1.setTranslateX(Main.WIDTH /2.0 - 170);
		p2.setTranslateX(Main.WIDTH /2.0 + 130);
        p1tally.setTranslateY(Main.HEIGHT-75);
		p2tally.setTranslateY(Main.HEIGHT-75);
		p1.setTranslateY(Main.HEIGHT-35);
		p2.setTranslateY(Main.HEIGHT-35);
		p1tally.setFont(new Font(30));
		p2tally.setFont(new Font(30));
        root.getChildren().addAll(p1tally, p2tally,p1,p2);
	}
	
	//not encapsulated
	public void clear(){
		getRoot().getChildren().clear();
	}
	
	public void addVisualGameEntity(GameEntity entity){
		getRoot().getChildren().add(entity.getView());
	}
	
	public void createRestartButton(){
		//Create restart button
        restartBtn.setTranslateX(Main.WIDTH / 2.0 );
        restartBtn.setTranslateY((Main.HEIGHT-100) / 2.0);
	}
		
	// Announce winner of match
	public void announceWinner(int winner){
		Label winText = new Label("");
		switch(winner){
			case 0:
			winText.setText("IT WAS A TIE");
			break;
			
			case 1: case 2:
			winText.setText("PLAYER " + winner + " WON!");
			break;
		}
        winText.setTranslateX(Main.WIDTH / 2.0-10);
        winText.setTranslateY((Main.HEIGHT-100) / 2.0 - 20);
        root.getChildren().addAll(restartBtn, winText);
		
	}

	// privacy leak
	public void removeBullet(Bullet bullet){
		root.getChildren().removeAll(bullet.getView());
	}
	
	public void removeTank(Tank tank){
		root.getChildren().removeAll(tank.getView());
	}
	
	// privacy leak
	public void removeAll(Bullet bullet,Tank tank){
		root.getChildren().removeAll(bullet.getView(), tank.getView());
	}	
}