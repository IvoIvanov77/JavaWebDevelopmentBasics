package javache;

import javache.Constants.ServerConstants;

import java.io.IOException;

public class StartUp {

    public static void main(String[] args) {
       start(args);
    }

    private static void start(final String[] args) {
        final int port = (args.length > 1) ?
                Integer.parseInt(args[1]) :
                ServerConstants.DEFAULT_SERVER_PORT;

        final Server server = new Server(port);

        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
