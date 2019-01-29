package javache;

import javache.io.Reader;
import javache.io.Writer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler extends Thread {
    private Socket clientSocket;

    private InputStream clientInputStream;

    private OutputStream clientOutputStream;

    private RequestHandler requestHandler;

    public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler){
        this.initializeConnection(clientSocket);
        this.requestHandler = requestHandler;
    }

    private void initializeConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.clientInputStream = clientSocket.getInputStream();
            this.clientOutputStream = clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        try {
            String content = null;
            int counter = 0;
            while(counter++ < 20000){
                content = Reader.readAllLines(this.clientInputStream);
                if(content.length() > 0){
                    break;
                }
            }

            if (content == null){
                throw new NullPointerException("request is null");
            }

            byte[] responseContent = this.requestHandler.handleRequest(content);
            Writer.write(responseContent, this.clientOutputStream);
            this.clientInputStream.close();
            this.clientOutputStream.close();
            this.clientSocket.close();



//            System.out.println(content);
//
//            HttpRequest request = new HttpRequestImpl(content);
//
//            String path = "C:\\Users\\Ivaylo\\Documents\\SoftUni\\JavaWeb\\SoftUni-JavaWebDevelopmentBasics" +
//                    "\\simple-demo\\src\\resources\\index.html";
//
//            List<String> lines = Files.readAllLines(Paths.get(path));
//
//            HttpResponse response = new HttpResponseImpl();
//            System.out.println(response);
//            response.setStatusCode(HttpStatusCode.OK);
//            response.addHeader("Content-Type", "text/html");
//            response.setContent(String.join("", lines).getBytes());
//            Writer.write(response.getBytes(), this.clientOutputStream);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
