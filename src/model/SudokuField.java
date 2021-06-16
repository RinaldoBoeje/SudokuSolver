package model;

import java.util.List;

public class SudokuField {

	private Cell[][] field;
	private Block block;
	
	public SudokuField() {
		field = new Cell[9][9];
		block = new Block();
	}
	
	public void initializeField(List<String> puzzle) {
		int colValue;
		
		for(int row = 0; row < puzzle.size(); row++) {
			for(int col = 0; col < puzzle.get(row).length(); col++) {
				
				colValue = Character.getNumericValue(puzzle.get(row).charAt(col));
				
				if(colValue != 0) {
					field[row][col] = new Cell();
					field[row][col].setValue(colValue);
				}
				else {
					field[row][col] = new Cell();
				}
			}
			
		}

	}
	
	public Cell[][] getCells(){
		return field;
		
	}
}
