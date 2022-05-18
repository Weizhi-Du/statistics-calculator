package stats;

public class LinearRegression {
   public static void main(String[] args) {
       int[][] table = {
               {1, 30},
               {2, 45},
               {3, 51},
               {4, 57},
               {5, 60},
               {6, 65},
               {7, 70},
               {8, 71}
       };

       System.out.println(lRegressionEquation(table));
       System.out.println("r = " + calculatingR(table));
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
}
