import java.io.IOException;

/**
 * Created by Yaroslav on 12.09.16.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ServerRegistration server = new ServerRegistration();
        server.listen(5000, new CalculatorImpl());
    }
}
