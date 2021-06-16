package view;

import java.io.File;
import java.io.IOException;


import controller.GameController;
import controller.SudokuException;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainScene extends Scene{


	private FileChooser fileChooser;
	private BorderPane borderPane;
	private GameController gameController;
	
	private SudokuPane sudokuPane;
	private ButtonPane buttonPane;
	

	public MainScene(VBox parent, double width, double height, GameController gameController) {
		super(parent, width, height);
		this.gameController = gameController;

		borderPane = new BorderPane();
		buttonPane = new ButtonPane(gameController);
		sudokuPane = new SudokuPane(gameController);

		borderPane.setLeft(buttonPane.drawPane());
		borderPane.setCenter(sudokuPane.drawPane());
		
		parent.getChildren().addAll(createMenu(), borderPane);
		
	}
	
	//Menu bar
	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load puzzle...");
		load.setOnAction(e -> {
			try {
				this.gameController.sudokuPane = sudokuPane; //Move to after data is loaded
				this.gameController.readPuzzle(loadPuzzle());
			} catch (IOException | SudokuException e1) {
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(e -> System.exit(0));
		
		file.getItems().addAll(load, exit);
		
		menuBar.getMenus().add(file);
		return menuBar;
	}
	
	//ButtonPane
	public VBox createButtonPane() {
		
		return buttonPane.drawPane();
	}
	

	//Load Puzzle
	private File loadPuzzle()
	{
		//load a puzzle
		fileChooser = new FileChooser();
		
		//Only show .sud files
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Sudoku Files", "*.sud"));
		
		//show files in resources/files
		fileChooser.setInitialDirectory(new File("Resources/files"));
		
		return fileChooser.showOpenDialog(getWindow());
	}

}