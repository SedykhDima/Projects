import junit.framework.*;

public class TestFactorial1 extends TestCase {
	public void testFactorial() {
		int cases[][] = { { 1, 1 }, { 2, 2 }, { 3, 6 }, { 4, 24 }, { 5, 120 },
				{ 6, 720 }, { 7, 5040 }, { 8, 40320 }, { 9, 362880 },
				{ 10, 3628800 } };
		for (int i = 0; i < cases.length; i++)
			assertEquals("Неверное число факториала", cases[i][1],
					Factorial.fact(cases[i][0]));
	}
}
