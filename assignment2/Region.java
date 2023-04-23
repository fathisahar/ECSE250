package assignment2;

public class Region {
    private int minx;
    private int miny;
    private int maxx;
    private int maxy;

    public Region(int minx, int miny, int maxx, int maxy){
        if (minx < 0 || miny < 0 || maxx < 0 || maxy < 0){
            throw new IllegalArgumentException();
        }
        if (maxx < minx || maxy < miny){
            throw new IllegalArgumentException();
        }

        if (maxx == minx || maxy == miny){
            throw new IllegalArgumentException();
        }
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
    }

    public boolean contains(Position position){
        if (position.getX() >= minx && position.getX() <= maxx && position.getY() >= miny && position.getY() <= maxy){
            return true;
        }
        return false;
    }



}
