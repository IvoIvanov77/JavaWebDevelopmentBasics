package javache;

import javache.io.Reader;
import javache.io.Writer;

import java.io.*;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;

    private InputStream inputStream;

    private OutputStream outputStream;

    private RequestHandler requestHandler;

    public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
        this.initialiseConnection(clientSocket);
    }

    private void initialiseConnection(Socket socket){
        try {
            this.clientSocket = socket;
            this.inputStream = this.clientSocket.getInputStream();
            this.outputStream = this.clientSocket.getOutputStream();
//            BufferedReader br =
//                    new BufferedReader(new InputStreamReader(this.inputStream));
//            System.out.println(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            String requestContent = Reader.readAllLines(this.inputStream);
            byte[] responseContent = this.requestHandler.handleRequest(requestContent);
            Writer.writeBytes(responseContent, this.outputStream);
            this.inputStream.close();
            this.outputStream.close();
            this.clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
