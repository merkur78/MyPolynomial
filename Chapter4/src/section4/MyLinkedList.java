package section4;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	public MyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}


	//Node Inner Class
	private static class Node<T> {
		private T data;
		private Node<T> next;
		private Node<T> prev;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
			this.prev = null;
		}
	}
	
	
	public Iterator<E> iterator() {
		return new MyListIterator(0);
	}
	
	public ListIterator<E> listIterator() {
		return new MyListIterator(0);
	}
	
	public ListIterator<E> listIterator(int index) {
		return new MyListIterator(index);
	}
	
	//ListIterator Inner Class implement
	private class MyListIterator implements ListIterator<E> {
		private Node<E> nextItem;
		private Node<E> lastItemReturned;
		private int index;
		
		public MyListIterator() {
			super();
			this.nextItem = null;
			this.lastItemReturned = null;
			this.index = 0;
		}
		
		public MyListIterator(int i) {
			if(i < 0 || i > size) {
				throw new IndexOutOfBoundsException("Invalid index " + i);
			}
			
			this.lastItemReturned = null;
			if(i == size) {
				this.index = size;
				this.nextItem = null;
			} else {
				this.nextItem = head;
				for(this.index = 0; this.index < i; this.index++) {
					this.nextItem = this.nextItem.next;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return this.nextItem != null;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			
			return lastItemReturned.data;
		}

		@Override
		public boolean hasPrevious() {
			return (nextItem == null && size != 0) || nextItem.prev != null;
		}

		@Override
		public E previous() {
			if(!hasPrevious())
				throw new NoSuchElementException();
			
			if(nextItem == null)
				nextItem = tail;
			else
				nextItem = nextItem.prev;
			
			lastItemReturned = nextItem;
			index--;
			
			return lastItemReturned.data;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index - 1;
		}

		@Override
		public void remove() {
			
		}

		@Override
		public void set(E e) {
			
		}

		@Override
		public void add(E e) {
			if(head == null) {	//신규
				head = new Node<E>(e);
				tail = head;
			} else if(nextItem == head) {	//맨앞에.
				Node<E> newNode = new Node<E>(e);
				newNode.next = nextItem;
				nextItem.prev = newNode;
				
				head = newNode;
			} else if(nextItem == null) {	//맨마지막
				Node<E> newNode = new Node<E>(e);
				tail.next = newNode;
				newNode.prev = tail;
				tail = newNode;
			} else {
				Node<E> newNode = new Node<E>(e);
				newNode.prev = nextItem.prev;
				nextItem.prev.next = newNode;
				newNode.next = nextItem;
				nextItem.prev = newNode;
			}
			
			size++;
			index++;
		}
		
	}


	public int indexOf(E data) {
		Node<E> p = head;
		int index = 0;
		while(p != null) {
			if(p.data.equals(data))
				return index;
			p = p.next;
			index++;
		}
		return -1;
	}
	
	public void add(int index, E data) {
		listIterator(index).add(data);
	}
	
	public E get(int index) {
		return listIterator(index).next();
	}
	
	public E remove(int index) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		ListIterator<E> listIterator = listIterator(index);
		E result = listIterator.next();
		listIterator.remove();
		return result;
	}
	
	public boolean remove(E data) {
		
		
		return false;
	}
	
	public int size() {
		return size;
	}
	
}
