package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        Node(Node pre, T i, Node n) {
            prev = pre;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    private class LinkedDequeIterator implements Iterator<T> {
        private int idx;

        LinkedDequeIterator() {
            idx = 0;
        }

        @Override
        public boolean hasNext() {
            return idx < size;
        }

        @Override
        public T next() {
            T ret = get(idx++);
            return ret;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    /**
     * @author Einsam
     * 构造方法
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /**
     * Adds an item to the end of the list.
     */
    @Override
    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
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
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node toRemove = sentinel.next;
        toRemove.next.prev = sentinel;
        sentinel.next = toRemove.next;
        size -= 1;
        return toRemove.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node toRemove = sentinel.prev;
        toRemove.prev.next = sentinel;
        sentinel.prev = toRemove.prev;
        size -= 1;
        return toRemove.item;
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.item;
    }

    private T getRecursiveHelper(Node cur, int index) {
        if (index == 0) {
            return cur.item;
        }

        return getRecursiveHelper(cur.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
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
