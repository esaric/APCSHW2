import java.util.*;

public class MyQueue<T> {
    
    private MyLinkedList<T> L = new MyLinkedList<T>();
    
    public boolean add(T value) {
        L.add(value);
        return true;
    }
    
    public T element() {
        return L.get(0);
    }
    
    public boolean offer(T value) {
        return add(value);
    }
    
    public T peek() {
        if (L.size() == 0) {
            return null;
        }
        return element();
    }
    
    public T poll() {
        if (L.size() == 0) {
            return null;
        }
        return L.remove(0);
    }
    
    public T remove() {
        return poll();
    }
    
}
