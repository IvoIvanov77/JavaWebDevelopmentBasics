package org.softuni.util;

import org.softuni.http.constans.HTTPConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader {

    private BufferedReader bufferedReader;

    public ConsoleReader() {
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public static String readAllLines()  {
        try (InputStreamReader instream = new InputStreamReader(System.in);
             BufferedReader buffer = new BufferedReader(instream)) {
            StringBuilder builder = new StringBuilder();
            String line;
            while (!(line = buffer.readLine()).isEmpty()) {
                builder.append(line).append(HTTPConstants.LINE_SEPARATOR);
            }
            return builder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
