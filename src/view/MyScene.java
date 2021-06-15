package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import controller.MainController;
import controller.SudokuException;
import view.ButtonPane;
import view.SudokuPane;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MyScene extends Scene{


	private FileChooser fileChooser;
	private BorderPane borderPane;
	
	private List<String> puzzle;
	

	public MyScene(VBox parent, double width, double height) {
		super(parent, width, height);

		borderPane = new BorderPane();
		borderPane.setLeft(createButtonPane());
		borderPane.setCenter(createSudokuPane());
		parent.getChildren().addAll(createMenu(), borderPane);

	}
	
	//Menu bar
	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem load = new MenuItem("Load puzzle...");
		load.setOnAction(e -> {
			try {
				readPuzzle(loadPuzzle());
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
		SudokuPane sudokuPane = new SudokuPane();
		
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
	
	private List<String> readPuzzle(File file) throws SudokuException, IOException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String str;
		
		puzzle = new ArrayList<String>();
		
		while((str = bufferedReader.readLine()) != null) {
			puzzle.add(str);
			System.out.println(str);
		}
		
		System.out.println(puzzle.size());
		bufferedReader.close();
		if(verifyPuzzle(puzzle, file) == true) {
			return puzzle;	
		}
		return null;
	}
	
	private boolean verifyPuzzle(List<String> puzzle, File file) throws SudokuException {
		
		boolean isTrue = true;
		String row;
		char character;
		
		if(puzzle.size() == 9) {
			for (int i = 0; i < puzzle.size(); i++) {
				row = puzzle.get(i);
				if(row.length() == 9) {
					for (int j = 0; j < row.length(); j++) {
						character = row.charAt(i);
						if(Character.isDigit(character) == false) {
							throw new SudokuException(file.getName(), i, row);
						}							
					}
				}
				else {
					throw new SudokuException(file.getName(), i, row);
				}
			}
		}
		else { 
			throw new SudokuException(file.getName(), puzzle.size(), puzzle.toString());
		}
		
		return isTrue;
	}

}