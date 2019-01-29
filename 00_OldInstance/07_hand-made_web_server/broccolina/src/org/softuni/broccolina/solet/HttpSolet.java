package org.softuni.broccolina.solet;

import java.io.IOException;

public interface HttpSolet {

    void init(SoletConfig soletConfig);

    SoletConfig getSoletConfig();

    boolean isInitialized();

    void service(HttpSoletRequest request, HttpSoletResponse response) throws IOException;

}
