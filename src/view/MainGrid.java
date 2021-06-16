package view;

import java.util.List;

import controller.GameController;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

public class MainGrid {

	private GameController gameController;

	private BlockGrid blockGrid;
	private CellGrid cellGrid;
	
	private GridPane gridPane;
	private int cellValue;

	public MainGrid(GameController gameController) {
		this.gameController = gameController;
	}

	public GridPane drawPane() {
		gridPane = new GridPane();

		gridPane.setHgap(15);
		gridPane.setVgap(15);
		gridPane.setPadding(new Insets(15, 0, 0, 15));
		gridPane.setStyle("-fx-background-color: #202020;");
		gridPane.setGridLinesVisible(false); // TODO REMOVE
		gridPane.autosize();
		gridPane.setPrefSize(800, 800);

		return gridPane;
	}

	public GridPane fillGrid(List<String> puzzle) {

		for (int row = 0; row < 9; row++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(((750 - (7 * 15)) / 9)));
			gridPane.getRowConstraints().add(new RowConstraints(((750 - (7 * 15)) / 9)));
			for (int col = 0; col < 9; col++) {

				cellValue = Character.getNumericValue(puzzle.get(row).charAt(col));

				if (cellValue == 0) {
					gridPane.add(drawGridCell(), col, row);
				} else {
					gridPane.add(drawCell(cellValue), col, row);
				}

			}
		}

		return gridPane;
		
		//TODO
		//create new grid with loaded values
		//create new blocks with 9 cells
		//create new rows with 9 cells
		//create new columns with 9 cells
		
		
		//connect gameController to block/row/column that is in action
		//connect gameController to cell that is in action
		//connect gameController to cell to possibility that is in action
	}
	
	public FlowPane createBlockGrid() {
		
		
		
		
		return null;
	}

	public Text drawCell(int value) {
		return new Text(String.valueOf(value));
	}

	public GridPane drawGridCell() {
		GridPane pos = new GridPane();

		pos.setVgap(5);
		pos.setHgap(5);
		pos.setPadding(new Insets(0, 0, 0, 0));
		pos.setStyle("-fx-background-color: #aaffaa;");
		pos.setMaxSize(64, 64);
		pos.setGridLinesVisible(true); // TODO REMOVE

		int innerInt = 1;
		for (int v = 0; v < 3; v++) {
			pos.getColumnConstraints().add(new ColumnConstraints(18));
			pos.getRowConstraints().add(new RowConstraints(18));
			for (int h = 0; h < 3; h++) {

				pos.add(new Text(String.valueOf(innerInt)), h, v);
				innerInt++;
			}
		}

		return pos;
	}

}
