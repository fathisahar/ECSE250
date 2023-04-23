package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;

import java.lang.annotation.Target;
import java.util.NoSuchElementException;

import java.lang.reflect.Field;

import assignment2.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

public class TargetQueueTest {
    @Test
    public void TargetQueue_constructor_instantiates(){
        TargetQueue myQueue = new TargetQueue();
    }

    @Test
    public void TargetQueue_clear_makesQueueEmpty(){
        TargetQueue myTargetQueue = new TargetQueue();
        Position myPosition = new Position(4, 5);
        myTargetQueue.enqueue(myPosition);
        myTargetQueue.clear();
        assertTrue(myTargetQueue.isEmpty());
    }

    @Test
    public void TargetQueue_clear_makesStackEmpty(){
        try {
            MyStack<String> dummyStack = new MyStack<>();
            dummyStack.push("hello");
            assertFalse(dummyStack.isEmpty());

            TargetQueue myTargetQueue = new TargetQueue();
            Field privateCurrentPosition = TargetQueue.class.getDeclaredField("currentPosition");
            privateCurrentPosition.setAccessible(true);
            privateCurrentPosition.set(myTargetQueue, dummyStack);

            //@SuppressWarnings("unchecked") //we know that the last thing we put in is a MyStack<String>.
            MyStack<String> stackCopy = (MyStack<String>)privateCurrentPosition.get(myTargetQueue);
            assertEquals(stackCopy, dummyStack);
            assertFalse(stackCopy.isEmpty());

            myTargetQueue.clear();

            //can't add a suppressWarnings here because of Junit I guess (after asserts)
            stackCopy = (MyStack<String>)privateCurrentPosition.get(myTargetQueue);
            assertTrue(stackCopy.isEmpty());
        }
        catch(Exception e){
            System.out.println("no field named num... this test cannot work :(");
        }

    }

    @Test
    public void TargetQueue_clear_makesNumEmpty(){
        try {
            String dummyString = "836548";
            TargetQueue myTargetQueue = new TargetQueue();

            Field privateNum = TargetQueue.class.getDeclaredField("num");
            privateNum.setAccessible(true);
            privateNum.set(myTargetQueue, dummyString);

            String numCopy = (String)privateNum.get(myTargetQueue);
            assertEquals(numCopy, dummyString);
            assertTrue(numCopy.length() > 0);

            myTargetQueue.clear();
            numCopy = (String)privateNum.get(myTargetQueue);
            assertEquals(0, numCopy.length());
        }
        catch(Exception e){
            System.out.println("no field named num... this test cannot work");
        }
    }


    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: invalid inputs badly placed (")
    @ValueSource(strings = {"((108,72).(34,45)",
            "(1(08,72).(34,45)",
            "(108(,72).(34,45)",
            "(108,(72).(34,45)",
            "(108,7(2).(34,45)",
            "(108,72().(34,45)",
            "(108,72).((34,45)",
            "(108,72).(3(4,45)",
            "(108,72).(34,(45)",
            "(108,72).(34,4(5)",
            "(108,72).(34,45()",
            "(108,72).(34,45)("})
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenMisplacedParenthesis(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: invalid inputs badly placed num")
    @ValueSource(strings = {"42(108,72).(34,45)",
            "(108,72)42.(34,45)",
            "(108,72).42(34,45)",
            "(108,72).(34,45)42"
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenMisplacedNumber(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: invalid inputs badly placed num")
    @ValueSource(strings = {"(2147483648,72).(34,45)",
            "(34,2147483648).(34,45)",
            "(34,72).(2147483648,45)",
            "(34,72).(34,2147483648)"
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenIntOverflow(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: invalid inputs badly placed period")
    @ValueSource(strings = { "(.108,72).(34,45)",
            "(1.08,72).(34,45)",
            "(108.,72).(34,45)",
            "(108,.72).(34,45)",
            "(108,7.2).(34,45)",
            "(108,72.).(34,45)",
            "(108,72)..(34,45)",
            "(108,72).(.34,45)",
            "(108,72).(3.4,45)",
            "(108,72).(34.,45)",
            "(108,72).(34,.45)",
            "(108,72).(34,4.5)",
            "(108,72).(34,45.)"
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenMisplacedPeriod(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: invalid inputs badly placed right parenthesis")
    @ValueSource(strings = {")(108,72).(34,45)",
            "()108,72).(34,45)",
            "(1)08,72).(34,45)",
            "(108),72).(34,45)",
            "(108,)72).(34,45)",
            "(108,7)2).(34,45)",
            "(108,72)).(34,45)",
            "(108,72).)(34,45)",
            "(108,72).()34,45)",
            "(108,72).(3)4,45)",
            "(108,72).(34),45)",
            "(108,72).(34,)45)",
            "(108,72).(34,4)5)",
            "(108,72).(34,45))",
            "(108,72).(34,45))"
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenMisplacedRightParenthesis(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }


    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: extra number")
    @ValueSource(strings = {"(108,72,72).(34,45)",
            "(108,72).(34,72,45)"
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenExtraNumber(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: missing parts")
    @ValueSource(strings = {"108,72).(34,45)",
            "",
            "(,72).(34,45)",
            "(10872).(34,45)",
            "(108,).(34,45)",
            "(108,72.(34,45)",
            "(108,72)(34,45)",
            "(108,72).34,45)",
            "(108,72).(,45)",
            "(108,72).(3445)",
            "(108,72).(34,)",
            "(108,72).(34,45",
            "(72).(34,45)",
            "().(34,45)",
            "(108,72).()",
            "(108,72).(34)",
            "108,72.(34,45)",
            "(108,72).34,45",
            "().()",
            "(,).(,)",
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenMissingParts(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @ParameterizedTest
    @Tag("TargetQueueTest") @DisplayName("TargetQueue: wrong characters")
    @ValueSource(strings = {"[108,72].(34,45)",
            "(108.72).(34,45)",
            "(-108,72).(34,45)",
            "(108,-72).(34,45)",
            "(108,72).(-34,45)",
            "(108,72).(34,-45)",
            "(108-,72).(34,45)",
            "(108,72),(34,45)",
            "(108,72).43(34,45)",
            "(108,72)27(34,45)",
            "(108,72)34.43(34,45)",
            "(a,72).(34,45)",
            "(ae,72).(34,45)",
            "(10d8,72).(34,45)",
            "(108,72).(a,45)",
            "(108,72).(3a4,45)",
    })
    void TargetQueue_addTargets_throwsIllegalArgumentExceptionWhenInvalidCharacters(String input) {
        TargetQueue myTargetQueue = new TargetQueue();
        assertThrows(IllegalArgumentException.class, ()-> myTargetQueue.addTargets(input));
    }

    @Test
    void TargetQueue_addTargets_buildsValidQueueCorrectly() {
        TargetQueue myTargetQueue1 = new TargetQueue();
        TargetQueue myTargetQueue2 = new TargetQueue();
        TargetQueue myTargetQueue3 = new TargetQueue();
        MyQueue<Position> expectedQueue = new MyQueue<>();

        expectedQueue.enqueue(new Position(0, 0));
        expectedQueue.enqueue(new Position(1, 4));
        expectedQueue.enqueue(new Position(3489, 832));

        myTargetQueue1.addTargets("(0,0).(1,4).(3489,832)");
        myTargetQueue2.addTargets(".(0,0).(1,4).(3489,832)");
        myTargetQueue3.addTargets("(0,0).(1,4).(3489,832).");


        assertEquals(expectedQueue, myTargetQueue1);
    }

    /*
                "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
                        "(108,72).(34,45)",
    */




}
