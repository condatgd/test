package katas.serialcomp;

public class ShiftStorage {

    int pos;
    int[] store;

    public ShiftStorage(int size) {
        store = new int[size];
    }

    public void clear() {
        for (int i=0; i<store.length; i++) {
            store[i] = 0;
        }
        pos = 0;
    }

    public int getCurrentValue() {
        return store[pos];
    }

    public int getPos() {
        return pos;
    }

    public void setCurrentValue(int v) {
        store[pos] = v;
    }

    public void shift() {
        pos ++;
        if(pos>=store.length)
            pos = 0;
    }

}
