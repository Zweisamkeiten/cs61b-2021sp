package deque;

public class LinkedListDeque<T> {
    public class Node {
        private Node prev;
        public T item;
        private Node next;

        public Node(Node pre, T i, Node n) {
            prev = pre;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

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

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /**
     * get the first item.
     *
     * @return T
     */
    public T getFirst() {
        return sentinel.next.item;
    }

    /**
     * Adds an item to the end of the list.
     */
    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return list size.
     *
     * @return int
     */
    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

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

    private T getRecursive_Helper(Node cur, int index) {
        if (index == 0) {
            return cur.item;
        }

        return getRecursive_Helper(cur.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }
        return getRecursive_Helper(sentinel.next, index);
    }
}
