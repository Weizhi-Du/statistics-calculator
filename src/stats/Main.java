package stats;

public class Main {

    public static void main(String[] args) {
        System.out.println(normalcdf());
    }

    public static int factorial(int i) {
        if (i < 2) return 1;
        return i*factorial(i-1);
    }

    public static double normalcdf() {
        //get variables
        double lower = -40;
        double upper = 40.0;
        double mean = 0;
        double sd = 20;
        return normalcdf(upper, mean, sd) - normalcdf(lower, mean, sd);
    }

    public static double normalcdf(double upper, double mean, double sd) {
        double zval = (upper - mean) / sd;
        double sum = 0;
        double delta = 1;

        for (int n = 0; Math.abs(delta)>=0.000001; n++) {
            delta = (Math.pow(-0.5, n) * Math.pow(zval, 2*n + 1)) / (factorial(n) * Math.sqrt(2*Math.PI) * (2*n + 1));
            sum += delta;
        } return sum;
    }
}