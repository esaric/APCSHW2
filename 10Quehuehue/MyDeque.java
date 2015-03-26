import java.util.*;

public class MyDeque<T> {
    
    private Object[] array;
    private int head;
    private int tail;

    public MyDeque(int length) {
	array = new Object[length];
	head = 0;
	tail = 0;
    }

    public T getFirst() {
	return (T)array[head];
    }
    public T getLast() {
	return (T)array[tail];
    }

    public T removeFirst() {
	if (array.length == 0) {
	    throw new NoSuchElementException();
	}
	T output = (T)array[head];
	array[head] = null;
	if (head == 0) {
	    head = array.length - 1;
	}else {
	    head--;
	}
	return output;
    }
    public T removeLast() {
	if (array.length == 0) {
	    throw new NoSuchElementException();
	}
	T output = (T)array[tail];
	array[tail] = null;
	if (tail == array.length - 1) {
	    tail = 0;
	}else {
	    tail++;
	}
	return output;
    }

    private void resize() {
	
    }
}