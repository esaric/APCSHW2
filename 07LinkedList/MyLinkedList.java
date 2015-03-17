public class MyLinkedList<T> {
	
	private int length;
	private LNode<T> start;
	private LNode<T> end;
	
	public MyLinkedList() {
		length = 0;
	}
	
	public String name() {
		return "saric.elias";
	}	
	public String toString() {
		String output = "";
		LNode<T> node = start;
		try {
			while (true) { //dank infinite loops
				output += node.getData() + " ";
				node = node.getNext();
			}
		}catch (NullPointerException e) {
			return "[ " + output + "]";
		}
	}
	
	public boolean add(T value) {
		LNode<T> node = new LNode<T>(value);
		if (length == 0) {
			start = node;
			end = node;
		}else {
			end.setNext(node);
			end = node;
		}
		length++;
		return true;
	}
	public boolean add(int index, T value) {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		LNode<T> node = new LNode<T>(value, start);
		if (index == size()) {
			return add(value);
		}else if (index == 0) {
			start = node;
		}else {
			node.setNext(getNode(index, start));
			getNode(index-1, start).setNext(node);
		}
		length++;
		return true;
	}
	
	public T remove(int index) {
		if (index < 0 || index > size() - 1) {
			throw new IndexOutOfBoundsException();
		}
		T removed = getNode(index, start).getData();
		if (size() == 1) {
			start = null;
			end = null;
		}else if (index == size() - 1) {
			end = getNode(index - 1, start);
			end.setNext(null);
		}else if (index == 0) {
			start = getNode(1, start);
		}else {
			getNode(index - 1, start).setNext(getNode(index + 1, start));
		}
		length--;
		return removed;
	}
	
	public T get(int index) {
		return getNode(index, start).getData();
	}
	private LNode<T> getNode(int index, LNode<T> current) {
		while (index > 0) {
			current = current.getNext();
			index--;
		}
		return current;
	}
	
	public int indexOf(T value) {
		LNode<T> currentNode = start;
		for (int i = 0; i < size(); i++) {
			if (value.equals(currentNode.getData())) {
				return i;
			}
			currentNode = currentNode.getNext();
		}
		return -1;
	}
	
	public int size() {
		return length;
	}
	
}