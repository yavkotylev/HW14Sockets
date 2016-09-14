import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import org.apache.commons.lang.SerializationUtils;

/**
 * Created by Yaroslav on 10.09.16.
 */
public class ClientInvocationHandler implements InvocationHandler {
    private final String host;
    private final int port;

    public ClientInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Socket socket = new Socket(host, port);
        socket.getOutputStream().write(SerializationUtils.serialize
                (new SerializationRequestImpl(args, method)));
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        SerializationAnswer answer = (SerializationAnswerImpl) inputStream.readObject();
        if (answer.getException() != null) throw answer.getException();
        socket.close();
        return answer.getResult();
    }
}
