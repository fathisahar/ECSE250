package assignment3;

import java.awt.Color;

public class BlobGoal extends Goal{

	public BlobGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		int sum = 0;
		Color[][] flattened = board.flatten();
		boolean[][] visited = new boolean[flattened.length][flattened[0].length];
		for (int i = 0; i < flattened.length; i++) {
			for (int j = 0; j < flattened[i].length; j++) {
				if (flattened[i][j] == this.targetGoal && !visited[i][j]) {
					int blobSize = undiscoveredBlobSize(i, j, flattened, visited);
					sum += blobSize;
				}
			}
		}
		return sum;
	}

	@Override
	public String description() {
		return "Create the largest connected blob of " + GameColors.colorToString(targetGoal) 
		+ " blocks, anywhere within the block";
	}


	public int undiscoveredBlobSize2(int i, int j, Color[][] unitCells, boolean[][] visited) {
		Color targetColor = this.targetGoal;
		if (visited[i][j] || !targetColor.equals(unitCells[i][j])) {
			return 0;
		}
		visited[i][j] = true;
		int blobSize = 1;

 		if (i>0 && j>0 && i < unitCells.length - 1 && j < unitCells.length - 1){
			 if (targetColor.equals(unitCells[i-1][j]) || targetColor.equals(unitCells[i][j-1]) || targetColor.equals(unitCells[i][j+1]) || targetColor.equals(unitCells[i-1][j])){
				if (i > 0) {
					blobSize += undiscoveredBlobSize2(i - 1, j, unitCells, visited);
				}
				if (j > 0) {
					blobSize += undiscoveredBlobSize2(i, j - 1, unitCells, visited);
				}
				if (i < unitCells.length - 1) {
					blobSize += undiscoveredBlobSize2(i + 1, j, unitCells, visited);
				}
				if (j < unitCells[0].length - 1) {
					blobSize += undiscoveredBlobSize2(i, j + 1, unitCells, visited);
				}
			}
		}
		return blobSize;
	}

	public int undiscoveredBlobSize(int i, int j, Color[][] unitCells, boolean[][] visited) {
		if (i < 0 || i >= unitCells.length || j < 0 || j >= unitCells[0].length){
			return 0;
		}

		if (visited[i][j] || unitCells[i][j] != this.targetGoal){
			return 0;
		}
		int size = 1;
		visited[i][j] = true;
		size += undiscoveredBlobSize(i-1,j,unitCells,visited);
		size += undiscoveredBlobSize(i+1,j,unitCells,visited);
		size += undiscoveredBlobSize(i,j-1,unitCells,visited);
		size += undiscoveredBlobSize(i,j+1,unitCells,visited);
		return size;
	}


}
