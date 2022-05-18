package stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        normalcdf();
    }

    public static BigDecimal factorial(BigDecimal i) {
        if (i.compareTo(BigDecimal.valueOf(2)) < 0) return BigDecimal.ONE;
        return i.multiply(factorial(i.subtract(BigDecimal.ONE)));
    }

    //Dialogue for normalcdf() function
    public static void normalcdf() {
        System.out.print("Mean: ");
        double mean = s.nextDouble();
        System.out.print("SD: ");
        double sd = s.nextDouble();
        System.out.print("Lower: ");
        double lower = s.nextDouble();
        System.out.print("Upper: ");
        double upper = s.nextDouble();
        System.out.println();

        double lowerzval = (lower - mean)/sd;
        double upperzval = (upper - mean)/sd;

        double l = 7.2; //experimental
        if (lowerzval < -l) lowerzval = -l;
        if (lowerzval > l) lowerzval = l;
        if (upperzval < -l) upperzval = -l;
        if (upperzval > l) upperzval = l;

        System.out.println("Area: " + normalcdf(upperzval).subtract(normalcdf(lowerzval)));
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