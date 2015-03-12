public class LNode {
	
	private int data;
	private LNode next;
	
	public LNode(int value) {
		this(value, null);
	}
	public LNode(int value, LNode pointer) {
		data = value;
		next = pointer;
	}
	
	public int getData() {
		return data;
	}
	public void setData(int value) {
		data = value;
	}
	
	public LNode getNext() {
		return next;
	}
	public void setNext(LNode pointer) {
		next = pointer;
	}
	
}