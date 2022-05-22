package stats;

import java.util.Scanner;


public class Probability extends CalculatorFolder {
    static final Scanner s = new Scanner(System.in);

    public static void dialogue() {
        System.out.println("1: geompdf");
        System.out.println("2: geomcdf");
        System.out.println("3: binompdf");
        System.out.println("4: binomcdf");
        System.out.print("#"); int f = s.nextInt();
        System.out.println();

        switch (f) {
            case 1: geompdf(); break;
            case 2: geomcdf(); break;
            case 3: binompdf(); break;
            case 4: binomcdf(); break;
            default:
                System.out.println("Invalid Selection\n");
                dialogue();
        }
    }

    public static double nCk(int n, int k) {
        if (n < 0 || k < 0) { System.out.println("Negative value passed into nCk"); return -1; }
        if (k > n) { System.out.println("K greater than n in nCk: " + k + " > " + n); return -1; }
        if (k == 0) return 1;
        return ((n-(k-1)) / (double) (k)) * nCk(n, k-1);
    }

    private static void geompdf() {
        System.out.print("p: ");
        double p = s.nextDouble();
        System.out.print("x: ");
        int x = s.nextInt();
        System.out.println();

        System.out.println("Probability: " + geompdf(p, x));
    }

    public static double geompdf(double p, int x) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for geompdf: " + p); return -1; }
        if (x < 1) return 0;
        return p * Math.pow(1-p, x-1);
    }

    private static void geomcdf() {
        System.out.print("p: ");
        double p = s.nextDouble();
        System.out.print("x: ");
        int x = s.nextInt();
        System.out.println();

        System.out.println("Probability: " + geomcdf(p, x));
    }

    public static double geomcdf(double p, int x) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for geompdf: " + p); return -1; }
        if (x < 1) return 0;
        return geompdf(p, x) + geomcdf(p, x-1);
    }

    private static void binompdf() {
        System.out.print("p: ");
        double p = s.nextDouble();
        System.out.print("x: ");
        int x = s.nextInt();
        System.out.print("n: ");
        int n = s.nextInt();
        System.out.println();

        System.out.println("Probability: " + binompdf(p, x, n));
    }

    public static double binompdf(double p, int x, int n) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for binompdf: " + p); return -1; }
        if (x > n) { System.out.println("X greater than n in binompdf: " + x + " > " + n); return -1; }
        if (x < 0 || n < 1) return 0;
        return Math.pow(p, x) * Math.pow(1-p, n-x) * nCk(n, x);
    }

    private static void binomcdf() {
        System.out.print("p: ");
        double p = s.nextDouble();
        System.out.print("x: ");
        int x = s.nextInt();
        System.out.print("n: ");
        int n = s.nextInt();
        System.out.println();

        System.out.println("Probability: " + binomcdf(p, x, n));
    }

    public static double binomcdf(double p, int x, int n) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for binomcdf: " + p); return -1; }
        if (x > n) { System.out.println("X greater than n in binomcdf: " + x + " > " + n); return -1; }
        if (x < 0 || n < 1) return 0;
        return binompdf(p, x, n) + binomcdf(p, x-1, n);
    }
}
