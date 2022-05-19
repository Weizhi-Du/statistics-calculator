package stats;

import java.util.Scanner;


public class Main implements CalculatorFolder {
    static final Scanner s = new Scanner(System.in);
    static final Distribution distribution = new Distribution();
    static final LinearRegression linearRegression = new LinearRegression();
    static final Tests tests = new Tests();
    static final Main main = new Main();

    public static void main(String[] args) {
        main.dialogue();
    }

    public void dialogue() {
        System.out.println("1: Tests");
        System.out.println("2: Linear Regression");
        System.out.println("3: Distribution");
        System.out.print("#");
        int f = s.nextInt();
        System.out.println();

        switch (f) {
            case 1: tests.dialogue(); break;
            case 2: linearRegression.dialogue(); break;
            case 3: distribution.dialogue(); break;
            default:
                System.out.println("Invalid Selection");
                System.out.println();
                dialogue();
        }
    }
}