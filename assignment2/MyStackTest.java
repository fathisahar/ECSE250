package assignment2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import assignment2.*;
import org.junit.jupiter.api.Test;

public class MyStackTest {
    @Test
    public void MyStack_constructor_instantiates(){
        MyStack<Integer> myStack = new MyStack<>();
    }

    @Test
    public void MyStack_getSize_0Returns0(){
        MyStack<Integer> myStack = new MyStack<>();
        assertEquals(0, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_pushWhen0MakesSize1(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        assertEquals(1, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_pushWhen1MakesSize2(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        assertEquals(2, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_pushWhen2MakesSize3(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        assertEquals(3, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_popWhen3MakesSize2(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        myStack.pop();
        assertEquals(2, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_popWhen2MakesSize1(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        myStack.pop();
        assertEquals(1, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_popWhen1MakesSize0(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        myStack.pop();
        assertEquals(1, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_clearMakesSize0(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.clear();
        assertEquals(0, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_peekWhen1MakesSize1(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.peek();
        assertEquals(1, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_peekWhen2MakesSize2(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(0);
        myStack.peek();
        assertEquals(2, myStack.getSize());
    }

    @Test
    public void MyStack_getSize_peekWhen3MakesSize3(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(0);
        myStack.push(0);
        myStack.peek();
        assertEquals(3, myStack.getSize());
    }

    @Test
    public void MyStack_push_returnsTrueWhenSize0(){
        MyStack<Integer> myStack = new MyStack<>();
        assertTrue(myStack.push(0));
    }

    @Test
    public void MyStack_push_returnsTrueWhenSize1(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        assertTrue(myStack.push(0));
    }
    @Test
    public void MyStack_push_returnsTrueWhenSize2(){
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(2);
        assertTrue(myStack.push(0));
    }

    @Test
    public void MyStack_push_returnsFalseWhenElementIsNull(){
        MyStack<Integer> myStack = new MyStack<>();
        assertFalse(myStack.push(null));
    }

    @Test
    public void MyStack_push_addElementsToEnd(){
        MyStack<Integer> myStack = new MyStack<>();
        Integer myInt = 2;
        myStack.push(0);
        myStack.push(1);
        myStack.push(myInt);
        assertEquals(myInt, myStack.peek());
    }


    @Test
    public void MyStack_pop_returnsLastElementWhenSize3(){
        MyStack<Integer> myStack = new MyStack<>();
        Integer myInteger = 2;
        myStack.push(0);
        myStack.push(1);
        myStack.push(myInteger);
        assertEquals(myInteger, myStack.pop());
    }

    @Test
    public void MyStack_pop_returnsLastElementWhenSize2(){
        MyStack<Integer> myStack = new MyStack<>();
        Integer myInteger = 2;
        myStack.push(0);
        myStack.push(myInteger);
        assertEquals(myInteger, myStack.pop());
    }

    @Test
    public void MyStack_pop_returnsLastElementWhenSize1(){
        MyStack<Integer> myStack = new MyStack<>();
        Integer myInteger = 2;
        myStack.push(myInteger);
        assertEquals(myInteger, myStack.pop());
    }

    @Test
    public void MyStack_pop_throwsNoSuchElementExceptionWhenSize0() {
        MyStack<Integer> myStack = new MyStack<>();
        assertThrows(NoSuchElementException.class, myStack::pop);
    }

    @Test
    public void MyStack_clear_thenPeekThrowsNoSuchElementException() {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(0);
        myStack.push(0);
        myStack.push(0);
        myStack.clear();
        assertThrows(NoSuchElementException.class, myStack::peek);
    }

    @Test
    public void MyStack_isEmpty_returnsTrueWhenSize0() {
        MyStack<Integer> myStack = new MyStack<>();
        assertTrue(myStack.isEmpty());
    }

    @Test
    public void MyStack_isEmpty_returnsFalseWhenSize1() {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(2454);
        assertFalse(myStack.isEmpty());
    }

    @Test
    public void MyStack_peek_returnsLastObject() {
        MyStack<Integer> myStack = new MyStack<>();
        Integer myInteger = 2454;
        myStack.push(0);
        myStack.push(1);
        myStack.push(myInteger);
        assertEquals(myInteger, myStack.peek());
    }
}
