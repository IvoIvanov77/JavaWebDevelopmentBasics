package javache.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {

    public static String readAllLines(InputStream inputStream) throws IOException {
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder result = new StringBuilder();

        while (reader.ready()){
            result.append((char) reader.read());
        }
        return result.toString();
    }

    private Reader(){

    }
}