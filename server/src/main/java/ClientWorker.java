import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by Yaroslav on 12.09.16.
 */
class ClientWorker implements Runnable {
    private final Socket client;
    private final Object implementation;

    public ClientWorker(Socket client, Object impl) {
        this.client = client;
        implementation = impl;
    }

    public void run() {
        SerializationRequest request = getClientRequest();
        for (Method method : implementation.getClass().getMethods()) {
            if (method.getName().equals(request.getMethodName())) {
                Object result = null;
                Throwable exception = null;
                try {
                    result = method.invoke(implementation, request.getArgs());
                } catch (IllegalAccessException e) {
                    exception = e;
                } catch (IllegalArgumentException e) {
                    exception = e;
                } catch (InvocationTargetException e) {
                    exception = e.getCause();
                }
                writeToClient(new SerializationAnswerImpl(exception, result));
            }
        }
    }

    private void writeToClient(SerializationAnswer answer) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(answer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SerializationRequest getClientRequest() {
        System.out.println("client accepted");
        byte[] b = new byte[2048];
        int count;
        try {
            count = client.getInputStream().read(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] reqByte = new byte[count];
        System.arraycopy(b, 0, reqByte, 0, count);
        return (SerializationRequestImpl) SerializationUtils.deserialize(reqByte);
    }
}