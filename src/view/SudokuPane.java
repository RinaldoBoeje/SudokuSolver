package view;

import javafx.scene.layout.TilePane;
import java.util.List;


import controller.GameController;

public class SudokuPane {

	private GameController gameController;

	private MainGrid mainGrid;
	
	private TilePane tilePane;

	public SudokuPane(GameController gameController) {
		this.gameController = gameController;
		this.tilePane = new TilePane();
	}
	
	public TilePane drawPane() {
		mainGrid = new MainGrid(gameController);
		
		tilePane.setStyle("-fx-background-color: #202020");
		tilePane.getChildren().add(mainGrid.drawPane());
		
		return tilePane;
	}
	
	public void InitializeGrid(List<String> puzzle) {
		
		mainGrid.fillGrid(puzzle);
	}

	// SudokuPane
	//// gridpane
	////// cellpane
	//////// possibillitypane

}
