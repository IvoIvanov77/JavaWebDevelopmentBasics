package org.softuni.broccolina;

import org.softuni.broccolina.solet.HttpSolet;
import org.softuni.broccolina.solet.HttpSoletRequest;
import org.softuni.broccolina.solet.HttpSoletRequestImpl;
import org.softuni.broccolina.solet.HttpSoletResponseImpl;
import org.softuni.broccolina.util.ApplicationLoadingService;
import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.http.HttpStatus;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class SoletDispatcher implements RequestHandler {
    private final String serverRootFolderPath;

    private boolean hasIntercepted;

    private Map<String, HttpSolet> soletMap;

    private ApplicationLoadingService applicationLoadingService;

    public SoletDispatcher(String serverRootFolderPath) {
        this.serverRootFolderPath = serverRootFolderPath;
        this.hasIntercepted = false;
        this.applicationLoadingService = new ApplicationLoadingService();
        this.initializeSoletMap();
    }

    private void initializeSoletMap(){
        this.soletMap =
            this.applicationLoadingService.loadApplications(this.serverRootFolderPath
                + "webapps" + File.separator);
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try {
            String requestContent = Reader.readAllLines(inputStream);
            HttpSoletRequest request = new HttpSoletRequestImpl(requestContent, inputStream);
            HttpSoletResponseImpl response = new HttpSoletResponseImpl(outputStream);

            String requestUrl = request.getRequestUrl();

            HttpSolet soletObject = this.soletMap.get(requestUrl);

            if(soletObject == null){
                this.hasIntercepted = false;
            }else {
                soletObject.service(request, response);
                this.hasIntercepted = true;
            }
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
