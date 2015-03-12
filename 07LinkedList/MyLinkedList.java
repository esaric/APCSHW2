public class MyLinkedList {
	
	private int length;
	private LNode start;
	
	public static void main(String[]args) {
		MyLinkedList l = new MyLinkedList();
		System.out.println(l);
		l.add(5);
		System.out.println(l);
		l.add(7);
		System.out.println(l);
		System.out.println(l.get(0));
		System.out.println(l.get(1));
	}
	
	public MyLinkedList() {
		length = 0;
	}
	
	public String toString() {
		String output = "";
		LNode node = start;
		try {
			while (true) { //dank infinite loops
				output += node.getData() + " ";
				node = node.getNext();
			}
		}catch (NullPointerException e) {
			return "[ " + output + "]";
		}
	}
	
	public void add(int value) {
		LNode newStart = new LNode(value, start);
		start = newStart;
		length++;
	}
	
	// work in progress
	public int remove(int index) {
		return removeHelp(index, start).getData();
	}
	public LNode removeHelp(int index, int current) {
		if (index == 0) {
			return current.getData();
		}
		return removeHelp(index-1, current.getNext());
	}
	
	public int get(int index) {
		return getHelp(index, start);
	}
	private int getHelp(int index, LNode current) {
		if (index == 0) {
			return current.getData();
		}
		return getHelp(index-1, current.getNext());
	}
	
	public int size() {
		return length;
	}
	
}