import junit.framework.*;

public class SimpleArraySortTestSuite extends TestCase {
	public static TestSuite suite() {
		TestSuite totalSuite = new TestSuite("���� ��� �������� ������");
		totalSuite.addTestSuite(TestArraySort1.class);
		totalSuite.addTestSuite(TestArraySort2.class);
		totalSuite.addTestSuite(TestArraySort3.class);
		totalSuite.addTestSuite(TestArraySort4.class);
		return totalSuite;
	}
}
