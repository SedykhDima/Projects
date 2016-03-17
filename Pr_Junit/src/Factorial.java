public class Factorial {
	public static long fact(int n) {
		long k = 1;
		if (n > 1)
			for (int i = 2; i <= n; i++)
				k = k * i;
		return k;
	}
}
