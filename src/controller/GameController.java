package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import view.SudokuPane;
import model.Block;
import model.Cell;
import model.SudokuField;
public class GameController {

	private List<String> puzzle;
	public SudokuPane sudokuPane;
	private SudokuField sudokuField;
	
	Block block;
	Cell[][] allCells;
	String[][] currentBlock;
	int cbRow;
	int cbCol;
	
	boolean hasChanged;
	
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
			
			sudokuPane.InitializeGrid(puzzle);
			// save values to model
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
	
	
	public void Solver() {
		
		
		
		block = new Block();
		allCells = sudokuField.getCells();
		currentBlock = new String[3][3];
		cbRow = 0;
		cbCol = 0;
		
		
		
		int foundValue;
		ArrayList<Integer>foundPossibilities = new ArrayList<Integer>();
		int checkCurrent[] = new int[2];
		int foundPossibleValue;
		
		int checkPossible[] = new int[2];
		
		
		//TODO REMOVE
		System.out.println("----------");
		System.out.print("before Anything changed");
		WriteField();
		
		hasChanged = true;
		while(hasChanged == true) {
			hasChanged = false;
			
			//Loop through block
			for(int block = 0; block <9; block++) {
				SetCurrentBlock(block);
				
				for(int br = 0; br < 3; br++) {
					for(int bc = 0; bc<3; bc++) {
						
						checkCurrent[0] = Character.getNumericValue(currentBlock[br][bc].charAt(0));
						checkCurrent[1] = Character.getNumericValue(currentBlock[br][bc].charAt(1));
						
						//Puzzle 1, first value to be found is value 2. Why is it removing value 2? 
						foundValue = allCells[checkCurrent[0]][checkCurrent[1]].getValue();
						if(foundValue != 0) {
							
							//loop through block and mark possibility
							for(int posRow = 0; posRow<3; posRow++) {
								for(int posCol =0; posCol <3; posCol++) {
									checkCurrent[0] = Character.getNumericValue(currentBlock[posRow][posCol].charAt(0));
									checkCurrent[1] = Character.getNumericValue(currentBlock[posRow][posCol].charAt(1));
									
									//This removes found possiblevalue from list
									//TODO, remove possibleValue after all possible values have been found
									//use foundPossibillities
									foundPossibleValue = allCells[checkCurrent[0]][checkCurrent[1]].getValue();
									if(foundPossibleValue == 0) {
										
										RemovePossibillity(checkCurrent[0], checkCurrent[1], foundValue);
									}								
								}
							}
						}
					}
				}
			}
			
			//TODO REMOVE
			System.out.println("----------");
			System.out.print("After Block");
			WriteField();
			
			//loop through row
			for(int rowRow =0; rowRow<9; rowRow++) {
				
				for(int rowCol=0; rowCol<9; rowCol++) {
									
					foundValue = allCells[rowRow][rowCol].getValue();
					
					if(foundValue != 0) {
						
						for(int checkCol =0; checkCol<9; checkCol++) {
							
							foundPossibleValue = allCells[rowRow][checkCol].getValue();
							if(foundPossibleValue == 0) {
								RemovePossibillity(rowRow, checkCol, foundValue);
							}
						}
					}
				}
			}
			
			//TODO REMOVE
			System.out.println("----------");
			System.out.print("After Row");
			WriteField();
			
			//loop through col
			for(int colCol =0; colCol<9; colCol++) {
				
				for(int colRow=0; colRow<9; colRow++) {
									
					foundValue = allCells[colRow][colCol].getValue();
					
					if(foundValue != 0) {
						
						for(int checkRow =0; checkRow<9; checkRow++) {
							
							foundPossibleValue = allCells[checkRow][colCol].getValue();
							if(foundPossibleValue == 0) {
								RemovePossibillity(checkRow, colCol, foundValue);
							}
						}
					}
				}
			}
			
			//TODO REMOVE
			System.out.println("----------");
			System.out.print("AfterCol");
			WriteField();
		}
		
		
		
	}	
	
	private void SetCurrentBlock(int currentBlockValue) {
		
		cbCol=0;
		cbRow=0;
		//Set values for current block
		for(int row = 0; row < 9; row++) {
			for(int col = 0; col < 9; col++) {
				if(block.GetBlock(row, col) == currentBlockValue) {
					currentBlock[cbRow][cbCol] = String.valueOf(row)+String.valueOf(col);
					if(cbCol == 2) {
						cbCol = 0;
						cbRow++;
					}
					else {
						cbCol++;
					}
				}
			}
		}
	}
	
	private void RemovePossibillity(int row, int col, int val) {
		

		
		if(allCells[row][col].getPossibilities()[val-1] == val) {
			allCells[row][col].removePossibilityValue(val);
			hasChanged = true;
		}
		 
		int numberOfPossibillities = 9;
		for(int value : allCells[row][col].getPossibilities()) {
			if(value == 0)
			{
				numberOfPossibillities--;
			}
		}
		if(numberOfPossibillities == 1) {			
			//find value to be replaced and place here
			for(int value : allCells[row][col].getPossibilities()) {
				if(value != 0)
				{
					allCells[row][col].setValue(value);
					hasChanged = true;
				}
			}
		}
	}
	
	//remove
	public void showMeTheMoney() {
		for(int i=0;i<9;i++) {
			for(int j =0;j<9;j++) {
				System.out.println("Row: " + i + " Col: " + j);
				for(int number : allCells[i][j].getPossibilities()) {
					System.out.print(number);
				}
				System.out.println("");
			}
		}
	}
	
	//remove
	public void WriteField() {
		System.out.println();
		for(int i =0; i<9; i++) {
			for(int j =0; j<9; j++) {
				System.out.print(sudokuField.getCells()[i][j].getValue());
			}
			System.out.println("");
		}
	}
	
}
