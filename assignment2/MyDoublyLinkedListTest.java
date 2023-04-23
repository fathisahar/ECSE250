package assignment2;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;

import java.util.NoSuchElementException;

import assignment2.*;
import org.junit.jupiter.api.Test;


class MyDoublyLinkedListTest {
    @Test
    public void MyDoublyLinkedList_constructor_instantiates(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
    }

    @Test
    public void MyDoublyLinkedList_getSize_returns0WhenEmpty(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        assertEquals(0, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_getSize_returns1When1Added(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.add(myInteger);
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_getSize_returns2When2Added(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.add(myInteger);
        myDoublyLinkedList.add(myInteger);
        assertEquals(2, myDoublyLinkedList.getSize());
    }


    // TESTING REMOVING ITEMS FROM LIST

    @Test
    public void MyDoublyLinkedList_remove_size1DecreasesBy1Becomes0(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.remove();
        assertEquals(0, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_remove_size2DecreasesBy1Becomes1(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.remove();
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_remove_size3DecreasesBy1Becomes2(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.remove();
        assertEquals(2, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_remove_lastItemNoLongerPresent(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();

        String myString = "hello world";
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.remove();

        assertNotEquals(myString, myDoublyLinkedList.peekLast());
    }

    @Test
    public void MyDoublyLinkedList_remove_returnsLastElement() {
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString1 = "hello world1";
        String myString2 = "hello world2";
        myDoublyLinkedList.add(myString1);
        myDoublyLinkedList.add(myString2);
        String otherString = myDoublyLinkedList.remove();
        assertEquals(myString2, otherString);
    }

    @Test
    public void MyDoublyLinkedList_remove_throwsNoSuchElementExceptionWhenEmpty() {
        assertThrows(java.util.NoSuchElementException.class, ()->{
            MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
            myDoublyLinkedList.remove();
        });

    }

    @Test
    public void MyDoublyLinkedList_remove_beforeLastNodeNextIsNull() {
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
        myDoublyLinkedList.add("hello");
        myDoublyLinkedList.add("world");
        myDoublyLinkedList.add("this is a string");

        Iterator<String> myForwardIterator = myDoublyLinkedList.iterator(); //curr = list.head = hello
        myForwardIterator.next();                                           //curr = world
        myDoublyLinkedList.remove();
        myForwardIterator.next();                                           //curr = null
        assertThrows(NoSuchElementException.class, myForwardIterator::next);
    }


    @Test
    public void MyDoublyLinkedList_removeFirst_throwsNoSuchElementExceptionWhenEmpty() {
        assertThrows(java.util.NoSuchElementException.class, ()->{
            MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
            myDoublyLinkedList.removeFirst();
        });

    }

    @Test
    public void MyDoublyLinkedList_removeLast_throwsNoSuchElementExceptionWhenEmpty() {
        assertThrows(java.util.NoSuchElementException.class, ()->{
            MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
            myDoublyLinkedList.removeLast();
        });
    }

    @Test
    public void MyDoublyLinkedList_removeFirst_size1DecreasesBy1Becomes0(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.removeFirst();
        assertEquals(0, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeFirst_size2DecreasesBy1Becomes1(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.removeFirst();
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeFirst_size3DecreasesBy1Becomes2(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.removeFirst();
        assertEquals(2, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeFirst_FirstItemNoLongerPresent(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();

        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.removeFirst();

        assertNotEquals(myString, myDoublyLinkedList.peekFirst());
    }

    @Test
    public void MyDoublyLinkedList_removeFirst_FirstNodeNextIsNull() {
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
        myDoublyLinkedList.add("hello");
        myDoublyLinkedList.add("world");
        myDoublyLinkedList.add("this is a string");

        Iterator<String> myForwardIterator = myDoublyLinkedList.iterator(); //curr = list.head, not null
        myDoublyLinkedList.removeFirst();
        myForwardIterator.next();                                                    //curr = null
        assertThrows(NoSuchElementException.class, myForwardIterator::next);
    }


    @Test
    public void MyDoublyLinkedList_removeFirst_makesNodeElementNull() {
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
        myDoublyLinkedList.add("hello");
        myDoublyLinkedList.add("world");
        myDoublyLinkedList.add("this is a string");

        Iterator<String> myForwardIterator = myDoublyLinkedList.iterator();
        myDoublyLinkedList.removeFirst();
        assertNull(myForwardIterator.next());
    }

    @Test
    public void MyDoublyLinkedList_removeLast_size1DecreasesBy1Becomes0(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.removeLast();
        assertEquals(0, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeLast_size2DecreasesBy1Becomes1(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.removeLast();
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeLast_size3DecreasesBy1Becomes2(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.add(myString);

        myDoublyLinkedList.removeLast();
        assertEquals(2, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_removeLast_lastItemNoLongerPresent(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();

        String myString = "hello world";
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.add("notMyString");
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.removeFirst();

        assertNotEquals(myString, myDoublyLinkedList.peekFirst());
    }


    @Test
    public void MyDoublyLinkedList_removeLast_beforeLastNodeNextIsNull() {
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
        myDoublyLinkedList.add("hello");
        myDoublyLinkedList.add("world");
        myDoublyLinkedList.add("this is a string");

        Iterator<String> myForwardIterator = myDoublyLinkedList.iterator(); //curr = list.head = hello
        myForwardIterator.next();                                           //curr = world
        myDoublyLinkedList.removeLast();
        myForwardIterator.next();                                           //curr = null
        assertThrows(NoSuchElementException.class, myForwardIterator::next);
    }

    @Test
    public void MyDoublyLinkedList_isEmpty_returnsTrueWhenEmpty(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        assertTrue(myDoublyLinkedList.isEmpty());
    }

    @Test
    public void MyDoublyLinkedList_isEmpty_returnsFalseWhenNotEmpty(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<>();
        myDoublyLinkedList.add("Fun fact: snakes don't have eyelids");
        assertFalse(myDoublyLinkedList.isEmpty());
    }

    @Test
    public void MyDoublyLinkedList_add_returnsTrueWhenAddToEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();
        Integer myInteger = 5;
        assertTrue(myDoublyLinkedList.add(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_add_returnsTrueWhenAddToNonEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();
        Integer myInteger = 5;
        myDoublyLinkedList.add(myInteger);
        assertTrue(myDoublyLinkedList.add(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_add_returnsFalseWhenAddNull(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();
        Integer myInteger = 5;
        myDoublyLinkedList.add(myInteger);
        assertFalse(myDoublyLinkedList.add(null));
    }

    @Test
    public void MyDoublyLinkedList_add_canTakeString(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        assertTrue(myDoublyLinkedList.add(myString));
    }

    @Test
    public void MyDoublyLinkedList_add_addsAtEndOfList(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add("string1");
        myDoublyLinkedList.add("string2");
        myDoublyLinkedList.add("string3");
        myDoublyLinkedList.add(myString);

        assertEquals(myString, myDoublyLinkedList.peekLast());
    }

    @Test
    public void MyDoublyLinkedList_clear_sizeBecomesZero(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.clear();
        assertEquals(0, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_clear_DLLNowEmpty(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.add(myString);
        myDoublyLinkedList.clear();
        assertTrue(myDoublyLinkedList.isEmpty());
    }


    @Test
    public void MyDoublyLinkedList_addFirst_returnsTrueWhenAddToEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        assertTrue(myDoublyLinkedList.addFirst(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_addFirst_returnsTrueWhenAddToNonEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addFirst(myInteger);
        assertTrue(myDoublyLinkedList.addFirst(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_addFirst_canTakeString(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.addFirst(myString);
        assertTrue(myDoublyLinkedList.addFirst(myString));
    }

    @Test
    public void MyDoublyLinkedList_addFirst_add1AugmentsSizeBy1(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addFirst(myInteger);
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_addFirst_add2AugmentsSizeBy2(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addFirst(myInteger);
        myDoublyLinkedList.addFirst(myInteger);
        assertEquals(2, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_addFirst_returnsFalseWhenAddNull(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();
        Integer myInteger = 5;
        myDoublyLinkedList.addFirst(myInteger);
        assertFalse(myDoublyLinkedList.addFirst(null));
    }

    @Test
    public void MyDoublyLinkedList_addLast_returnsTrueWhenAddToEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        assertTrue(myDoublyLinkedList.addLast(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_addLast_returnsTrueWhenAddToNonEmptyList(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addLast(myInteger);
        assertTrue(myDoublyLinkedList.addLast(myInteger));
    }

    @Test
    public void MyDoublyLinkedList_addLast_canTakeString(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString = "hello world";
        myDoublyLinkedList.addLast(myString);
        assertTrue(myDoublyLinkedList.addLast(myString));
    }

    @Test
    public void MyDoublyLinkedList_addLast_add1AugmentsSizeBy1(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addLast(myInteger);
        assertEquals(1, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_addLast_add2AugmentsSizeBy2(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<Integer>();
        Integer myInteger = 5;
        myDoublyLinkedList.addLast(myInteger);
        myDoublyLinkedList.addLast(myInteger);
        assertEquals(2, myDoublyLinkedList.getSize());
    }

    @Test
    public void MyDoublyLinkedList_addLast_returnsFalseWhenAddNull(){
        MyDoublyLinkedList<Integer> myDoublyLinkedList = new MyDoublyLinkedList<>();
        Integer myInteger = 5;
        myDoublyLinkedList.addLast(myInteger);
        assertFalse(myDoublyLinkedList.addLast(null));
    }

    @Test
    public void MyDoublyLinkedList_peekFirst_size1ListReturnsFirstElement(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString1 = "hello world1";
        myDoublyLinkedList.add(myString1);
        String otherString = myDoublyLinkedList.peekFirst();
        assertEquals(myString1, otherString);
    }

    @Test
    public void MyDoublyLinkedList_peekFirst_size2ListReturnsFirstElement(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString1 = "hello world1";
        String myString2 = "hello world2";
        myDoublyLinkedList.add(myString1);
        myDoublyLinkedList.add(myString2);
        String otherString = myDoublyLinkedList.peekFirst();
        assertEquals(myString1, otherString);
    }

    @Test
    public void MyDoublyLinkedList_peekFirst_throwsNoSuchElementExceptionWhenEmpty(){
        assertThrows(NoSuchElementException.class, ()->{
            MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
            myDoublyLinkedList.peekFirst();
        });
    }

    @Test
    public void MyDoublyLinkedList_peekLast_size1ListReturnsLastElement(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString1 = "hello world1";
        myDoublyLinkedList.add(myString1);
        String otherString = myDoublyLinkedList.peekLast();
        assertEquals(myString1, otherString);
    }

    @Test
    public void MyDoublyLinkedList_peekLast_size2ListReturnsLastElement(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String myString1 = "hello world1";
        String myString2 = "hello world2";
        myDoublyLinkedList.add(myString1);
        myDoublyLinkedList.add(myString2);
        String otherString = myDoublyLinkedList.peekLast();
        assertEquals(myString2, otherString);
    }

    @Test
    public void MyDoublyLinkedList_peekLast_throwsNoSuchElementExceptionWhenEmpty(){
        assertThrows(NoSuchElementException.class, ()->{
            MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
            myDoublyLinkedList.peekLast();
        });
    }

    //TESTING EQUALS
    @Test
    public void MyDoublyLinkedList_equals_diffTypesReturnsFalse(){
        MyDoublyLinkedList<String> myDoublyLinkedList = new MyDoublyLinkedList<String>();
        String notAList = "I'm definitly not a list";

        String myString1 = "hello world1";
        String myString2 = "hello world2";
        myDoublyLinkedList.add(myString1);
        myDoublyLinkedList.add(myString2);

        assertNotEquals(myDoublyLinkedList, notAList);
    }

    @Test
    public void MyDoublyLinkedList_equals_diffGenericTypesReturnsFalse(){
        MyDoublyLinkedList<String> stringDLL = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<Integer> intDLL = new MyDoublyLinkedList<>();
        stringDLL.add("hello world");
        intDLL.add(1);
        assertNotEquals(stringDLL, intDLL);
    }

    @Test
    public void MyDoublyLinkedList_equals_notEqualsSize1ReturnsFalse(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();
        myDoublyLinkedList1.add("string1");
        myDoublyLinkedList2.add("string2");
        assertNotEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_notEqualsSize2ReturnsFalse(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();
        myDoublyLinkedList1.add("string1");
        myDoublyLinkedList1.add("string1");

        myDoublyLinkedList2.add("string2");
        myDoublyLinkedList2.add("string2");

        assertNotEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_diffSize1And2ReturnsFalse(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();

        myDoublyLinkedList1.add("string1");

        myDoublyLinkedList2.add("string2");
        myDoublyLinkedList2.add("string2");

        assertNotEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_size1ReturnsTrue(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();

        myDoublyLinkedList1.add("string1");

        myDoublyLinkedList2.add("string1");

        assertEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_size2ReturnsTrue(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();

        myDoublyLinkedList1.add("string1");
        myDoublyLinkedList1.add("string2");

        myDoublyLinkedList2.add("string1");
        myDoublyLinkedList2.add("string2");

        assertEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_size3ReturnsTrue(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();

        myDoublyLinkedList1.add("string1");
        myDoublyLinkedList1.add("string2");
        myDoublyLinkedList1.add("string3");

        myDoublyLinkedList2.add("string1");
        myDoublyLinkedList2.add("string2");
        myDoublyLinkedList2.add("string3");

        assertEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }

    @Test
    public void MyDoublyLinkedList_equals_size0ReturnsTrue(){
        MyDoublyLinkedList<String> myDoublyLinkedList1 = new MyDoublyLinkedList<>();
        MyDoublyLinkedList<String> myDoublyLinkedList2 = new MyDoublyLinkedList<>();

        assertEquals(myDoublyLinkedList1, myDoublyLinkedList2);
    }


}