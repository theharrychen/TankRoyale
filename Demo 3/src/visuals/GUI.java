package visuals;

import logic.*;
import drivers.*;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
		root.setPrefSize(MainGUI.WIDTH, MainGUI.HEIGHT);
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
        p1tally.setTranslateX(MainGUI.WIDTH /2.0 - 150);
		p2tally.setTranslateX(MainGUI.WIDTH /2.0+ 150);
		p1.setTranslateX(MainGUI.WIDTH /2.0 - 170);
		p2.setTranslateX(MainGUI.WIDTH /2.0 + 130);
        p1tally.setTranslateY(MainGUI.HEIGHT-75);
		p2tally.setTranslateY(MainGUI.HEIGHT-75);
		p1.setTranslateY(MainGUI.HEIGHT-35);
		p2.setTranslateY(MainGUI.HEIGHT-35);
		p1tally.setFont(new Font(30));
		p2tally.setFont(new Font(30));
        root.getChildren().addAll(p1tally, p2tally,p1,p2);
	}
	
	public void clear(){
		root.getChildren().clear();
	}
	
	public void addVisualGameEntity(GameEntity entity){
		root.getChildren().add(entity.getView());
	}
	
	public void createRestartButton(){
		//Create restart button
        restartBtn.setTranslateX(MainGUI.WIDTH / 2.0 );
        restartBtn.setTranslateY((MainGUI.HEIGHT-100) / 2.0);
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
        winText.setTranslateX(MainGUI.WIDTH / 2.0-10);
        winText.setTranslateY((MainGUI.HEIGHT-100) / 2.0 - 20);
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