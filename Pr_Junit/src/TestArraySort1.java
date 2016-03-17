import junit.framework.*;

public class TestArraySort1 extends TestCase {
	/*
	 * ��������� ����� ��� ��������� ���� �������� ArraySort.less
	 */
	public void testArraySort() {
		int[][] cases = { {1, 2}, {3, 5}, {4, 49}, {10, 22}};
		for (int i = 0 ; i < cases.length; i++){
			assertTrue(cases[i][1]+" ������ " + cases[i][0], ArraySort.less(cases[i][0], cases[i][1]));
		}
	}
}
