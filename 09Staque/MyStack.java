import java.util.*;

public class MyStack<T> {
    
    private MyLinkedList<T> L = new MyLinkedList<T>();
    
    public boolean empty() {
        return L.size() == 0;
    }
    
    public T peek() {
        if (L.size() == 0) {
            throw new EmptyStackException();
        }
        return L.get(L.size() - 1);
    }
    
    public T push(T value) {
        L.add(value);
        return value;
    }
    
    public T pop() {
        return L.remove(L.size() - 1);
    }
    
    public int search(T value) {
        return L.indexOf(value);
    }
}
