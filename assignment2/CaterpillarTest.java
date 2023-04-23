package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import assignment2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class CaterpillarTest {
    @Test
    public void Caterpillar_constructor_instantiates(){
        Caterpillar myCaterpillar = new Caterpillar();
    }

    @Test
    public void Caterpillar_constructor_initialPositionIs7_7(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position centerPosition = new Position(7, 7);
        assertEquals(centerPosition, myCaterpillar.peekFirst());
    }

    @Test
    public void Caterpillar_constructor_initialSizeIs1(){
        Caterpillar myCaterpillar = new Caterpillar();
        assertEquals(1, myCaterpillar.getSize());
    }

    @Test
    public void Caterpillar_getHead_whenNotMovedReturnsInitialPosition(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position centerPosition = new Position(7, 7);
        assertEquals(centerPosition, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_getHead_returnsHeadWhenNotOnOriginalPosition(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position food = new Position(7, 6);
        myCaterpillar.eat(food);
        assertEquals(food, myCaterpillar.peekFirst());
        assertEquals(food, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_eat_movesHeadToAdjacentPositionNorth(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position food = new Position(7, 6);
        myCaterpillar.eat(food);
        assertEquals(food, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_eat_movesHeadToAdjacentPositionSouth(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position food = new Position(7, 8);
        myCaterpillar.eat(food);
        assertEquals(food, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_eat_movesHeadToAdjacentPositionEast(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position food = new Position(8, 7);
        myCaterpillar.eat(food);
        assertEquals(food, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_eat_movesHeadToAdjacentPositionWest(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position food = new Position(6, 7);
        myCaterpillar.eat(food);
        assertEquals(food, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_eat_doesntMoveTail(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position firstTail = myCaterpillar.peekLast();
        Position food = new Position(6, 7);
        myCaterpillar.eat(food);
        assertEquals(firstTail, myCaterpillar.peekLast());
    }

    @Test
    public void Caterpillar_eat_throwsIllegalArgumentExceptionWhenSelfCollide(){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.eat(new Position(6, 7));
        myCaterpillar.eat(new Position(6, 6));
        myCaterpillar.eat(new Position(7, 6)); //it's doing a little circle (square, whatever)
        assertThrows(IllegalArgumentException.class, ()->{
            myCaterpillar.eat(new Position(7, 7));
        });
    }

    @ParameterizedTest
    @ArgumentsSource(NonAdjacentPosition.class)
    public void Caterpillar_eat_nonAdjacentPositionThrowsIllegalArgumentException(Position nonAdjacentPosition){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.eat(new Position(6, 7));
        myCaterpillar.eat(new Position(5, 7));
        myCaterpillar.eat(new Position(4, 7)); //adjacent: (3, 7), (5, 7), (4, 6), (4, 8)
        assertThrows(IllegalArgumentException.class, ()->{
            myCaterpillar.eat(nonAdjacentPosition);
        });
    }

    @Test
    public void Caterpillar_move_movesHeadToAdjacentPositionNorth(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position newPosition = new Position(7, 6);
        myCaterpillar.move(newPosition);
        assertEquals(newPosition, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_move_movesHeadToAdjacentPositionSouth(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position newPosition = new Position(7, 8);
        myCaterpillar.move(newPosition);
        assertEquals(newPosition, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_move_movesHeadToAdjacentPositionEast(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position newPosition = new Position(8, 7);
        myCaterpillar.move(newPosition);
        assertEquals(newPosition, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_move_movesHeadToAdjacentPositionWest(){
        Caterpillar myCaterpillar = new Caterpillar();
        Position newPosition = new Position(6, 7);
        myCaterpillar.move(newPosition);
        assertEquals(newPosition, myCaterpillar.getHead());
    }

    @Test
    public void Caterpillar_move_movesTailToLastPosition(){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.eat(new Position(6, 7));
        Position nextTail = myCaterpillar.peekFirst(); //since the caterpillar is size 2
        myCaterpillar.move(new Position(6, 6));
        assertEquals(nextTail, myCaterpillar.peekLast());
    }

    @Test
    public void Caterpillar_move_throwsIllegalArgumentExceptionWhenSelfCollide(){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.eat(new Position(6, 7));
        myCaterpillar.eat(new Position(6, 6));
        myCaterpillar.eat(new Position(7, 6)); //it's doing a little circle (square, whatever)
        assertThrows(IllegalArgumentException.class, ()->{
            myCaterpillar.move(new Position(7, 7));
        });
    }


    @ParameterizedTest
    @ArgumentsSource(NonAdjacentPosition.class)
    public void Caterpillar_move_nonAdjacentPositionThrowsIllegalArgumentException(Position nonAdjacentPosition){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.move(new Position(6, 7));
        myCaterpillar.move(new Position(5, 7));
        myCaterpillar.move(new Position(4, 7)); //adjacent: (3, 7), (5, 7), (4, 6), (4, 8)
        assertThrows(IllegalArgumentException.class, ()->{
            myCaterpillar.move(nonAdjacentPosition);
        });
    }

    @Test
    public void Caterpillar_selfCollide_returnsFalseWhenNotSelfCollide(){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.move(new Position(6, 7));
        assertFalse(myCaterpillar.selfCollision(new Position(7, 7)));
    }

    @Test
    public void Caterpillar_selfCollide_returnsTrueWhenSelfCollide(){
        Caterpillar myCaterpillar = new Caterpillar();
        myCaterpillar.eat(new Position(6, 7));
        myCaterpillar.eat(new Position(6, 6));
        myCaterpillar.eat(new Position(7, 6)); //it's doing a little circle (square, whatever)
        assertTrue(myCaterpillar.selfCollision(new Position(7, 7)));
        assertTrue(myCaterpillar.selfCollision(new Position(6, 7)));
        assertTrue(myCaterpillar.selfCollision(new Position(6, 6)));
        assertTrue(myCaterpillar.selfCollision(new Position(7, 6)));
    }

    static class NonAdjacentPosition implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    //assumes head position of (4, 7)
                    Arguments.of(new Position(3, 6)),
                    Arguments.of(new Position(5, 6)),
                    Arguments.of(new Position(3, 8)),
                    Arguments.of(new Position(5, 8)),
                    Arguments.of(new Position(2, 7))
            );
        }
    }


}
