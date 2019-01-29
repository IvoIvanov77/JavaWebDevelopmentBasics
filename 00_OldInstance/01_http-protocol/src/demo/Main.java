package demo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8007);

        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        BufferedReader br =
                new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(outputStream));

//code to read and print headers
        String headerLine = br.readLine();
        String fileName = headerLine.split(" ")[1];
        while(headerLine.length() != 0){
            System.out.println(headerLine);
            headerLine = br.readLine();
        }

//code to read the post payload data
        StringBuilder payload = new StringBuilder();
        while(br.ready()){
            payload.append((char) br.read());
        }
        System.out.println("Payload data is: "+payload.toString());

        String path = "C:\\Users\\Ivaylo\\Documents\\SoftUni\\JavaWeb\\SoftUni-JavaWebDevelopmentBasics\\http-protrcol\\src\\demo";
        System.out.println(fileName);
        List<String> lines = Files.readAllLines(Paths.get(path + fileName));
        String content = String.join("", lines);
        out.write("HTTP/1.1 200 OK\r\n");
        out.write("Content-type:text/html\r\n");
        out.write("\r\n");
        out.write(content);
        out.flush();
        out.close();

        socket.close();
    }
}
