/**
 * Created by Yaroslav on 10.09.16.
 */
public class CalculatorImpl implements Calculator {
    public double calculate(int a, int b) {
        if (0 == 0){
            throw new RuntimeException("my exception");
        }
        return a + b;
    }
}
