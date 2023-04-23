package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import assignment2.*;
import org.junit.jupiter.api.Test;

public class PositionTest {
    @Test
    public void Position_constructorCoordinates_instantiates(){
        Position myPosition = new Position(0, 0);
    }

    @Test
    public void Position_constructorCoordinates_changesXYCoordinates(){
        int x = 17;
        int y = 1234;
        Position myPosition = new Position(x, y);
        assertEquals(x, myPosition.getX());
        assertEquals(y, myPosition.getY());
    }

    @Test
    public void Position_constructorCoordinates_negativeXThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->
        {
            Position myPosition = new Position(-1, 0);
        });
    }

    @Test
    public void Position_constructorCoordinates_negativeYThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->
        {
            Position myPosition = new Position(0, -1);
        });
    }

    @Test
    public void Position_constructorPosition_changesXYCoordinates(){
        int x = 17;
        int y = 1234;
        Position myPosition1 = new Position(x, y);
        Position myPosition2 = new Position(myPosition1);
        assertEquals(x, myPosition2.getX());
        assertEquals(y, myPosition2.getY());
    }

    @Test
    public void Position_resetCoordinates_changesXYCoordinates(){
        int x = 17;
        int y = 1234;
        Position myPosition = new Position(0, 0);
        myPosition.reset(x, y);
        assertEquals(x, myPosition.getX());
        assertEquals(y, myPosition.getY());
    }

    @Test
    public void Position_resetPosition_changesXYCoordinates(){
        int x = 17;
        int y = 1234;
        Position myPosition1 = new Position(0, 0);
        Position myPosition2 = new Position(x, y);

        myPosition1.reset(myPosition2);
        assertEquals(x, myPosition1.getX());
        assertEquals(y, myPosition1.getY());
    }

    @Test
    public void Position_getDistance_returnsCorrectDistance(){
        Position myPosition1 = new Position(292, 64293);
        Position myPosition2 = new Position(8083, 5297);
        long expectedPosition = 66787;
        assertEquals(expectedPosition, (long)myPosition1.getDistance(myPosition2));
    }

    @Test
    public void Position_getDistance_returnsSameDistanceBothWays(){
        Position myPosition1 = new Position(956478, 64293);
        Position myPosition2 = new Position(58512, 56459954);
        assertEquals(myPosition2.getDistance(myPosition1), myPosition1.getDistance(myPosition2));
    }


    @Test
    public void Position_moveWest_decrementsXCoordBy1(){
        int myXCoord = 12345;
        Position myPosition = new Position(myXCoord, 258);
        myPosition.moveWest();
        assertEquals(myXCoord-1, myPosition.getX());
    }

    @Test
    public void Position_moveWest_throwsIllegalStateExceptionWhenXCoordIs0(){
        Position myPosition = new Position(0, 258);
        assertThrows(IllegalStateException.class, myPosition::moveWest);
    }

    @Test
    public void Position_moveEast_incrementsXCoordBy1(){
        int myXCoord = 897123;
        Position myPosition = new Position(myXCoord, 654);
        myPosition.moveEast();
        assertEquals(myXCoord+1, myPosition.getX());
    }

    @Test
    public void Position_moveEast_throwsIllegalStateExceptionWhenXCoordIsIntegerMAX_VALUE(){
        Position myPosition = new Position(Integer.MAX_VALUE, 789);
        assertThrows(IllegalStateException.class, myPosition::moveEast);
    }

    @Test
    public void Position_moveNorth_decrementsYCoordBy1(){
        int myYCoord = 39643;
        Position myPosition = new Position(147, myYCoord);
        myPosition.moveNorth();
        assertEquals(myYCoord-1, myPosition.getY());
    }

    @Test
    public void Position_moveNorth_throwsIllegalStateExceptionWhenYCoordIs0(){
        Position myPosition = new Position(519, 0);
        assertThrows(IllegalStateException.class, myPosition::moveNorth);
    }

    @Test
    public void Position_moveSouth_incrementsYCoordBy1(){
        int myYCoord = 375489;
        Position myPosition = new Position(125, myYCoord);
        myPosition.moveSouth();
        assertEquals(myYCoord+1, myPosition.getY());
    }

    @Test
    public void Position_moveSouth_throwsIllegalStateExceptionWhenYCoordIsIntegerMAX_VALUE(){
        Position myPosition = new Position(519, Integer.MAX_VALUE);
        assertThrows(IllegalStateException.class, myPosition::moveSouth);
    }


    @Test
    public void Position_equals_diffObjectTypesNotEqual(){
        Position myPosition = new Position(519, Integer.MAX_VALUE);
        Integer myInt = 23;
        assertNotEquals(myInt, myPosition);
    }

    @Test
    public void Position_equals_returnsFalseWhenDiffXDiffY(){
        Position myPosition1 = new Position(51, 51);
        Position myPosition2 = new Position(43, 43);
        assertNotEquals(myPosition1, myPosition2);
    }

    @Test
    public void Position_equals_returnsFalseWhenSameXDiffY(){
        int sameX = 542;
        Position myPosition1 = new Position(sameX, 42);
        Position myPosition2 = new Position(sameX, 43);
        assertNotEquals(myPosition1, myPosition2);
    }

    @Test
    public void Position_equals_returnsFalseWhenDiffXSameY(){
        int sameY = 542;
        Position myPosition1 = new Position(35, sameY);
        Position myPosition2 = new Position(36, sameY);
        assertNotEquals(myPosition1, myPosition2);
    }


    @Test
    public void Position_equals_returnsTrueWhenSameXSameY(){
        int sameY = 542;
        int sameX = 425;
        Position myPosition1 = new Position(sameX, sameY);
        Position myPosition2 = new Position(sameX, sameY);
        assertEquals(myPosition1, myPosition2);
    }

}
