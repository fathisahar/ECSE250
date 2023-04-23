package assignment2;

public class MyQueue<E> {
    private MyDoublyLinkedList dll;

    public MyQueue(){
        dll = new MyDoublyLinkedList();
    }

    public boolean enqueue(E element){
        return dll.add(element);
    }

    public E dequeue(){
        return (E) dll.removeFirst();
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

    @Override
    public boolean equals(Object obj){
        if (obj == null || !(obj instanceof MyQueue<?>)){
            return false;
        }
        MyQueue<?> objDLL = (MyQueue<?>) obj;
        return dll.equals(objDLL.dll);
    }





}
