/**
 * Created by Yaroslav on 12.09.16.
 */
public class Main {
    public static void main(String[] args) {
        NetClientFactory netClientFactory = new NetClientFactory("localhost", 5000);
        try {
            Calculator calculator = netClientFactory.createClient(Calculator.class);
            double result = calculator.calculate(10, 20);
            System.out.println("result = " + result);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
