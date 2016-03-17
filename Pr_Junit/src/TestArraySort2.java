import junit.framework.*;

public class TestArraySort2 extends TestCase {
	/*
	 * Тестирует метод обменивающий два обьекта ArraySort.exch
	 */
	public void testArraySort2() {
		double[][] cases = { {1, 3}, {4, 10}, {105, 51}, {567, 11}};
		for (int i = 0; i < cases.length; i++) {
			double[] cases2 = {cases[i][1], cases[i][0]};
			ArraySort.exch(cases[i], 0, 1);
			for (int k = 0; k < cases2.length; k++) {
				assertEquals("не равны", cases[i][k], cases2[k]);
			}
		}
	}
}
