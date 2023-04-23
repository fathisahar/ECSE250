package assignment2;


import java.util.Iterator;

public class Caterpillar extends MyDoublyLinkedList<Position> {
    public Caterpillar(){
        super.add(new Position(7,7 ));
    }

    public Position getHead(){
        return this.peekFirst();
    }

    public void eat(Position position){
        int dx = Math.abs(position.getX() - this.getHead().getX());
        int dy = Math.abs(position.getY() - this.getHead().getY());
        if ((((dx <= 1 && position.getY() == this.getHead().getY()) || (position.getX() == this.getHead().getX() && dy <= 1)) && dx + dy != 0) && !this.selfCollision(position)){
            super.addFirst(position);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Position position){
        int dx = Math.abs(position.getX() - this.getHead().getX());
        int dy = Math.abs(position.getY() - this.getHead().getY());
        if ((((dx <= 1 && position.getY() == this.getHead().getY()) || (position.getX() == this.getHead().getX() && dy <= 1)) && dx + dy != 0) && !this.selfCollision(position)){
            super.addFirst(position);
            super.removeLast();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean selfCollision(Position position){
        Iterator<Position> bodyParts = this.iterator();
        for (int i = 0; i < this.getSize(); i++) {
            Position thisBodyPart = bodyParts.next();
            if (thisBodyPart.equals(position)) {
                return true;
            }
        }
        return false;
    }
}
