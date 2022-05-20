package stats;

import java.util.Scanner;


public class Main extends CalculatorFolder {
    static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        dialogue();
    }

    public static void dialogue() {
        System.out.println("1: Tests");
        System.out.println("2: Linear Regression");
        System.out.println("3: Distribution");
        System.out.print("#");
        int f = s.nextInt();
        System.out.println();

        switch (f) {
            case 1: Tests.dialogue(); break;
            case 2: LinearRegression.dialogue(); break;
            case 3: Distribution.dialogue(); break;
            default:
                System.out.println("Invalid Selection");
                System.out.println();
                dialogue();
        }
    }
}