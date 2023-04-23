package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyLinkedList<E> { 

	private DNode head;
	private DNode tail;

	/*
	 * ADD YOUR CODE HERE
	 */

	@Override
	public boolean add(E element) {
		if (element == null){
			return false;
		}
		DNode node = new DNode();
		node.element = element;
		node.next = null;
		node.prev = null;

		if (this.isEmpty()){
			tail = node;
			head = node;
			this.size++;
			return true;
		} else if (this.getSize() > 0 ){
			tail.next = node;
			node.prev = tail;
			tail = node;
			tail.next = null;
			this.size++;
			return true;
		}
		return false;
	}

	@Override
	public E remove() {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		} else if (this.getSize() == 1) {
			E element = tail.element;
			tail.next = null;
			tail.element = null;
			head = null;
			tail = null;
			this.size--;
			return element;
		} else {
			E element = tail.element;
			tail = tail.prev;
			tail.next.next = null;
			tail.next.prev = null;
			tail.next.element = null;
			tail.next = null;
			this.size--;
			return element;
		}
	}

	public boolean addFirst(E element){
		if (element == null){
			return false;
		}
		DNode node = new DNode();
		node.element = element;
		node.next = null;
		node.prev = null;
		if (this.isEmpty()){
			tail = node;
			head = node;
			this.size++;
			return true;
		} else if (this.getSize() > 0 ){
			head.prev = node;
			node.next = head;
			head = node;
			this.size++;
			return true;
		}
		return false;
	}


	public boolean addLast(E element){
		return add(element);
	}

	public E removeFirst() {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		} else if (this.getSize() == 1) {
			E element = head.element;
			head.next = null;
			head.prev = null;
			head.element = null;
			head = null;
			tail = null;
			this.size--;
			return element;
		} else {
			E element = head.element;
			head = head.next;
			head.prev.next = null;
			head.prev.prev = null;
			head.prev.element = null;
			head.prev = null;
			this.size--;
			return element;
		}
	}

	public E removeLast(){
		return remove();
	}

	public E peekFirst(){
		if (this.isEmpty()){
			throw new NoSuchElementException();
		} else  {
			return head.element;
		}
	}

	public E peekLast(){
		if (this.isEmpty()){
			throw new NoSuchElementException();
		} else  {
			return tail.element;
		}
	}

	@Override
	public void clear() {
		DNode toClear = new DNode();
		toClear = this.head;
			while (toClear != null){
				toClear = head.next;
				head = null;
				head = toClear;
				this.size--;
			}
		if(this.getSize()!=0){
			throw new NoSuchElementException();
		}
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MyDoublyLinkedList<?>) {
			MyDoublyLinkedList<?> listObj = (MyDoublyLinkedList<?>) obj;
			Iterator<E> thisIter = this.iterator();
			Iterator<?> objIter = listObj.iterator();
			if (listObj.getSize() == 0 && this.getSize()==0){
				return true;
			}
			if (listObj.getSize() != this.getSize()){
				return false;
			}
			for (int i = 0; i < listObj.getSize(); i++) {
				E thisElement = thisIter.next();
				Object objElement = objIter.next();
				if (!thisElement.equals(objElement)) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}


	private class DNode {
		private E element;
		private DNode next;
		private DNode prev;
	}

	public Iterator<E> iterator() {
		return new DLLIterator();
	}

	private class DLLIterator implements Iterator<E> {
		DNode curr;

		public DLLIterator() {
			this.curr = head;
		}

		public boolean hasNext() {
			return this.curr != null;
		}

		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();

			E element = this.curr.element;
			this.curr = this.curr.next;
			return element;
		}
	}
}
