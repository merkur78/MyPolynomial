package section3;

public class MySingleLinkedList<T> {
	
	public Node<T> head;
	public int size;
	
	public MySingleLinkedList() {
		head = null;
		size = 0;
	}

	public void addFirst(T item) {
		Node<T> newNode = new Node<T>(item);
		//Node<T>[] arr = new Node<T>[100]; //not OK
		newNode.next = head;
		head = newNode;
		
		size++;
	}
	
	public void addAfter(Node<T> before, T item) {
		Node<T> newNode = new Node<T>(item);
		newNode.next = before.next;
		before.next = newNode;
		
		size++;
	}
	

	
	public T removeFirst() {
		if(head == null)
			return null;
		else {
			T temp = head.data;
			head = head.next;
			size--;
			return temp;
		}
	}
	
	public T removeAfter(Node<T> before) {
		if(before.next == null)
			return null;
		else {
			T temp = before.next.data;
			before.next = before.next.next;
			size--;
			return temp;
		}
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

	public Node<T> getNode(int index){
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
			return;

		if(index == 0) {
			addFirst(item);
		} else {
			Node<T> before = getNode(index - 1);
			addAfter(before, item);
		}
	}
	
	public T remove(int index) {
		if(index < 0 || index > size)
			return null;
		
		if(index == 0) {
			return removeFirst();
		} else {
			Node<T> before = getNode(index - 1);
			return removeAfter(before);
		}
	}
	
	public T remove(T item) {
//		int index = indexOf(item);
		Node<T> p = head;
		Node<T> before = null;
		while(p != null && !p.data.equals(item)) {
			before = p;
			p = p.next;
		}
		if(p == null)
			return null;

		if(before == null)
			return removeFirst();
		else
			return removeAfter(before);
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
