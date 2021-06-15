package view;



import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonPane {

	public ButtonPane() {
		
	}
	
	public VBox drawPane() {
		VBox vBox = new VBox();
	    
		vBox.setPadding(new Insets(15, 10, 15, 10));
		vBox.setPrefWidth(125);
		vBox.setPrefHeight(800);
		vBox.setSpacing(10);
		vBox.setStyle("-fx-background-color: #F0F9FF;");
		
		Button btnStop = new Button("Stop");
		btnStop.setPrefSize(75, 20);
		
		Button btnAuto = new Button("Auto");
		btnAuto.setPrefSize(75, 20);
		
		vBox.getChildren().addAll(btnStop, btnAuto);
		
		return vBox;
	}
	
}
