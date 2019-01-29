package org.softuni.broccolina.solet;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SoletConfigImpl implements SoletConfig {
    private Map<String, Object> attributes;

    public SoletConfigImpl() {
        this.attributes = new HashMap<>();
    }

    @Override
    public Map<String, Object> getAllAttributes() {
        return Collections.unmodifiableMap(this.attributes);
    }

    @Override
    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }

    @Override
    public void setAttribute(String key, Object attribute) {
        this.attributes.put(key, attribute);
    }

    @Override
    public void deleteAttribute(String key) {
        this.attributes.remove(key);
    }
}
