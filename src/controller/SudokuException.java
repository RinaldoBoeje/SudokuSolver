package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SudokuException extends Exception {

	Alert alert;
	
	public SudokuException(String filename, int lineNumber, String description){
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("File could not be openend");
		alert.setHeaderText("File could not be opened");
		alert.setContentText(String.format("The file %s could not be read as it contains an error, this can be found on line %s showing %s",filename,lineNumber,description)); 
		alert.showAndWait();
	}
	
	
	
}
