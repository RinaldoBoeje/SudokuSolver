package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.MyScene;


public class MainController extends Application {
	private MyScene myScene;
	
	public MainController() {
	}

	public void launchFX(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane pane = new Pane();
		
		stage.setScene(new MyScene(new VBox(), 925, 800));
		stage.setResizable(false);
		stage.setTitle("Sudoku Solver");
		stage.show();	
	}
	
	public void exit() {
		System.exit(0);
	}
	
	
	
}
