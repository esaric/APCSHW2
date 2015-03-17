public class LNode<T> {
	
	private T data;
	private LNode<T> next;
	
	public LNode(T value) {
		this(value, null);
	}
	public LNode(T value, LNode<T> pointer) {
		data = value;
		next = pointer;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T value) {
		data = value;
	}
	
	public LNode<T> getNext() {
		return next;
	}
	public void setNext(LNode<T> pointer) {
		next = pointer;
	}
	
}