package model;

public class Block {

	int block;
	
	public Block() {
		
	}
	
	
	
	public int AssignBlocks(int row, int col) {
		
		if(row >= 0 && row <= 2) {
			if(col >= 0 && col <=2) {
				block = 0;
			}
			else if(col >= 3 && col <=5) {
				block = 1;
			}
			else if(col >= 6 && col <=8) {
				block = 2;
				
			}
		}
		else if (row >= 3 && row <= 5) {
			if(col >= 0 && col <=2) {
				block = 3;
			}
			else if(col >= 3 && col <=5) {
				block = 4;
			}
			else if(col >= 6 && col <=8) {
				block = 5;
				
			}
		}
		else if (row >= 6 && row <= 8) {
			if(col >= 0 && col <=2) {
				block = 6;
			}
			else if(col >= 3 && col <=5) {
				block = 7;
			}
			else if(col >= 6 && col <=8) {
				block = 8;
				
			}
		}
		
		return block;
	}
	

		
}

