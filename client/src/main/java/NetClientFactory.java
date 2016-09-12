import java.lang.reflect.Proxy;

/**
 * Created by Yaroslav on 10.09.16.
 */
public class NetClientFactory {
    private final String host;
    private final int port;

    public NetClientFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T createClient(Class<T> interfaceClass) {
        Class<T>[] tClass = new Class[]{interfaceClass};
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), tClass, new ClientInvocationHandler(host, port));
    }
}
