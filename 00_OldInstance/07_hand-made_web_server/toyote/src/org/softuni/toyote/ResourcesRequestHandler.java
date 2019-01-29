package org.softuni.toyote;

import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.http.*;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourcesRequestHandler implements RequestHandler {
    private static final String RESOURCES_FOLDER_NAME = "resources";

    private final String serverRootFolderPath;

    private boolean hasIntercepted;

    public ResourcesRequestHandler(String serverRootFolderPath) {
        this.serverRootFolderPath = serverRootFolderPath;
        this.hasIntercepted = false;
    }

    private String getMimeType(String filePath) {

        Path path = Paths.get(filePath);
        String mimeType = "";
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mimeType;

    }

    private byte[] proccesStaticResource(String resourcePath){

        File file = new File(resourcePath);

        if(!file.exists() || file.isDirectory() ){
            return null;
        }
        HttpResponse response = new HttpResponseImpl();

        byte[] result = new byte[0];
        try {
            result = Files.readAllBytes(Paths.get(resourcePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

       return result;

    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try {
            String requestContent = Reader.readAllLines(inputStream);
            HttpRequest request = new HttpRequestImpl(requestContent);
            HttpResponse response = new HttpResponseImpl();

            String requestUrl = request.getRequestUrl();

            String resourcesFolderPath = this.serverRootFolderPath
                    + "webapps"
                    + File.separator
                    + "ROOT"
                    + File.separator
                    + RESOURCES_FOLDER_NAME;

            String resourcePath = resourcesFolderPath + requestUrl;

            byte[] result = this.proccesStaticResource(resourcePath);
            if(result == null || result.length == 0){
                this.hasIntercepted = false;
                return;
            }

            response.setStatusCode(HttpStatus.OK);
            response.addHeader("Content-Type", this.getMimeType(resourcePath));
            response.addHeader("Content-Length", "" + result.length);
            response.addHeader("Content-Disposition", "inline");
            response.setContent(result);

            Writer.writeBytes(response.getBytes(), outputStream);

            this.hasIntercepted = true;

        }catch (IOException e){
            this.hasIntercepted = false;
            e.printStackTrace();
        }

    }

    @Override
    public boolean hasIntercepted() {
        return this.hasIntercepted;
    }
}
