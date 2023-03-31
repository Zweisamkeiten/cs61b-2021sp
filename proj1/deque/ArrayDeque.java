package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int INITIAL_SIZE = 8;
    private int amount;

    private class ArrayDequeIterator implements Iterator<T> {
        private int idx;

        ArrayDequeIterator() {
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public T next() {
            T ret = items[idx++];
            return ret;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_SIZE];
        size = 0;
        amount = INITIAL_SIZE;
        nextFirst = 7;
        nextLast = 0;
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
        int i = 0;
        do {
            a[i++] = items[idx];
            idx = indexForward(idx);
        } while (idx != nextLast);

        nextLast = i;
        amount = capacity;
        nextFirst = capacity - 1;
        items = a;
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

    private boolean usageUnderPercent25() {
        if (amount >= 16) {
            return size / (double) amount * 100 < 25;
        }
        return false;
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
        if (usageUnderPercent25()) {
            resize(amount / 2);
        }
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
        if (usageUnderPercent25()) {
            resize(amount / 2);
        }
        return res;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int idx = index % size;
        idx = (indexForward(nextFirst) + idx) % size;
        return items[idx];
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        } else if (other instanceof Deque<?>) {
            Deque castedOther = (Deque) other;
            if (this.size != castedOther.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(castedOther.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
