import java.util.*;

public class MyHeap {

    private int[] heap;
    private int mode;

    private static final int MAX = 1;
    private static final int MIN = -1;
    private static final int UP = 1;
    private static final int DOWN = -1;

    public MyHeap() {
	this(MAX);
    }
    public MyHeap(int mode) {
	this.mode = mode;
	heap = new int[4];
    }

    public void add(int val) {
		if (heap[0] + 1 == heap.length) {
			resize();
		}
		heap[0]++;
		heap[heap[0]] = val;
		shiftUp();
    }
    public int remove() throws NoSuchElementException{
		if (heap[0] == 0) {
			throw new NoSuchElementException();
		}
		int output = heap[1];
		swap(1, heap[0]);
		heap[heap[0]] = 0;
		heap[0]--;
		shiftDown();
		return output;
    }

    public String toString() {
	return Arrays.toString(heap);
    }
	
	public int peek() {
		return heap[1];
	}

    private void swap(int index1, int index2) {
	int temp = heap[index1];
	heap[index1] = heap[index2];
	heap[index2] = temp;
    }
    private void resize() {
	heap = Arrays.copyOf(heap, (heap[0]+1)*2);
    }
    private void shiftUp() {
	int last = heap[0];
	while (heap[last]*mode >= heap[last/2]*mode && last != 1) {
	    //System.out.println(heap[last]);
	    swap(last, last/2);
	    last = last/2;
	}
    }
    private void shiftDown() {
		int first = 1;
		while (first*2 < heap[0]
			   && (heap[first]*mode < heap[first*2]*mode 
			   || heap[first]*mode < heap[first*2+1]*mode)) {
				   
			if (heap[first*2]*mode > heap[first*2+1]*mode) {
				swap(first, first*2);
				first = first * 2;
			}else{
				swap(first, first*2+1);
				first = first * 2 + 1;
			}
		}
    }

    public static void main(String[] args) {
	MyHeap h = new MyHeap();
	System.out.println(h);
	h.add(5);
	h.add(4);
	h.add(6);
	h.add(6);
	h.add(6);
	System.out.println(h);
	h.remove();
	System.out.println(h);
	h.remove();
	System.out.println(h);
	h.remove();
	System.out.println(h);
	h.remove();
	h.remove();
	h.remove();
	System.out.println(h);
    }
}