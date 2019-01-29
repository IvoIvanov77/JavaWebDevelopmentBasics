package javache.http;

import java.util.HashMap;
import java.util.Map;

public class AttributeMapImpl implements AttributeMap {
    private Map<String, String> attributes;

    public AttributeMapImpl() {
        this.attributes = new HashMap<>();
    }

    @Override
    public String get(String key) {
        return this.attributes.get(key);
    }

    @Override
    public void add(String key, String value) {
        this.attributes.putIfAbsent(key, value);
    }

    @Override
    public boolean contains(String key) {
        return this.attributes.containsKey(key);
    }
}
