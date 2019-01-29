package org.softuni.javache;

import org.softuni.javache.util.InputStreamCachingService;
import org.softuni.javache.util.JavacheConfigService;
import org.softuni.javache.util.LoggingService;
import org.softuni.javache.util.RequestHandlerLoadingService;

import java.io.*;
import java.net.*;
import java.util.concurrent.FutureTask;

public class Server {
    private static final String LISTENING_MESSAGE = "Listening on port: ";

    private static final String TIMEOUT_DETECTION_MESSAGE = "Timeout detected.";

    private static final Integer SOCKET_TIMEOUT_MILLISECONDS = 5000;

    private int port;

    private int timeouts;

    private ServerSocket server;

    private JavacheConfigService javacheConfigService;

    private RequestHandlerLoadingService requestHandlerLoadingService;

    public Server(int port) {
        this.port = port;
        this.timeouts = 0;
        this.javacheConfigService = new JavacheConfigService();
        this.requestHandlerLoadingService = new RequestHandlerLoadingService();
        this.initRequestHandlers();
    }

    private void initRequestHandlers() {
        try{
            this.requestHandlerLoadingService.loadRequestHandlers(
                    this.javacheConfigService.getHandlersPriority()
            );

        }catch (Exception e){
            LoggingService.error(e.getMessage());
        }

    }

    public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        LoggingService.info(LISTENING_MESSAGE + this.port);

        this.server.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

        while(true) {
            try(Socket clientSocket = this.server.accept()) {
                clientSocket.setSoTimeout(SOCKET_TIMEOUT_MILLISECONDS);

                ConnectionHandler connectionHandler
                        = new ConnectionHandler(clientSocket,
                        this.requestHandlerLoadingService.getRequestHandlers(),
                        new InputStreamCachingService());

                FutureTask<?> task = new FutureTask<>(connectionHandler, null);
                try {
                    task.run();
                }catch (Exception e){
                    LoggingService.error(e.getMessage());
                }

            } catch(SocketTimeoutException e) {
                LoggingService.warning(TIMEOUT_DETECTION_MESSAGE);
                this.timeouts++;
            }
        }
    }
}