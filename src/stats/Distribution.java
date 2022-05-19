package stats;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Scanner;


public class Distribution extends CalculatorFolder {
    static final Scanner s = new Scanner(System.in);

    public static void dialogue() {
        System.out.println("1: normalpdf");
        System.out.println("2: normalcdf");
        System.out.println("3: invnorm");
        System.out.print("#");
        int f = s.nextInt();
        System.out.println();

        switch (f) {
            case 1: normalpdf(); break;
            case 2: normalcdf(); break;
            case 3: invNorm(); break;
            default:
                System.out.println("Invalid Selection");
                System.out.println();
                dialogue();
        }
    }

    public static BigDecimal factorial(BigDecimal i) {
        if (i.compareTo(BigDecimal.valueOf(2)) < 0) return BigDecimal.ONE;
        return i.multiply(factorial(i.subtract(BigDecimal.ONE)));
    }

    //Dialogue for normalcdf() function
    private static void normalcdf() {
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

        long taylorStart = System.currentTimeMillis();
        System.out.println("Area: " + normalcdf(lowerzval, upperzval));
        long taylorRuntime = System.currentTimeMillis()-taylorStart;

        long riemannStart = System.currentTimeMillis();
        System.out.println("Riemann Area: " + normalcdf2(lowerzval, upperzval));
        long riemannRuntime = System.currentTimeMillis()-riemannStart;

        System.out.println("Taylor Series Approximation Time: " + taylorRuntime + "ms");
        System.out.println("Riemann Sum Approximation Time: " + riemannRuntime + "ms");
        System.out.println();
    }

    public static BigDecimal normalcdf(double lowerz, double upperz) { return normalcdf(upperz).subtract(normalcdf(lowerz)); }

    //Calculates area from center of the standardized normal curve to the given z-value
    public static BigDecimal normalcdf(double zval) {
        if (Math.abs(zval) > 7.2) zval = Math.signum(zval) * 7.2;
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

    public static double normalcdf2(double lowerz, double upperz) { return normalcdf2(upperz) - normalcdf2(lowerz); }

    //normalcdf() but faster using a Riemann sum
    public static double normalcdf2(double zval) {
        double rightzval = Math.abs(zval);
        boolean right = zval>0;
        double step = 0.00001;
        double sum = 0;
        for (double n = 0; n<rightzval; n+=step) {
            sum += step * normalpdf(n, 0, 1);
        } return sum * (right ? 1 : -1);
    }

    //Dialogue for normalpdf() function
    private static void normalpdf() {
        System.out.print("Mean: ");
        double mean = s.nextDouble();
        System.out.print("SD: ");
        double sd = s.nextDouble();
        System.out.print("X-Val: ");
        double xval = s.nextDouble();

        System.out.println("PDF: " + normalpdf(xval, mean, sd));
    }

    //Calculates y-value of the standardized normal curve at the given x-value
    public static double normalpdf(double xval, double mean, double sd) {
        return Math.pow(Math.E, -0.5*Math.pow((xval-mean)/sd, 2))/(sd*Math.sqrt(2*Math.PI));
    }

    //Dialogue for invNorm() function
    private static void invNorm() {
        System.out.print("Mean: ");
        double mean = s.nextDouble();
        System.out.print("SD: ");
        double sd = s.nextDouble();
        System.out.print("LEFT, CENTER, or RIGHT: ");
        String position = s.next().substring(0, 1).toUpperCase(Locale.ROOT);
        System.out.print("Area: ");
        double area = s.nextDouble();

        if (area < 0 || area > 1) {
            System.out.println("Invalid input area: " + area); return;
        } System.out.println();

        double adjustedArea = area - 0.5;

        switch (position) {
            case "L": System.out.println("X-Val: " + Math.signum(adjustedArea) * invNorm(Math.abs(adjustedArea), mean, sd)); break;
            case "R": System.out.println("X-Val: " + (-1) * Math.signum(adjustedArea) * invNorm(Math.abs(adjustedArea), mean, sd)); break;
            case "C": System.out.println("X-Val: " + invNorm(area/2, mean, sd)); break;
            default: System.out.println("Invalid position input: " + position);
        } System.out.println();
    }

    //Calculates x-value whose normalcdf() results in the given area
    public static double invNorm(double area, double mean, double sd) {
        double step = 0.00001 * sd; double xval;
        for (xval = 0; area>0; xval+=step) {
            area -= step * normalpdf(xval, mean, sd);
        } return xval + mean;
    }
}