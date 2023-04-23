package assignment3;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;

public class Block {
	
	private int xCoord;
	private int yCoord;
	private int size; // height/width of the square
	private int level; // the root (outer most block) is at level 0
	private int maxDepth; 
	private Color color;
	private Block[] children; // {UR, UL, LL, LR}

	public static Random gen = new Random();
 
 
	/*
	 * These two constructors are here for testing purposes. 
	 */
	public Block() {}
 
	public Block(int x, int y, int size, int lvl, int  maxD, Color c, Block[] subBlocks) {
		this.xCoord=x;
		this.yCoord=y;
		this.size=size;
		this.level=lvl;
		this.maxDepth = maxD;	
		this.color=c;
		this.children = subBlocks;
	}
 
 
	/*
	 * Creates a random block given its level and a max depth. 
	 * 
	 * xCoord, yCoord, size, and highlighted should not be initialized
	 * (i.e. they will all be initialized by default)
	 */
	public Block(int lvl, int maxDepth) {
		if (size < 0 || lvl < 0 || maxDepth <= 0){
			throw new IllegalArgumentException();
		}
		if (lvl < 0 || maxDepth <= 0){
			throw new IllegalArgumentException();
		}

		if (lvl > maxDepth){
			throw new IllegalArgumentException();
		}
		this.level = lvl;
		this.maxDepth = maxDepth;
		this.children = new Block[0];

		if (this.level < this.maxDepth) {
			double randomNum = gen.nextDouble();
			if (randomNum < Math.exp(-0.25*this.level)){
				this.children = new Block[4];
				for (int i = 0; i<4; i++){
					this.children[i] = new Block(lvl+1, maxDepth);
				}
			} else {
				int index = gen.nextInt(4);
				this.color = GameColors.BLOCK_COLORS[index];
			}
		} else if (this.level == maxDepth){
			int index = gen.nextInt(4);
			this.color = GameColors.BLOCK_COLORS[index];
		}
	}

	/*
	  * Updates size and position for the block and all of its sub-blocks, while
	  * ensuring consistency between the attributes and the relationship of the 
	  * blocks. 
	  * 
	  *  The size is the height and width of the block. (xCoord, yCoord) are the 
	  *  coordinates of the top left corner of the block. 
	 */
	public void updateSizeAndPosition (int size, int xCoord, int yCoord) {
		this.size = size;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		if (size <= 0){
			throw new IllegalArgumentException();
		} else if (this.size != 1 && this.size%(Math.pow(2, this.maxDepth - this.level)) != 0){
			throw new IllegalArgumentException();
		} else {
			if (this.children.length == 0) {
				this.size = size;
				this.xCoord = xCoord;
				this.yCoord = yCoord;
			} else {
				for (int i = 0; i < 4; i++) {
					if (i == 0){
						this.children[i].updateSizeAndPosition(this.size / 2, this.xCoord + (this.size/2), this.yCoord);
					} else if (i == 1) {
						this.children[i].updateSizeAndPosition(this.size / 2, this.xCoord, this.yCoord);
					} else if (i == 2){
						this.children[i].updateSizeAndPosition(this.size / 2, this.xCoord, this.yCoord + (this.size/2));
					} else {
						this.children[i].updateSizeAndPosition(this.size / 2, this.xCoord + (this.size/2), this.yCoord + (this.size/2));
					}
				}
			}
		}
	}

 
	/*
  	* Returns a List of blocks to be drawn to get a graphical representation of this block.
  	* 
  	* This includes, for each undivided Block:
  	* - one BlockToDraw in the color of the block
  	* - another one in the FRAME_COLOR and stroke thickness 3
  	* 
  	* Note that a stroke thickness equal to 0 indicates that the block should be filled with its color.
  	*  
  	* The order in which the blocks to draw appear in the list does NOT matter.
  	*/
	public ArrayList<BlockToDraw> getBlocksToDraw() {
		ArrayList<BlockToDraw> blocksToDraw = new ArrayList<>();
		addBTD(blocksToDraw);
		return blocksToDraw;
	}

	private void addBTD(ArrayList<BlockToDraw> blocksToDraw) {
		if (this.children.length == 0) {
			blocksToDraw.add(new BlockToDraw(this.color, this.xCoord, this.yCoord, this.size, 0));
			blocksToDraw.add(new BlockToDraw(GameColors.FRAME_COLOR, this.xCoord, this.yCoord, this.size, 3));
		} else {
			for (int i = 0; i < 4; i++) {
				this.children[i].addBTD(blocksToDraw);
			}
		}
	}

	/*
	 * This method is provided and you should NOT modify it. 
	 */
	public BlockToDraw getHighlightedFrame() {
		return new BlockToDraw(GameColors.HIGHLIGHT_COLOR, this.xCoord, this.yCoord, this.size, 5);
	}
 
 
 
	/*
	 * Return the Block within this Block that includes the given location
	 * and is at the given level. If the level specified is lower than 
	 * the lowest block at the specified location, then return the block 
	 * at the location with the closest level value.
	 * 
	 * The location is specified by its (x, y) coordinates. The lvl indicates 
	 * the level of the desired Block. Note that if a Block includes the location
	 * (x, y), and that Block is subdivided, then one of its sub-Blocks will 
	 * contain the location (x, y) too. This is why we need lvl to identify 
	 * which Block should be returned. 
	 * 
	 * Input validation: 
	 * - this.level <= lvl <= maxDepth (if not throw exception)
	 * - if (x,y) is not within this Block, return null.
	 */

	public Block getSelectedBlock(int x, int y, int lvl) {
		if (this.level > lvl || lvl > this.maxDepth){
			throw new IllegalArgumentException();
		}
		if (this.xCoord <= x && this.yCoord <= y && this.xCoord+this.size >= x && this.yCoord+this.size >= y && this.level >= lvl){
			return this;
		} else if (this.children.length != 4 && this.level <= lvl && this.xCoord <= x && this.yCoord <= y && this.xCoord+this.size >= x && this.yCoord+this.size >= y){
			return this;
		} else if (this.xCoord <= x && this.xCoord+this.size/2 >= x && this.yCoord <= y && this.yCoord+this.size/2 >= y){
			return this.children[1].getSelectedBlock(x, y, lvl);
		} else if (this.xCoord+this.size/2 <= x && this.xCoord+this.size >= x  && this.yCoord <= y && this.yCoord+this.size/2 >= y){
			return this.children[0].getSelectedBlock(x, y, lvl);
		} else if (this.xCoord <= x && this.xCoord+this.size/2 >= x && this.yCoord+this.size/2 <= y && this.yCoord+this.size >= y){
			return this.children[2].getSelectedBlock(x, y, lvl);
		} else if (this.xCoord+this.size >= x && this.xCoord+this.size/2 <= x && this.yCoord+this.size >= y && this.yCoord+this.size/2 <= y){
			return this.children[3].getSelectedBlock(x, y, lvl);
		}
		return null;
	}

	/*
	 * Swaps the child Blocks of this Block. 
	 * If input is 1, swap vertically. If 0, swap horizontally. 
	 * If this Block has no children, do nothing. The swap 
	 * should be propagate, effectively implementing a reflection
	 * over the x-axis or over the y-axis.
	 * 
	 */
	public void reflect(int direction) {
		if (direction != 0 && direction != 1){
			throw new IllegalArgumentException();
		} else {
			if (this.children.length != 0){
				this.updater(direction,0);
				this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
			}
		}
	}

	/*
	 * Rotate this Block and all its descendants.
	 * If the input is 1, rotate clockwise. If 0, rotate
	 * counterclockwise. If this Block has no children, do nothing.
	 */
	public void rotate(int direction) {
		if(direction != 0 && direction != 1){
			throw new IllegalArgumentException();
		} else {
			if (this.children.length != 0){
				this.updater(direction,1);
				this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
			}
		}
	}

	private void updater(int direction, int action) {
		if (this.children.length != 0) {
			Block[] temp = new Block[4];
			temp[0] = this.children[0];
			temp[1] = this.children[1];
			temp[2] = this.children[2];
			temp[3] = this.children[3];
			if (direction == 0 && action == 0) {
				this.children[0] = this.children[3];
				this.children[1] = this.children[2];
				this.children[2] = temp[1];
				this.children[3] = temp[0];
				this.children[0].updater(direction, action);
				this.children[1].updater(direction, action);
				this.children[2].updater(direction, action);
				this.children[3].updater(direction, action);
			} else if (direction == 1 && action == 0) {
				this.children[0] = temp[1];
				this.children[1] = temp[0];
				this.children[2] = temp[3];
				this.children[3] = temp[2];
				this.children[0].updater(direction, action);
				this.children[1].updater(direction, action);
				this.children[2].updater(direction, action);
				this.children[3].updater(direction, action);
			} else if (direction == 0 && action == 1) {
				this.children[0] = temp[3];
				this.children[1] = temp[0];
				this.children[2] = temp[1];
				this.children[3] = temp[2];
				this.children[0].updater(direction, action);
				this.children[1].updater(direction, action);
				this.children[2].updater(direction, action);
				this.children[3].updater(direction, action);
			} else if (direction == 1 && action == 1) {
				this.children[0] = temp[1];
				this.children[1] = temp[2];
				this.children[2] = temp[3];
				this.children[3] = temp[0];
				this.children[0].updater(direction, action);
				this.children[1].updater(direction, action);
				this.children[2].updater(direction, action);
				this.children[3].updater(direction, action);
			}

		}
	}

 


	/*
	 * Smash this Block.
	 * 
	 * If this Block can be smashed,
	 * randomly generate four new children Blocks for it.  
	 * (If it already had children Blocks, discard them.)
	 * Ensure that the invariants of the Blocks remain satisfied.
	 * 
	 * A Block can be smashed iff it is not the top-level Block 
	 * and it is not already at the level of the maximum depth.
	 * 
	 * Return True if this Block was smashed and False otherwise.
	 * 
	 */
	public boolean smash() {
		if (this.level <= maxDepth && this.level != 0 ){
			if (this.level == maxDepth){
				return false;
			}
			this.children = new Block[4];
			this.children[0] = new Block(this.level+1, this.maxDepth);
			this.children[1] = new Block(this.level+1, this.maxDepth);
			this.children[2] = new Block(this.level+1, this.maxDepth);
			this.children[3] = new Block(this.level+1, this.maxDepth);
			this.updateSizeAndPosition(this.size, this.xCoord, this.yCoord);
			return true;
		}
		return false;
	}
 
 
	/*
	 * Return a two-dimensional array representing this Block as rows and columns of unit cells.
	 * 
	 * Return and array arr where, arr[i] represents the unit cells in row i, 
	 * arr[i][j] is the color of unit cell in row i and column j.
	 * 
	 * arr[0][0] is the color of the unit cell in the upper left corner of this Block.
	 */
	public Color[][] flatten() {
		int unit = (int) (Math.pow(2, this.maxDepth));
		Color[][] color = new Color[unit][unit];
		this.flattenHelper(0, 0, unit, color);
		return color;
	}

	private void flattenHelper(int x, int y, int size, Color[][] color) {
		if (this.children.length==0) {
			for (int i = y; i < y + size; i++) {
				for (int j = x; j < x + size; j++) {
					color[i][j] = this.color;
				}
			}
		} else {
			int childSize = size / 2;
			this.children[0].flattenHelper(x+childSize, y, childSize, color);
			this.children[1].flattenHelper(x, y, childSize, color);
			this.children[2].flattenHelper(x, y+childSize, childSize, color);
			this.children[3].flattenHelper(x + childSize, y + childSize, childSize, color);
		}
	}
 
 
	// These two get methods have been provided. Do NOT modify them. 
	public int getMaxDepth() {
		return this.maxDepth;
	}
 
	public int getLevel() {
		return this.level;
	}


	/*
	 * The next 5 methods are needed to get a text representation of a block. 
	 * You can use them for debugging. You can modify these methods if you wish.
	 */
	public String toString() {
		return String.format("pos=(%d,%d), size=%d, level=%d", this.xCoord, this.yCoord, this.size, this.level);
	}

	public void printBlock() {
		this.printBlockIndented(0);
	}

	private void printBlockIndented(int indentation) {
		String indent = "";
		for (int i=0; i<indentation; i++) {
			indent += "\t";
		}

		if (this.children.length == 0) {
			// it's a leaf. Print the color!
			String colorInfo = GameColors.colorToString(this.color) + ", ";
			System.out.println(indent + colorInfo + this);   
		} 
		else {
			System.out.println(indent + this);
			for (Block b : this.children)
				b.printBlockIndented(indentation + 1);
		}
	}
 
	private static void coloredPrint(String message, Color color) {
		System.out.print(GameColors.colorToANSIColor(color));
		System.out.print(message);
		System.out.print(GameColors.colorToANSIColor(Color.WHITE));
	}

	public void printColoredBlock(){
		Color[][] colorArray = this.flatten();
		for (Color[] colors : colorArray) {
			for (Color value : colors) {
				String colorName = GameColors.colorToString(value).toUpperCase();
				if(colorName.length() == 0){
					colorName = "\u2588";
				}
				else{
					colorName = colorName.substring(0, 1);
				}
				coloredPrint(colorName, value);
			}
			System.out.println();
		}
	}
}

