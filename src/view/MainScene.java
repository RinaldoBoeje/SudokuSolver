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

	

	public MainScene(VBox parent, double width, double height, GameController gameController) {
		super(parent, width, height);

		borderPane = new BorderPane();
		borderPane.setLeft(createButtonPane());
		borderPane.setCenter(createSudokuPane());
		parent.getChildren().addAll(createMenu(), borderPane);
		this.gameController = gameController;
	}
	
	//Menu bar
	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load puzzle...");
		load.setOnAction(e -> {
			try {
				this.gameController.sudokuPane = sudokuPane;
				this.gameController.readPuzzle(loadPuzzle());
				//TODO REMOVE, Put under button, JUST TEMP
				this.gameController.Solver();
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
		ButtonPane buttonPane = new ButtonPane();
		return buttonPane.drawPane();
	}
	
	//SudokuPane
	public GridPane createSudokuPane() {
		sudokuPane = new SudokuPane();
		return sudokuPane.drawPane();
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