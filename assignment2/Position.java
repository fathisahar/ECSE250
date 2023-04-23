package assignment2;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("You cannot have a negative coordinate.");
        } else if (x > Integer.MAX_VALUE || y > Integer.MAX_VALUE){
            throw new IllegalArgumentException("You cannot have larger than the int. max.");
        }
        this.x = x;
        this.y = y;
    }

    public Position(Position position){
        x = position.x;
        y = position.y;
    }

    public void reset(int x, int y){
        if (x < 0 || y < 0) {
            throw new IllegalStateException("You cannot have a negative coordinate.");
        }
        this.x = x;
        this.y = y;
    }

    public void reset(Position position){
        x = position.x;
        y = position.y;
    }

    public int getDistance(Position position){
        return Math.abs(position.x - x) + Math.abs(position.y - y);
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void moveWest(){
        if (x-1 < 0) {
            throw new IllegalStateException("You cannot have a negative coordinate.");
        }
        this.x = x-1;
    }

    public void moveEast(){
        if (x == Integer.MAX_VALUE ){
            throw new IllegalStateException("You cannot have larger than the int. max.");
        }
        this.x = x+1;
    }
    public void moveNorth(){
        if (y-1 < 0) {
            throw new IllegalStateException("You cannot have a negative coordinate.");
        }
        this.y = y-1;
    }

    public void moveSouth(){
        if (y == Integer.MAX_VALUE ){
            throw new IllegalStateException("You cannot have larger than the int. max.");
        }
        this.y = y+1;
    }

    public boolean equals(Object obj){
        if (obj instanceof Position) {
            Position objPos = (Position) obj;
            if (objPos.x == this.x && objPos.y == this.y){
                return true;
            }
        }
        return false;
    }


}