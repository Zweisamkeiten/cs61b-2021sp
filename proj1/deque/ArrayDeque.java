package deque;

public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_SIZE = 8;
    private int amount;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        amount = INITIAL_SIZE;
        nextFirst = 4;
        nextLast = 5;
    }

    private int indexForward(int idx) {
        return (idx + 1) % amount;
    }

    private int indexBackward(int idx) {
        return (idx + amount - 1) % amount;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int idx = indexForward(nextFirst);

        while (idx != 0) {
            a[capacity - (amount - idx)] = items[idx];
            idx = indexForward(idx);
        }

        while (idx != nextLast) {
            a[idx] = items[idx];
            idx = indexForward(idx);
        }

        items = a;
        nextFirst = ((capacity - (amount - idx)) + capacity - 1) % capacity;
        amount = capacity;
    }

    @Override
    public void addFirst(T item) {
        if (size + 1 > amount) {
            resize(2 * amount);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = indexBackward(nextFirst);
    }

    /**
     * get the first item.
     *
     * @return T
     */
    @Override
    public T getFirst() {
        return items[indexForward(nextFirst)];
    }

    /**
     * Adds an item to the end of the list.
     */
    @Override
    public void addLast(T item) {
        if (size + 1 > amount) {
            resize(2 * amount);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = indexForward(nextLast);
    }

    @Override
    public T getLast() {
        return items[indexBackward(nextLast)];
    }
    
    /**
     * return list size.
     *
     * @return int
     */
    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int idx = indexForward(nextFirst);
        while (idx != nextLast) {
            System.out.print(items[idx] + " ");
            idx = indexForward(idx);
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int toRemoveidx = indexForward(nextFirst);
        T res = items[toRemoveidx];
        items[toRemoveidx] = null;
        size -= 1;
        nextFirst = indexForward(nextFirst);
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int toRemoveidx = indexBackward(nextLast);
        T res = items[toRemoveidx];
        items[toRemoveidx] = null;
        size -= 1;
        nextLast = indexBackward(nextLast);
        return res;
    }

    private boolean indexWithinArr(int idx) {
        if (nextFirst < nextLast) {
            return idx > nextFirst && idx < nextLast;
        } else {
            return idx < nextLast || idx > nextFirst;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        if (indexWithinArr(index)) {
            return items[index];
        }
        return null;
    }
}
