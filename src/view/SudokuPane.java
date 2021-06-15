package view;

import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;

import java.util.Random;

public class SudokuPane {

	public SudokuPane() {
		
	}
	
	public GridPane drawPane(){
		GridPane gridPane = new GridPane();
		
		gridPane.setHgap(15);
		gridPane.setVgap(15);
		gridPane.setPadding(new Insets(15, 0, 0,15));
		gridPane.setStyle("-fx-background-color: #aaaaff;");
		gridPane.setGridLinesVisible(true);
		gridPane.autosize();
		gridPane.setPrefSize(800, 800);
		
		
		
		for(int col = 0; col < 9; col++) {
			gridPane.getColumnConstraints().add(new ColumnConstraints(((750-(7*15))/9)));
			gridPane.getRowConstraints().add(new RowConstraints(((750-(7*15))/9)));
			for(int row = 0; row<9; row++) {
				
				
				
				//TEMPORARY TODO REMOVE
				Random rand = new Random();
				int randInt = rand.nextInt(10);
				
				
				if(randInt == 0) {
					gridPane.add(drawGridCell(), row, col);
					//gridPane.add(drawCell(randInt), row, col);
				}
				else {
					gridPane.add(drawCell(randInt), row, col);
				}
				
			}
		}

		return gridPane;
	}
	
	
	
	public Text drawCell(int value) {
		return new Text(String.valueOf(value));
	}
	
	public GridPane drawGridCell( ) {
		GridPane pos = new GridPane();
		
		pos.setVgap(5);
		pos.setHgap(5);
		pos.setPadding(new Insets(0, 0, 0, 0));
		pos.setStyle("-fx-background-color: #aaffaa;");
		pos.setMaxSize(64, 64);
		pos.setGridLinesVisible(true);
		
		int innerInt = 1;
		for(int v = 0; v < 3; v++) {
			pos.getColumnConstraints().add(new ColumnConstraints(18));
			pos.getRowConstraints().add(new RowConstraints(18));
			for(int h = 0; h < 3; h++) {
				

				pos.add(new Text(String.valueOf(innerInt)), h, v);
				innerInt++;
			}
		}
		
		return pos;
	}
			
}
