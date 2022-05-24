package stats;

import java.lang.Math;
import java.util.Scanner;

import static stats.Distribution.normalcdf;


public class Tests extends CalculatorFolder {
	static final Scanner s = new Scanner(System.in);

	public static void dialogue() {
		System.out.println("1: one_prop_z_test");
		System.out.println("2: z_test");
        System.out.print("#"); int f = s.nextInt();
		System.out.println();

		switch (f) {
			case 1: one_prop_z_test(); break;
			case 2: z_test(); break;
			default:
				System.out.println("Invalid Selection\n");
				dialogue();
		}
	}

	private static void one_prop_z_test() {
		System.out.print("p0: ");
		double p0 = s.nextDouble();
		System.out.print("x: ");
		double x = s.nextDouble();
		System.out.print("n: ");
		double n = s.nextDouble();
		System.out.print("Alternative Hypothesis: (prop<p0 enter '-1'; prop>p0 enter '1'; prop≠p0 enter '0') ");
		int altHypo = s.nextInt();
		System.out.println();

		one_prop_z_test(p0, x, n, altHypo);
	}

	public static void one_prop_z_test(double p0, double x, double n, int altHypo) {
		double p_hat = x / n;
		double sd = Math.sqrt(p0 * (1 - p0) / n);
		double z = (p_hat - p0) / sd;

		double p_value = normalcdf(Math.abs(z), 100).doubleValue();
		if (altHypo == 0) p_value *= 2;

		System.out.println("Z: "+z+", P-value: "+ p_value);
	}

	public static void z_test() {
		System.out.print("µ0: ");
		double mu0 = s.nextDouble();
		System.out.print("sigma: ");
		double sigma = s.nextDouble();
		System.out.print("x: ");
		double x = s.nextDouble();
		System.out.print("n: ");
		double n = s.nextDouble();
		System.out.print("Alternative Hypothesis: (x<µ0 enter '-1'; x>µ0 enter '1'; x≠µ0 enter '0') ");
		int altHypo = s.nextInt();
		System.out.println();

		z_test(mu0, sigma, x, n, altHypo);
	}

	public static void z_test(double mu0, double sigma, double x, double n, int altHypo) {
		double sd = sigma / Math.sqrt(n);
		double z = (x - mu0) / sd;

		double p_value = normalcdf(Math.abs(z), 100).doubleValue();
		if (altHypo == 0) p_value *= 2;

		System.out.println("Z: "+z+", P-value: "+ p_value);
	}
}

