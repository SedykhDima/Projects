import junit.framework.*;

public class TestArraySort3 extends TestCase {
	/*
	 * Тестирует метод сравнивающий и меняющий обьекты местами
	 * ArraySort.copmExch
	 */
	public void testArraySort3() {
		double[][] cases = {{1, 3}, {4, 10}, {105, 51}, {567, 11}};
		double[][] cases2 = {{3, 1}, {10, 4}, {105, 51}, {567, 11}};
		for (int i = 0; i < cases.length; i++) {
			ArraySort.compExch(cases[i], 1, 0);
			for (int k = 0; k < cases2[i].length; k++) {
				assertEquals("Не равны", cases[i][k], cases2[i][k]);
			}
		}
	}
}
