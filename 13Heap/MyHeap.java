public class MyHeap {
	
	private int[] heap;
	private int mode;
	private static final int MAX = 0;
	private static final int MIN = 1;
	
	public MyHeap() {
		MyHeap(MAX);
	}
	public MyHeap(int mode) {
		this.mode = mode;
		heap = new int[2];
	}
	
	public void add(int val) {}
	
	public void int remove(int val) {
		return val;
	}
	
	private static boolean compare(int num1, int num2) {
		if (mode == MAX)
			return num1 >= num2;
		else
			return num2 >= num1;
	}
}