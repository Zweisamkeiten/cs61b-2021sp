package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> ref = new AListNoResizing<>();
        BuggyAList<Integer> dut = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            int testNumber = (int) (Math.random() % 10);
            ref.addLast(testNumber);
            dut.addLast(testNumber);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(ref.removeLast(), dut.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> ref = new AListNoResizing<>();
        BuggyAList<Integer> dut = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                ref.addLast(randVal);
                dut.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = ref.size();
                System.out.println("size: " + size);
                assertEquals(size, dut.size());
            } else if (operationNumber == 2) {
                if (ref.size() == 0) continue;
                assertEquals(ref.getLast(), dut.getLast());
                assertEquals(ref.removeLast(), dut.removeLast());
            }
        }
    }
}
