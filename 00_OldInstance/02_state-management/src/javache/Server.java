package javache;

import javache.Constants.MessagesConstants;
import javache.Constants.ServerConstants;
import javache.http.HttpSessionStorage;
import javache.http.HttpSessionStorageImpl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
    private int port;

    private ServerSocket server;
    private HttpSessionStorage sessionStorage;


    public Server(int port) {
        this.sessionStorage = new HttpSessionStorageImpl();
        this.port = port;
    }

    public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        System.out.println(MessagesConstants.LISTENING_MESSAGE + this.port);

        this.server.setSoTimeout(ServerConstants.SOCKET_TIMEOUT_MILLISECONDS);

        while(true){
            try (Socket clientSocket = this.server.accept()){
                clientSocket.setSoTimeout(ServerConstants.SOCKET_TIMEOUT_MILLISECONDS);
                ConnectionHandler connectionHandler
                        = new ConnectionHandler(clientSocket,
                        new RequestHandler(this.sessionStorage));
                FutureTask<?> task
                        = new FutureTask<>(connectionHandler, null);
                task.run();

            } catch (SocketTimeoutException ste) {
                System.out.println(MessagesConstants.TIMEOUT_DETECTION_MESSAGE);
            }
        }

    }
}
