package codes;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	@Override
	public void start(Stage stage) throws Exception {
			stage.setScene(new Scene(createContent()));
			stage.getScene().setOnKeyPressed(e -> {
					if (e.getCode() == KeyCode.LEFT) {
							player.rotateLeft();
					} else if (e.getCode() == KeyCode.RIGHT) {
							player.rotateRight();
					} else if (e.getCode() == KeyCode.SPACE) {
							Bullet bullet = new Bullet();
							bullet.setVelocity(player.getVelocity().normalize().multiply(5));
							addBullet(bullet, player.getView().getTranslateX(), player.getView().getTranslateY());
					}
			});
			stage.show();
	}

	public static void main(String[] args) {
			launch(args);
	}

}
