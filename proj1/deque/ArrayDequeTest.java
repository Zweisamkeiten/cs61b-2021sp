package deque;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {


    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        Deque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        Deque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        Deque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        Deque<String> lld1 = new ArrayDeque<>();
        Deque<Double> lld2 = new ArrayDeque<>();
        Deque<Boolean> lld3 = new ArrayDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        Deque<Integer> lld1 = new ArrayDeque<>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        Deque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequegetTest() {

        Deque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (int i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.get(i), 0.0);
        }

        for (int i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.get(i), 0.0);
        }

    }

    @Test
    /* Test iterator. */
    public void iteratorTest() {
        ArrayDeque<Integer> adeque = new ArrayDeque<>();
        adeque.addLast(5);
        adeque.addLast(23);
        adeque.addLast(42);

        //iteration
        for (int i : adeque) {
            System.out.println(i);
        }
    }

    @Test
    public void resizingCauseNulls() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            d.addLast(i);
        }
        assertNotNull(d.removeFirst());
        assertNotNull(d.removeLast());
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();
        d.removeFirst();

        d.addFirst(0);
        d.addLast(1);
        assertNotNull(d.get(0));
    }

    @Test
    public void resizingCauseNulls2() {
        ArrayDeque<Integer> d = new ArrayDeque<>();
        d.addFirst(0);
        assertEquals(0, (int) d.removeLast());
        d.addLast(2);
        d.addLast(3);
        d.addFirst(4);
        d.addFirst(5);
        assertEquals(3, (int) d.removeLast());
        assertEquals(5, (int) d.removeFirst());
        assertEquals(2, (int) d.get(1));
    }
}
