package section3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySingleLinkedList<T> {
	
	private Node<T> head;
	private int size;
	
	public MySingleLinkedList() {
		head = null;
		size = 0;
	}

	//inner class
	private static class Node<T> {
		public T data;
		public Node<T> next;
		
		public Node(T data) {
			this.data = data;
			this.next = null;
		}	
	}
	
	public Iterator<T> iterator() {

		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T> {
		private Node<T> nextNode;
		
		
		public MyIterator() {
			this.nextNode = head;
		}

		public boolean hasNext() {
			return (nextNode != null);
		}
		
		public T next() {
			if(nextNode == null)
				throw new NoSuchElementException();
			
			T val = nextNode.data;
			nextNode = nextNode.next;

			return val;
		}
		
		public void remove() {
			
		}
	}
	
	private void addFirst(T item) {
		Node<T> newNode = new Node<T>(item);
		newNode.next = head;
		head = newNode;
		
		size++;
	}
	
	private void addAfter(Node<T> before, T item) {
		Node<T> newNode = new Node<T>(item);
		newNode.next = before.next;
		before.next = newNode;
		
		size++;
	}
	
	private boolean removeFirst() {
		if(head == null)
			return false;
		else {
			//T temp = head.data;
			head = head.next;
			size--;
			return true;
		}
	}
	
	private boolean removeAfter(Node<T> before) {
		if(before.next == null)
			return false;
		else {
			//T temp = before.next.data;
			before.next = before.next.next;
			size--;
			return true;
		}
	}

	private Node<T> getNode(int index) {
		if(index < 0 || index >= size)
			return null;
		
		Node<T> p = head;
		for(int i = 0; i < index; i++) {
			p = p.next;
		}
		return p;
	}
	

	
	public T get(int index) {
		if(index < 0 || index >= size)
			return null;
//		
//		Node<T> p = head;
//		for(int i = 0; i < index; i++) {
//			p = p.next;
//		}
//		return p.data;
		return getNode(index).data;
	}

	
	public void add(int index, T item) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException(index);

		if(index == 0) {
			addFirst(item);
		} else {
			Node<T> before = getNode(index - 1);
			addAfter(before, item);
		}
	}

	
	
	public boolean remove(int index) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException(index);
		
		if(index == 0) {
			return removeFirst();
		} else {
			Node<T> before = getNode(index - 1);
			return removeAfter(before);
		}
	}
	
	public boolean remove(T item) {
		Node<T> p = head;
		Node<T> before = null;
		while(p != null && !p.data.equals(item)) {
			before = p;
			p = p.next;
		}
		if(p == null)
			return false;

		if(before == null)
			return removeFirst();
		else
			return removeAfter(before);
	}
	
	
	public int indexOf(T item) {
		Node<T> p = head;
		int index = 0;
		while(p != null) {
			if(p.data.equals(item))
				return index;
			p = p.next;
			index++;
		}
		return -1;
	}
	
	
	public int getSize() {
		return size;
	}
	
	

	public static void main(String[] args) {
		MySingleLinkedList<String> list = new MySingleLinkedList<String>();
		list.add(0, "Saturday");
		list.add(1, "Friday");
		list.add(0, "Monday");
		list.add(2, "Tuesday");
		
		String str = list.get(2);
		System.out.println(str);
		list.remove(2);
		int pos = list.indexOf("Friday");
		System.out.println(pos);
		for (int i = 0; i < list.size; i++) {
			System.out.println(list.get(i));
		}
	}

}
