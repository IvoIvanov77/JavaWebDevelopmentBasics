package softuni.web.io;

import java.io.*;

public class HTMLReader implements Reader {
    @Override
    public String read(String path) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path)
                        )
                )
        );

        StringBuilder htmlContent = new StringBuilder();
        String htmlLine;

        while ((htmlLine = reader.readLine()) != null){
            htmlContent.append(htmlLine).append(System.lineSeparator());
        }

        return htmlContent.toString().trim();
    }
}
