import junit.framework.*;

public class SimpleFactorialTestSuite extends TestCase {
	public static TestSuite suite() {
		TestSuite totalSuite = new TestSuite("Тест для текущего пакета");
		totalSuite.addTestSuite(TestFactorial1.class); // Добавляем тесты
		totalSuite.addTestSuite(TestFactorial2.class);
		return totalSuite;
	}
}
