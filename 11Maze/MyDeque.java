import java.util.*;

public class MyDeque<T> {
    
    private Object[] array;
    private int head;
    private int tail;
    private int size;

    public MyDeque() {
    	array = new Object[4];
    	head = 0;
    	tail = 0;
    	size = 0;
    }
    
    public String toString() {
        String output = "";
        int index = 0;
        while (index < size) {
            output += array[(head+index) % array.length] + " ";
            index++;
        }
        //return "[ " + output + "]";
        return "[ " + output + "] " + "Tail: " + tail + " Head: " + head + "\n" + Arrays.toString(array);
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
            array[0] = value;
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
            array[0] = value;
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
	    int index = 0;
        while (index < size) {
            newArray[index] = array[(head+index) % array.length];
            index++;
        }
        head = 0;
        tail = size - 1;
	    array = newArray;
    }
    
	public int getLength() {
		return size;
	}
	
    public static void main(String[]args) {
        MyDeque<Integer> d = new MyDeque<Integer>();
        d.addFirst(5);
		//d.addFirst(6);
		d.removeFirst();
		System.out.println(d);
        d.addFirst(7);
		System.out.println(d);
    }
}
