package main;

import domein.LoginController;
import gui.AanmeldSchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public void start(Stage stage) {

		LoginController lc = new LoginController();
		AanmeldSchermController as = new AanmeldSchermController(lc);
		Scene scene = new Scene(as, 600, 500);
		stage.setScene(scene);
		stage.show();
		stage.centerOnScreen();
	}

	public static void main(String[] args) {
		launch(args);
	}
}