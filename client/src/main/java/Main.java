import java.util.Scanner;

/**
 * Created by Yaroslav on 12.09.16.
 */
public class Main {
    public static void main(String[] args) {
        NetClientFactory netClientFactory = new NetClientFactory("localhost", 5000);
        try {
            Calculator calculator = netClientFactory.createClient(Calculator.class);
            try {
                double result = calc(calculator);
                System.out.println("result = " + result);
            } catch (NumberFormatException e) {
                System.out.println("Wrong operands");
            }

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static double calc(Calculator calculator) {
        Scanner scanner = new Scanner(System.in);
        int operand1 = 0;
        int operand2 = 0;
        System.out.println("Write first operand:");
        operand1 = Integer.valueOf(scanner.next());
        System.out.println("Write second operand:");
        operand2 = Integer.valueOf(scanner.next());
        return calculator.calculate(operand1, operand2);
    }
}
