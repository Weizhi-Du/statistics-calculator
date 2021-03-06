package stats;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class LinearRegression extends CalculatorFolder {
    static final Scanner s = new Scanner(System.in);

    public static void dialogue() {
        System.out.print("Path to csv: ");
        int[][] arr;
        try {
            arr = tableMaker(s.next());
        } catch (FileNotFoundException e) {
            System.out.println("Invalid path");
            return;
        } System.out.println();

        System.out.println(lRegressionEquation(arr));
        double r = calculatingR(arr);
        System.out.println("R: " + r);
        System.out.println("R^2: " + r*r);
    }

    public static String lRegressionEquation(int[][] arr) {
        int n = arr.length;
        int xsum = 0;
        int ysum = 0;
        int xysum = 0;
        int xsqsum = 0;
        int ysqsum = 0;

        for (int i = 0; i < arr.length; i++) {
            xsum += arr[i][0];
            ysum += arr[i][1];
            xysum += arr[i][0] * arr[i][1];
            xsqsum += arr[i][0] * arr[i][0];
            ysqsum += arr[i][1] * arr[i][1];
        }

        double mDividend = (n * xysum) - (xsum * ysum);
        double mDivisor = (n * xsqsum) - (xsum * xsum);

        double m = (double) (mDividend / mDivisor);

        double bDividend = (ysum - m * xsum);

        double b = (double) (bDividend / n);

        double roundM = Math.round(m * 1000.0) / 1000.0;
        double roundB = Math.round(b * 1000.0) / 1000.0;

        return "y = " + roundB + " + " + roundM + "x";
    }

    public static double calculatingR(int[][] arr) {
        int n = arr.length;
        int xsum = 0;
        int ysum = 0;
        int xysum = 0;
        int xsqsum = 0;
        int ysqsum = 0;

        for (int i = 0; i < arr.length; i++) {
            xsum += arr[i][0];
            ysum += arr[i][1];
            xysum += arr[i][0] * arr[i][1];
            xsqsum += arr[i][0] * arr[i][0];
            ysqsum += arr[i][1] * arr[i][1];
        }

        double rNumerator = n * xysum - (xsum * ysum);
        double rDenominator1 = Math.sqrt((n * xsqsum) - (xsum * xsum));
        double rDenominator2 = Math.sqrt((n * ysqsum) - (ysum * ysum));

        double r = rNumerator / (rDenominator1 * rDenominator2);

        return Math.round(r * 10000.0) / 10000.0;
    }

    public static int[][] tableMaker(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);
        int size = 0;
        int counter = 0;

        while (sc.hasNextLine()) {
            sc.nextLine();
            size++;
        }
        int[][] table = new int[size][2];
        sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String[] i = sc.nextLine().split(", ");
            table[counter][0] = parseInt(i[0]);
            table[counter][1] = parseInt(i[1]);
            counter++;
        }
        sc.close();
        return table;
    }
}