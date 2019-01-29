package org.softuni.broccolina.solet;

import org.softuni.javache.http.HttpResponse;

import java.io.InputStream;
import java.io.OutputStream;

public interface HttpSoletResponse extends HttpResponse {
    OutputStream getOutputStream();
}
