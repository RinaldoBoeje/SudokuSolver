package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import view.SudokuPane;
import model.SudokuField;
public class GameController {

	private List<String> puzzle;
	public SudokuPane sudokuPane;
	private SudokuField sudokuField;
	
	GameController(){
		
	}
	
	
	public void exit() {
		System.exit(0);
	}
	
	public List<String> readPuzzle(File file) throws SudokuException, IOException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String str;
		
		puzzle = new ArrayList<String>();
		
		while((str = bufferedReader.readLine()) != null) {
			puzzle.add(str);
		}
		
		bufferedReader.close();
		if(verifyPuzzle(puzzle, file) == true) {
			//return puzzle;
			sudokuPane.fillGrid(puzzle);
			//save values to model
			sudokuField = new SudokuField();
			sudokuField.initializeField(puzzle);
			
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
