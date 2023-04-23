package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import assignment2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

public class RegionTest {
    @Test
    public void MyQueue_constructor_instantiates(){
        Region myRegion = new Region(5, 5, 15, 15);
    }

    @Test
    public void MyQueue_constructor_emptyXBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(5, 5, 5, 15);
        });
    }

    @Test
    public void MyQueue_constructor_emptyYBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(5, 5, 15, 5);
        });
    }

    @Test
    public void MyQueue_constructor_emptyBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(5, 5, 5, 5);
        });
    }

    @Test
    public void MyQueue_constructor_negativeXBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(15, 5, 5, 15);
        });
    }

    @Test
    public void MyQueue_constructor_negativeYBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(5, 15, 15, 5);
        });
    }

    @Test
    public void MyQueue_constructor_negativeBoundsThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(15, 15, 5, 5);
        });
    }

    @Test
    public void MyQueue_constructor_negativeMinPosXThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(-5, 5, 15, 15);
        });
    }

    @Test
    public void MyQueue_constructor_negativeMinPosYThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, ()->{
            Region myRegion = new Region(5, -5, 15, 15);
        });
    }


    @ParameterizedTest
    @ArgumentsSource(ValidPositionArgumentProvider.class)
    public void MyQueue_contains_returnsTrueWhenContainsPosition(Position p){
        Region myRegion = new Region(5, 5, 15, 15);
        assertTrue(myRegion.contains(p));
    }

    @ParameterizedTest
    @ArgumentsSource(InvalidPositionArgumentProvider.class)
    public void MyQueue_contains_returnsFalseWhenDoesntContainsPosition(Position p){
        Region myRegion = new Region(5, 5, 15, 15);
        assertFalse(myRegion.contains(p));
    }


    static class ValidPositionArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Position(5, 5)),
                    Arguments.of(new Position(15, 15)),
                    Arguments.of(new Position(5, 15)),
                    Arguments.of(new Position(15, 5)),
                    Arguments.of(new Position(10, 5)),
                    Arguments.of(new Position(15, 10)),
                    Arguments.of(new Position(10, 15)),
                    Arguments.of(new Position(5, 10)),
                    Arguments.of(new Position(10, 10))
            );
        }
    }

    static class InvalidPositionArgumentProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new Position(4, 4)),
                    Arguments.of(new Position(5, 4)),
                    Arguments.of(new Position(10, 4)),
                    Arguments.of(new Position(15, 4)),
                    Arguments.of(new Position(16, 4)),
                    Arguments.of(new Position(4, 5)),
                    Arguments.of(new Position(16, 5)),
                    Arguments.of(new Position(4, 10)),
                    Arguments.of(new Position(16, 10)),
                    Arguments.of(new Position(4, 15)),
                    Arguments.of(new Position(16, 15)),
                    Arguments.of(new Position(4, 16)),
                    Arguments.of(new Position(5, 16)),
                    Arguments.of(new Position(10, 16)),
                    Arguments.of(new Position(15, 16)),
                    Arguments.of(new Position(16, 16))
            );
        }
    }



}
