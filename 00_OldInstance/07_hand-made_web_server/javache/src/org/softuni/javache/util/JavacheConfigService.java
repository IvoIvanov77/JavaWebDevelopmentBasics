package org.softuni.javache.util;

import org.softuni.javache.WebConstants;
import org.softuni.javache.io.Reader;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavacheConfigService {

    private static final String CONFIG_FOLDER_PATH =
            WebConstants.SERVER_ROOT_FOLDER_PATH + "config/";

    private static final String REQUEST_HANDLER_PRIORITY_CONFIG_FILE =
            CONFIG_FOLDER_PATH + "config.ini";

    private Set<String> handlersPriority;

    public JavacheConfigService() {
        this.initConfiguration();

    }

    private void loadRequestHandlerConfig() throws IOException {
        File priorityConfigFile = new File(REQUEST_HANDLER_PRIORITY_CONFIG_FILE );

        if(!priorityConfigFile.exists() || !priorityConfigFile.isFile()){
            throw  new IllegalArgumentException(
                    "RequestHandler priority configuration file does not exist");
        }

        String configFileContent = Reader.readAllLines(
                new BufferedInputStream(
                        new FileInputStream(priorityConfigFile)));

        String[] requestHandlers =
                configFileContent.replaceAll("request-handlers:\\s*","")
                        .split(",\\s*");


        this.handlersPriority = new LinkedHashSet<>(Arrays.asList(requestHandlers));
    }

    private void initConfiguration() {
        try {
            this.loadRequestHandlerConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<String> getHandlersPriority() {
        return Collections.unmodifiableSet(this.handlersPriority);
    }
}
