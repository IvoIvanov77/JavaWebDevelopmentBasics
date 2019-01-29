package org.softuni.broccolina.solet;

import org.softuni.javache.http.HttpStatus;
import org.softuni.javache.io.Writer;

import java.io.IOException;

public abstract class BaseHttpSolet implements HttpSolet{

    private static final String NOT_FOUND_MESSAGE =
            "<h1>[ERROR] %s %s</h1> " +
                    "<h3>" +
                    "[MESSAGE] The page or functionality " +
                    "you are looking for is not found" +
                    "</h3>";

    private SoletConfig soletConfig;

    private boolean isInitialized;

    protected BaseHttpSolet() {
        this.isInitialized = false;
    }

    private void configureNotFound(HttpSoletRequest request,HttpSoletResponse response){
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.addHeader("Content-type", "text/html");
        response.setContent(String.format(NOT_FOUND_MESSAGE,
                request.getRequestUrl(),
                request.getMethod())
                .getBytes());
    }


    protected void doGet(HttpSoletRequest request, HttpSoletResponse response) {
        this.configureNotFound(request, response);
    }


    protected void doPost(HttpSoletRequest request, HttpSoletResponse response) {
        this.configureNotFound(request, response);
    }


    protected void doPut(HttpSoletRequest request, HttpSoletResponse response) {
        this.configureNotFound(request, response);
    }


    protected void doDelete(HttpSoletRequest request, HttpSoletResponse response) {
        this.configureNotFound(request, response);
    }

    @Override
    public void service(HttpSoletRequest request, HttpSoletResponse response) throws IOException {
        switch (request.getMethod()) {
            case "GET":
                this.doGet(request, response);
                break;
            case "POST":
                this.doPost(request, response);
                break;
            case "PUT":
                this.doPut(request, response);
                break;
            case "DELETE":
                this.doDelete(request, response);
                break;
        }
        Writer.writeBytes(response.getBytes(), response.getOutputStream());
    }

    @Override
    public void init(SoletConfig soletConfig) {
        this.isInitialized = true;
        this.soletConfig = soletConfig;
    }

    @Override
    public SoletConfig getSoletConfig() {
        return this.soletConfig;
    }

    @Override
    public boolean isInitialized() {
        return this.isInitialized;
    }



}
