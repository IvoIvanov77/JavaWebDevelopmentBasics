package org.softuni.javache;

import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.util.InputStreamCachingService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;

public class ConnectionHandler extends Thread {

    private Socket clientSocket;

    private InputStream clientSocketInputStream;

    private OutputStream clientSocketOutputStream;

    private Set<RequestHandler> requestHandlers;

    private InputStreamCachingService cashingService;


    public ConnectionHandler(Socket clientSocket, Set<RequestHandler> requestHandlers,
                             InputStreamCachingService cashingService) {
        this.cashingService = cashingService;
        this.initializeConnection(clientSocket);
        this.requestHandlers = requestHandlers;
    }

    private void initializeConnection(Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            this.clientSocketInputStream = this.clientSocket.getInputStream();
            this.clientSocketOutputStream = this.clientSocket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processClientConnection() throws IOException {
        for (RequestHandler requestHandler : this.requestHandlers) {
            requestHandler.handleRequest(this.cashingService
                    .getOrCacheInputStream(this.clientSocketInputStream),
                    this.clientSocketOutputStream);

            if(requestHandler.hasIntercepted()) break;
        }
    }

    @Override
    public void run() {
        try {
            this.processClientConnection();
            this.clientSocketInputStream.close();
            this.clientSocketOutputStream.close();
            this.clientSocket.close();
            this.cashingService.evictCache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}






