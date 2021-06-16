package view;



import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import controller.GameController;

public class ButtonPane {

	private GameController gameController;
	
	public ButtonPane(GameController gameController) {
		this.gameController = gameController;
	}
	
	public VBox drawPane() {
		VBox vBox = new VBox();
	    
		vBox.setPadding(new Insets(15, 10, 15, 10));
		vBox.setPrefWidth(125);
		vBox.setPrefHeight(800);
		vBox.setSpacing(10);
		vBox.setStyle("-fx-background-color: #F0F9FF;");
		
		Button btnStep = new Button("Step");
		btnStep.setPrefSize(75, 20);
		
		Button btnAuto = new Button("Auto");
		btnAuto.setPrefSize(75, 20);
		btnAuto.setOnAction(e -> this.gameController.Solver());
		
		vBox.getChildren().addAll(btnStep, btnAuto);
		
		return vBox;
	}
	
}
