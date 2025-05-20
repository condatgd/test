package katas.stack;

public class StackWithoutLastTest<T> {
    T element;

    public boolean isEmpty() {
        return element == null;
    }

    public void push(T element) {
        this.element = element;
    }

    public T top() {
        return element;
    }

    public void pop() {
        element = null;
    }
}
