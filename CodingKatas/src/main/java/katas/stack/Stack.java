package katas.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack<T> {

    List<T> elements = new ArrayList<>();

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void push(T element) {
        this.elements.add(element);
    }

    public T top() {
        if(isEmpty()) throw new IllegalStateException("Stack is empty");
        return elements.get(elements.size() - 1);
    }

    public Stack<T> pop() {
        if(isEmpty()) throw new IllegalStateException("Stack is empty");
        elements.remove(elements.size() - 1);
        return this;
    }
}
