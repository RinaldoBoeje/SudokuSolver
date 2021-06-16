package view;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

public class BlockGrid {

	
	FlowPane blockGridOverlay;
	Rectangle blankSquare;
	
	public BlockGrid() {
		
		
	}
	
	
	public FlowPane CreateBlockOverlay() {
		
		blockGridOverlay = new FlowPane(Orientation.HORIZONTAL, 5, 5);
		blockGridOverlay.setPadding(new Insets(10));
		
		for(int i = 0; i<9; i++) {
			blockGridOverlay.getChildren().add(createBlankSquare());
		}
		
		
		return blockGridOverlay;
	}
	
	public Rectangle createBlankSquare() {
		
		blankSquare = new Rectangle();
		blankSquare.setWidth(250);
		blankSquare.setHeight(250);
		blankSquare.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-border-color: #202020");
		
		return blankSquare;
	}
	
}
