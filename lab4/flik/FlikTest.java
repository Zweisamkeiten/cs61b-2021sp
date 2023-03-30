package flik;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FlikTest {
    @Test
    public void Test() {
        for (int i = 0; i < 500; i++) {
            assertTrue("num == " + i, Flik.isSameNumber(i, i));
        }
    }
}
