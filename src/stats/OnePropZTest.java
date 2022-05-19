package stats;

import java.lang.Math;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class OnePropZTest {

	static final Scanner s = new Scanner(System.in);

	public static void main(String[] args) { one_prop_z_test(); };

	public static void one_prop_z_test() {

		System.out.print("p0: ");
		double p0 = s.nextDouble();
		System.out.print("x: ");
		double x = s.nextDouble();
		System.out.print("n: ");
		double n = s.nextDouble();
		System.out.print("Alternative Hypothesis: (prop<p0 enter '-1'; prop>p0 enter '1'; prop≠p0 enter '0') ");
		int altHypo = s.nextInt();
		System.out.println();

		double p_hat = x / n;
		double sd = Math.sqrt(p0 * (1 - p0) / n);
		double z = (p_hat - p0) / sd;

		double p_value = 0;
		if (altHypo == -1) {
			p_value = normalcdf(-100, z).doubleValue(); //TODO
		}
		if (altHypo == 1) {
			p_value = normalcdf(z, 100).doubleValue();
		}
		else {
			p_value = (normalcdf(-100, -z).add(normalcdf(z, 100))).doubleValue(); //TODO
		}

		System.out.println("Z: "+z+", P-value: "+ p_value);
	}

	public static BigDecimal factorial(BigDecimal i) {
		if (i.compareTo(BigDecimal.valueOf(2)) < 0) return BigDecimal.ONE;
		return i.multiply(factorial(i.subtract(BigDecimal.ONE)));
	}

	//Dialogue for normalcdf() function
	public static BigDecimal normalcdf(double lower, double upper) {

		double mean = 0;
		double sd = 1;

		double lowerzval = (lower - mean)/sd;
		double upperzval = (upper - mean)/sd;

		double l = 7.2; //experimental
		if (lowerzval < -l) lowerzval = -l;
		if (lowerzval > l) lowerzval = l;
		if (upperzval < -l) upperzval = -l;
		if (upperzval > l) upperzval = l;

		BigDecimal p_value = normalcdf(upperzval).subtract(normalcdf(lowerzval));
		return p_value;
	}

	//Calculates area from center of the standardized normal curve to the given zval
	public static BigDecimal normalcdf(double zval) {
		final MathContext c = new MathContext(16, RoundingMode.DOWN);
		final BigDecimal errorBound = BigDecimal.valueOf(0.00000001);
		final double root2pi = Math.sqrt(2*Math.PI);

		BigDecimal delta = new BigDecimal(1);
		BigDecimal sum = new BigDecimal(0);

		//Taylor series approximation
		for (short n = 0; delta.abs().compareTo(errorBound) > 0; n++) {
			delta = BigDecimal.valueOf(-0.5).pow(n).multiply(BigDecimal.valueOf(zval).pow(2*n + 1))
					.divide(factorial(BigDecimal.valueOf(n)).multiply(BigDecimal.valueOf(root2pi * (2*n + 1))), c);
			sum = sum.add(delta);
		} return sum;
	}

}

