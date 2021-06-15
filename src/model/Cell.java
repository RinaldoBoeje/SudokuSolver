package model;

public class Cell {
	
	private int value;
	private int[] possibilities = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	//private int block;
	
	
	public Cell() {
		value = 0;
	}
	
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
		for(int i = 0; i < possibilities.length; i++) {
			possibilities[i] = 0;
		}
	}
	
	public int[] getPossibilities() {
		return possibilities;
	}
	
	//reminder, to remove a value, use value -1
	//e.g. if found value is 6, to remove 6 you have to use array index 5, 
	//so value(6) -1 = (5)
	public void removePossibilityValue(int index) {
		possibilities[index] = 0;
	}
	
	/*public void setBlock(int block) {
		this.block = block;
	}
	
	public int getBlock() {
		return block;
	}*/
}
