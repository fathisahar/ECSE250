package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import assignment2.*;
import org.junit.jupiter.api.Test;

public class MyQueueTest {
    @Test
    public void MyQueue_constructor_instantiates(){
        MyQueue<Integer> myQueue = new MyQueue<>();
    }

    @Test
    public void MyQueue_isEmpty_returnsTrueWhenEmpty(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        assertTrue(myQueue.isEmpty());
    }

    @Test
    public void MyQueue_isEmpty_returnsFalseWhenNotEmpty(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(1234);
        assertFalse(myQueue.isEmpty());
    }

    // I implemented a getSize to make my life easier
    @Test
    public void MyQueue_getSize_returns0WhenQueueEmpty(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        assertEquals(0, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_clearMakesSize0(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        myQueue.enqueue(1);
        myQueue.enqueue(1);
        myQueue.enqueue(1);
        myQueue.clear();
        assertEquals(0, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_enqueueWhen0Returns1(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        assertEquals(1, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_enqueueWhen1Returns2(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        myQueue.enqueue(1);
        assertEquals(2, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_enqueueWhen2Returns3(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        assertEquals(3, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_dequeueWhen3Returns2(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        myQueue.enqueue(1);
        myQueue.enqueue(2);

        myQueue.dequeue();

        assertEquals(2, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_dequeueWhen2Returns1(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);
        myQueue.enqueue(1);

        myQueue.dequeue();

        assertEquals(1, myQueue.getSize());
    }

    @Test
    public void MyQueue_getSize_dequeueWhen1Returns0(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(0);

        myQueue.dequeue();

        assertEquals(0, myQueue.getSize());
    }

    @Test
    public void MyQueue_enqueue_returnsTrueWhenSuccess(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        assertTrue(myQueue.enqueue(21657));
    }

    @Test
    public void MyQueue_enqueue_returnsFalseWhenElementIsNull(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        assertFalse(myQueue.enqueue(null));
    }

    @Test
    public void MyQueue_dequeue_returnsFirstIn(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        Integer myInt = 42;
        myQueue.enqueue(myInt);
        myQueue.enqueue(1);
        myQueue.enqueue(2);

        assertEquals(myInt, myQueue.dequeue());
    }

    @Test
    public void MyQueue_dequeue_throwsNoSuchElementExceptionWhenEmpty(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        assertThrows(NoSuchElementException.class, myQueue::dequeue);
    }

    @Test
    public void MyQueue_clear_makesIsEmptyReturnTrue(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(1);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.clear();
        assertTrue(myQueue.isEmpty());
    }

    @Test
    public void MyQueue_clear_MakesQueueEmpty(){
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(1);
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.clear();
        assertThrows(NoSuchElementException.class, myQueue::dequeue);
    }

    @Test
    public void MyQueue_equals_returnsTrueWhenQueuesEqual(){
        MyQueue<Integer> myQueue1 = new MyQueue<>();
        myQueue1.enqueue(1);
        myQueue1.enqueue(2);
        myQueue1.enqueue(3);

        MyQueue<Integer> myQueue2 = new MyQueue<>();
        myQueue2.enqueue(1);
        myQueue2.enqueue(2);
        myQueue2.enqueue(3); //should be 1, 2, 3

        assertEquals(myQueue1, myQueue2);
    }

    @Test
    public void MyQueue_equals_returnsFalseWhenQueuesNotEqual(){
        MyQueue<Integer> myQueue1 = new MyQueue<>();
        myQueue1.enqueue(1);
        myQueue1.enqueue(2);
        myQueue1.enqueue(3);

        MyQueue<Integer> myQueue2 = new MyQueue<>();
        myQueue2.enqueue(2345);
        myQueue2.enqueue(1);
        myQueue2.enqueue(2);
        myQueue2.enqueue(3); //should be 2345, 1, 2, 3

        assertNotEquals(myQueue1, myQueue2);
    }

}
