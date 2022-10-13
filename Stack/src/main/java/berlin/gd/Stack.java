package berlin.gd;

import java.util.*;

public class Stack<T> implements IStack<T> {

    private final ArrayList<T> filo;

    public Stack() {
        this.filo = new ArrayList<>();
    }

    public Stack(List<T> list) {
        this.filo = new ArrayList<>(list);
    }

    @Override
    public void push(T t) {
        filo.add(0, t);
    }

    @Override
    public Optional<T> pop() {
        Optional<T> first = filo.stream().findFirst();
        if(first.isPresent()) {
            filo.remove(0);
        }
        return first;
    }
}
