package assignment2;

public class MyStack<E> {
    private MyDoublyLinkedList dll;

    public MyStack(){
        dll = new MyDoublyLinkedList();
    }

    public boolean push(E element) {
        return dll.addLast(element);
    }

    public E pop(){
        return (E) dll.removeLast();
    }

    public E peek(){
        return (E) dll.peekLast();
    }

    public boolean isEmpty(){
        return dll.isEmpty();
    }

    public void clear(){
        dll.clear();
    }

    public int getSize(){
        return dll.getSize();
    }

}
