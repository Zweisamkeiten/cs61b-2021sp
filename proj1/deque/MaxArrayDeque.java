package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    /**
     * creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    /**
     * returns the maximum element in the deque as governed by the previously
     * given Comparator. If the MaxArrayDeque is empty, simply return null
     */
    public T max() {
        return max(comparator);
    }

    /**
     * returns the maximum element in the deque as governed by the parameter
     * Comparator c. If the MaxArrayDeque is empty, simple return null.
     */
    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T retMax = get(0);
        for (int i = 1; i < size(); i++) {
            T cur = get(i);

            if (cur == null) {
                continue;
            }

            if (c.compare(retMax, cur) < 0) {
                retMax = cur;
            }
        }
        return retMax;
    }
}
