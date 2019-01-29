package org.softuni.broccolina.solet;

import java.util.Map;

public interface SoletConfig {

    Map<String, Object> getAllAttributes();

    Object getAttribute(String key);

    void setAttribute(String key, Object attribute);

    void deleteAttribute(String key);


}
