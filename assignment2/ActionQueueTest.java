package assignment2Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.NoSuchElementException;

import java.lang.reflect.Field;
import java.util.stream.Stream;

import assignment2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

public class ActionQueueTest {
    @Test
    public void ActionQueue_constructor_instantiates(){
        ActionQueue myQueue = new ActionQueue();
    }

    @Test
    public void ActionQueue_clear_makesQueueEmpty(){
        ActionQueue myActionQueue = new ActionQueue();
        Direction myDirection = Direction.EAST;
        myActionQueue.enqueue(myDirection);
        myActionQueue.clear();
        assertTrue(myActionQueue.isEmpty());
    }

    @Test
    public void TargetQueue_clear_makesStackEmpty(){
        try {
            MyStack<String> dummyStack = new MyStack<>();
            dummyStack.push("hello");
            assertFalse(dummyStack.isEmpty());

            ActionQueue myActionQueue = new ActionQueue();
            Field privateCurrentDirection = ActionQueue.class.getDeclaredField("currentDirection");
            privateCurrentDirection.setAccessible(true);
            privateCurrentDirection.set(myActionQueue, dummyStack);

            //@SuppressWarnings("unchecked") //we know that the last thing we put in is a MyStack<String>.
            MyStack<String> stackCopy = (MyStack<String>)privateCurrentDirection.get(myActionQueue);
            assertEquals(stackCopy, dummyStack);
            assertFalse(stackCopy.isEmpty());

            myActionQueue.clear();

            //can't add a suppressWarnings here because of Junit I guess (after asserts)
            stackCopy = (MyStack<String>)privateCurrentDirection.get(myActionQueue);
            assertTrue(stackCopy.isEmpty());
        }
        catch(Exception e){
            System.out.println("no field named num... this test cannot work :(");
        }

    }

    @Test
    public void ActionQueue_loadFromEncodedString_correctlyLoadsBasicStrings1(){
        ActionQueue myActionQueue = new ActionQueue();
        MyQueue<Direction> expectedQueue = new MyQueue<>();

        expectedQueue.enqueue(Direction.WEST);
        expectedQueue.enqueue(Direction.NORTH);
        expectedQueue.enqueue(Direction.EAST);
        expectedQueue.enqueue(Direction.SOUTH);
        expectedQueue.enqueue(Direction.WEST);
        expectedQueue.enqueue(Direction.WEST);
        expectedQueue.enqueue(Direction.NORTH);
        expectedQueue.enqueue(Direction.SOUTH);

        myActionQueue.loadFromEncodedString("WNESWWNS");

        assertEquals(expectedQueue, myActionQueue);
    }
    @Test
    public void ActionQueue_loadFromEncodedString_correctlyLoadsBasicStrings2(){
        ActionQueue myActionQueue = new ActionQueue();
        MyQueue<Direction> expectedQueue = new MyQueue<>();

        expectedQueue.enqueue(Direction.WEST);

        myActionQueue.loadFromEncodedString("W");

        assertEquals(expectedQueue, myActionQueue);
    }

    @ParameterizedTest
    @Tag("ActionQueueTest") @DisplayName("ActionQueue: valid inputs")
    @CsvSource(value = {
            "NNE, NNE",
            "3[N], NNN",
            "2[NE], NENE",
            "2[ES]3[WN], ESESWNWNWN",
            "S2[ES]3[WN]E, SESESWNWNWNE",
            "N3[S2[ES]3[WN]E], NSESESWNWNWNESESESWNWNWNESESESWNWNWNE",
            "2[3[2[NE]]W], NENENENENENEWNENENENENENEW",
            "17[E], EEEEEEEEEEEEEEEEE",
            "100[W], WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW",
            "10[EW], EWEWEWEWEWEWEWEWEWEW",
            "10[2[E]], EEEEEEEEEEEEEEEEEEEE"
    }, ignoreLeadingAndTrailingWhitespace = true)
    void ActionQueue_loadFromEncodedString_correctlyLoadsComplexStrings(String encodedString, String simplifiedString) {
        //at this point we know that the code can correctly parse simple strings (above tests)
        ActionQueue actionQueue1 = new ActionQueue();
        ActionQueue actionQueue2 = new ActionQueue();

        actionQueue1.loadFromEncodedString(encodedString);
        actionQueue2.loadFromEncodedString(simplifiedString);

        assertEquals(actionQueue1, actionQueue2);
    }

    @ParameterizedTest
    @Tag("ActionQueueTest") @DisplayName("ActionQueue: empty parenthesis")
    @ValueSource(strings = {"3[]",
            "",
            "2[SE]3[]",
            "S2[]N",
            "S3[N4[]S]E",
            "N3[S2[]3[WN]E]",
            "N3[S2[ES]3[]E]",
            "N3[]"
    })
    void ActionQueue_loadFromEncodedString_throwsIllegalArgumentExceptionWhenEmptyParenthesis(String input) {
        ActionQueue myActionQueue = new ActionQueue();
        assertThrows(IllegalArgumentException.class, ()-> myActionQueue.loadFromEncodedString(input));
    }


    @ParameterizedTest
    @Tag("ActionQueueTest") @DisplayName("ActionQueue: mismatched parenthesis")
    @ValueSource(strings = {"3[N",
            "3N]",
            "3[3[N]",
            "SNE]NS",
            "SNE[NS",
            "SNE[3][N]]",
            "N3S2[ES]3[WN]E]",
            "N3[S2ES]3[WN]E]",
            "N3[S2[ES3[WN]E]",
            "N3[S2[ES]3WN]E]",
            "N3[S2[ES]3[WNE]",
            "N3[S2[ES]3[WN]E"
    })
    void ActionQueue_loadFromEncodedString_throwsIllegalArgumentExceptionWhenMismatchedParenthesis(String input) {
        ActionQueue myActionQueue = new ActionQueue();
        assertThrows(IllegalArgumentException.class, ()-> myActionQueue.loadFromEncodedString(input));
    }

    @ParameterizedTest
    @Tag("ActionQueueTest") @DisplayName("ActionQueue: Invalid number")
    @ValueSource(strings = {"N-3[S2[ES]3[WN]E]",
            "N0[S2[ES]3[WN]E]",
            "N3[S0[ES]3[WN]E]",
            "N3[S2[ES]0[WN]E]",
            "N[S2[ES]3[WN]E]",
            "N3[S[ES]3[WN]E]",
            "N3[S2[ES][WN]E]",
            "N3[3S2[ES]3[WN]E]",
            "N3[S2[3ES]3[WN]E]",
            "N3[S2[E3S]3[WN]E]",
            "N3[S2[ES3]3[WN]E]",
            "N3[S2[ES]3[3WN]E]",
            "N3[S2[ES]3[W3N]E]",
            "N3[S2[ES]3[WN3]E]",
            "N3[S2[ES]3[WN]3E]",
            "N3[S2[ES]3[WN]E3]",
            "2147483648[N]",
            "N3[S2[ES]3[WN]E]3"
    })
    void ActionQueue_loadFromEncodedString_throwsIllegalArgumentExceptionWhenInvalidNumber(String input) {
        ActionQueue myActionQueue = new ActionQueue();
        assertThrows(IllegalArgumentException.class, ()-> myActionQueue.loadFromEncodedString(input));
    }

    @ParameterizedTest
    @Tag("ActionQueueTest") @DisplayName("ActionQueue: Invalid direction")
    @ValueSource(strings = {"n3[S2[ES]3[WN]E]",
            "N3[s2[ES]3[WN]E]",
            "N3[S2[eS]3[WN]E]",
            "N3[S2[Es]3[WN]E]",
            "N3[S2[ES]3[wN]E]",
            "N3[S2[ES]3[Wn]E]",
            "N3[S2[ES]3[WN]e]",
            "NORTH3[S2[ES]3[WN]E]",
            "N3[S2[32]3[WN]E]",
            "N3[S2[E2]3[WN]E]",
            "At the next intersection, turn right. Your destination will be on your left.",
            "N3[S2[E2S]3[WN]E]",
            "N3[S2[E-S]3[WN]E]",
            "N3[S2[E{S]3[WN]E]",
            "N3[S2[E~S]3[WN]E]",
            "N3[S2[E#S]3[WN]E]",
            "N3[S2S2[ES]3[WN]E]"
    })
    void ActionQueue_loadFromEncodedString_throwsIllegalArgumentExceptionWhenInvalidDirection(String input) {
        ActionQueue myActionQueue = new ActionQueue();
        assertThrows(IllegalArgumentException.class, ()-> myActionQueue.loadFromEncodedString(input));
    }


}
