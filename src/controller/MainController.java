package controller;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainScene;


public class MainController extends Application {
	public MainController() {
	}

	public void launchFX(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new MainScene(new VBox(), 925, 800, new GameController()));
		stage.setResizable(false);
		stage.setTitle("Sudoku Solver");
		stage.show();	
	}
	
}
