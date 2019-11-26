package ch.thegli.math;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {
    private Fibonacci testSubject;

    @Before
    public void prepare() {
        testSubject = new Fibonacci();
    }

    @Test
    public void calc10() {
        Assert.assertEquals(55, testSubject.calculate(10));
    }

    @Test
    public void calcMin() {
        Assert.assertEquals(0, testSubject.calculate(0));
    }

    @Test
    public void calcMax() {
        Assert.assertEquals(7540113804746346429l, testSubject.calculate(92));
    }

    @Test
    public void tooSmall() {
        Assert.assertEquals(2, testSubject.execute("-1", null));
    }

    @Test
    public void tooLarge() {
        Assert.assertEquals(2, testSubject.execute("93", null));
    }

    @Test
    public void invalidIndex() {
        Assert.assertEquals(2, testSubject.execute("one", null));
    }

}