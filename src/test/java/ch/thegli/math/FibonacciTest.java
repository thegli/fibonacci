package ch.thegli.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FibonacciTest {
	private static final int OK_RC = 0;
	private static final int BAD_ARGUMENT_RC = 2;
	private Fibonacci testSubject;

	@BeforeEach
	public void prepare() {
		testSubject = new Fibonacci();
	}

	@Test
	public void calc10() {
		Assertions.assertEquals(55, testSubject.calculate(10));
	}

	@Test
	public void calcMin() {
		Assertions.assertEquals(0, testSubject.calculate(0));
	}

	@Test
	public void calcMax() {
		Assertions.assertEquals(7540113804746346429l, testSubject.calculate(92));
	}
	
	@Test
	public void accepted() {
		Assertions.assertEquals(OK_RC, testSubject.execute("6", null));
	}

	@Test
	public void tooSmall() {
		Assertions.assertEquals(BAD_ARGUMENT_RC, testSubject.execute("-1", null));
	}

	@Test
	public void tooLarge() {
		Assertions.assertEquals(BAD_ARGUMENT_RC, testSubject.execute("93", null));
	}

	@Test
	public void invalidIndex() {
		Assertions.assertEquals(BAD_ARGUMENT_RC, testSubject.execute("one", null));
	}

}