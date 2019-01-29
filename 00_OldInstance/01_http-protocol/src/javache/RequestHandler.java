package javache;

import javache.constants.HttpConstants;
import javache.constants.MessagesConstants;
import javache.constants.ServerConstants;
import javache.enums.HttpStatus;
import javache.http.HttpRequest;
import javache.http.HttpRequestImpl;
import javache.http.HttpResponce;
import javache.http.HttpResponseImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RequestHandler {
    private HttpRequest httpRequest;

    private HttpResponce httpResponse;

    public byte[] handleRequest(String requestContent){
        this.httpRequest = new HttpRequestImpl(requestContent);
        this.httpResponse = new HttpResponseImpl();

        byte[] result = null;

        if(this.httpRequest.getMethod().equalsIgnoreCase("GET")){
            result = this.processGetRequest();
        }

        return result;
    }

    private byte[] ok(byte[] content){
        this.httpResponse.setStatusCode(HttpStatus.OK);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] badRequest(byte[] content){
        this.httpResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] notFound(byte[] content){
        this.httpResponse.setStatusCode(HttpStatus.NOT_FOUND);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] internalServerError(byte[] content){
        this.httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private byte[] redirect(byte[] content, String location){
        this.httpResponse.setStatusCode(HttpStatus.SEE_OTHER);
        this.httpResponse.addHeader(HttpConstants.LOCATION, location);
        this.httpResponse.setContent(content);
        return this.httpResponse.getBytes();
    }

    private String getMimeType(File file){
        String fileName = file.getName();

        if(fileName.endsWith("css")) {
            return "text/css";
        } else if (fileName.endsWith("html")) {
            return "text/html";
        } else if (fileName.endsWith("jpg") || fileName.endsWith("jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith("png")) {
            return "image/png";
        }

        return "text/plain";

    }

    private byte[] processResourceRequest() {
        final String assetPath = ServerConstants.ASSETS_FOLDER_PATH
                + this.httpRequest.getRequestUrl();

        byte[] result = this.getBytesFromRequest(assetPath, MessagesConstants.ASSET_NOT_FOUND);
        this.httpResponse.addHeader(HttpConstants.CONTENT_LENGTH, Integer.toString(result.length));
        this.httpResponse.addHeader(HttpConstants.CONTENT_DISPOSITION, HttpConstants.INLINE_CONTENT_DISPOSITION);

        return this.ok(result);
    }

    private byte[] processPageRequest(String page) {
        final String pagePath = ServerConstants.PAGES_FOLDER_PATH
                + page
                + ServerConstants.HTML_EXTENSION_AND_SEPARATOR;

        byte[] result = this.getBytesFromRequest(pagePath, MessagesConstants.PAGE_NOT_FOUND);
        return this.ok(result);
    }

    private byte[] getBytesFromRequest(String path, String notFoundMessage){
        final File file = new File(path);

        if (!file.exists() || file.isDirectory()) {
            return this.notFound(notFoundMessage
                    .getBytes(HttpConstants.SERVER_ENCODING));
        }

        byte[] result;

        try {
            result = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            return this.internalServerError(MessagesConstants.SOMETHING_WENT_WRONG.getBytes(HttpConstants.SERVER_ENCODING));
        }

        this.httpResponse.addHeader(HttpConstants.CONTENT_TYPE, this.getMimeType(file));

        return result;

    }

    private byte[] processGetRequest() {

        if (this.httpRequest.isResource()) {
            return this.processResourceRequest();
        }

        if ("/".equals(httpRequest.getRequestUrl())) {
            return this.processPageRequest(ServerConstants.INDEX_PAGE);
        }

        return this.processPageRequest(this.httpRequest.getRequestUrl());
    }
}
