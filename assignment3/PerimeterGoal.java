package assignment3;

import java.awt.Color;

public class PerimeterGoal extends Goal{

	public PerimeterGoal(Color c) {
		super(c);
	}

	@Override
	public int score(Block board) {
		Color[][] c = board.flatten();
		int sum = 0;
		for (int i =0; i < c.length; i++){
			if (c[i][0] == this.targetGoal){
				sum++;
			}
		}
		for (int i =0; i < c.length; i++){
			if (c[0][i] == this.targetGoal){
				sum++;
			}
		}
		for (int i =0; i < c.length; i++){
			if (c[i][c.length-1] == this.targetGoal){
				sum++;
			}
		}
		for (int i =0; i < c.length; i++){
			if (c[c.length-1][i] == this.targetGoal){
				sum++;
			}
		}
		return sum;
	}

	@Override
	public String description() {
		return "Place the highest number of " + GameColors.colorToString(targetGoal) 
		+ " unit cells along the outer perimeter of the board. Corner cell count twice toward the final score!";
	}

}
