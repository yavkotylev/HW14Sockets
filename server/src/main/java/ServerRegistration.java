/**
 * Created by Yaroslav on 10.09.16.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRegistration {
    public void listen(int port, Object impl) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                new Thread(new ClientWorker(client, impl)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
