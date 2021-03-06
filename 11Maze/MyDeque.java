import java.util.*;
public class MyDeque<T> {
    private Object[] array;
	private int[] weights;
    private int head;
    private int tail;
    private int size;
	
    public MyDeque() {
		array = new Object[4];
		weights = new int[4];
		head = 0;
		tail = 0;
		size = 0;
    }
    public String toString() {
		String output = "[ ";
		int index = 0;
		while (index < size) {
			output += array[(head+index) % array.length] + " ";
			index++;
		}
		output += "]\n[ ";
		index = 0;
		while (index < size) {
			output += weights[(head+index) % array.length] + " ";
			index++;
		}
		return output + "]";
		//return "[ " + output + "] " + "Tail: " + tail + " Head: " + head + "\n" + Arrays.toString(array);
    }
    public T getFirst() {
		return (T)array[head];
    }
    public T getLast() {
		return (T)array[tail];
    }
    public T removeFirst() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T output = (T)array[head];
		array[head] = null;
		if (size == 1) {
			head = tail;
		}else if (head == array.length - 1) {
			head = 0;
		}else {
			head++;
		}
		size--;
		return output;
    }
    public T removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		T output = (T)array[tail];
		array[tail] = null;
		if (size == 1) {
			tail = head;
		}else if (tail == 0) {
			tail = array.length - 1;
		}else {
			tail--;
		}
		size--;
		return output;
    }
    public void addFirst(T value) {
	if (size == 0) {
	    array[head] = value;
	}else{
	    if (size == array.length) {
		resize();
	    }
	    if (head == 0) {
		head = array.length - 1;
	    }else {
		head--;
	    }
	    array[head] = value;
	}
	size++;
    }
    public void addLast(T value) {
	if (size == 0) {
	    array[tail] = value;
	}else {
	    if (size == array.length) {
		resize();
	    }
	    if (tail == array.length - 1) {
		tail = 0;
	    }else {
		tail++;
	    }
	    array[tail] = value;
	}
	size++;
    }
    private void resize() {
		Object[] newArray = new Object[array.length * 2];
		int[] newWeights = new int[weights.length * 2];
		int index = 0;
		while (index < size) {
			newArray[index] = array[(head+index) % array.length];
			newWeights[index] = weights[(head+index) % weights.length];
			index++;
		}
		head = 0;
		tail = size - 1;
		array = newArray;
		weights = newWeights;
    }
    public int getLength() {
		return size;
    }
	public T add(T value, int priority) {
		if (size == array.length) {
			resize();
		}
		if (size > 0) {
			tail++;
		}
		if (tail == array.length) {
			tail = 0;
	    }
		array[tail] = value;
		weights[tail] = priority;
		size++;
		return value;
	}
	public T removeSmallest() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		swap(head, findSmallest());
		T output = (T)array[head];
		array[head] = null;
		weights[head] = -1;
		size--;
		if (size > 0) {
			head++;
		}
		if (head == array.length) {
			head = 0;
		}
		return output;
	}
	private void swap(int first, int last) {
		if (first > array.length || last > array.length || first < 0 || last < 0) {
			throw new IndexOutOfBoundsException();
		}
		Object tempObj = array[last];
		int tempInt = weights[last];
		array[last] = array[first];
		weights[last] = weights[first];
		array[first] = tempObj;
		weights[first] = tempInt;
	}
	private int findSmallest() {
		int min = weights[head];
		int minIndex = head;
		int index = 0;
		while (index < size) {
			int currentIndex = (head+index) % weights.length;
			if (min > weights[currentIndex]) {
				min = weights[currentIndex];
				minIndex = currentIndex;
			}
			index++;
		}
		return minIndex;
	}
    public static void main(String[]args) {
		MyDeque<String> d = new MyDeque<String>();
		d.add("1", 1);
		d.add("2", 2);
		System.out.println(d);
		System.out.println(d.removeSmallest());
		System.out.println(d);
		d.add("3", 3);
		System.out.println(d);
		System.out.println(d.removeSmallest());
		System.out.println(d);
		d.add("4", 4);
		System.out.println(d);
    }
}