import junit.framework.*;
public class TestArraySort4 extends TestCase {
	/*
	 * Тестирует метод сортировки ArraySort.sort
	 */
	public void testArraySort4(){
		double[] cases = {5, 12, 3, 67, 82, 9, 0, 11};
		double[] cases2 = {0, 3, 5, 9, 11, 12, 67, 82};
		ArraySort.sort(cases, 0, cases.length - 1 );
		for (int i = 0; i < cases.length; i++) {
			assertEquals("Не равны", cases[i], cases2[i]);
		}
	}

}
