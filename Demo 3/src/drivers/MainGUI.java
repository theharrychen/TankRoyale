package drivers;


/**
 * This class is used to run the game
 * 
 * @author Group 7, adapted from Almas Baimagambetov: https://www.youtube.com/
	 watch?v=l2XhUHW8Oa4&list=PLurZmf6mNWh4oNzAph6vk14xj9NdS-RCP&index=2&t=0s
 * @version 1.0
 * @since 2019-03-06
 */

import visuals.*;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainGUI extends Application {

    public static final double WIDTH = 1080.0, HEIGHT = 608.0; //16:9 Window Aspect Ratio
	
	/**
	 *Main entry point of JavaFX application
	 */
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tank Royale");
        stage.setResizable(false);

        MenuGUI menu = new MenuGUI();

        menu.start(stage);

        stage.show();
    }


	/**
	 *Launches the game
	 */
    public static void main(String[] args){
        launch(args);
    }

}
