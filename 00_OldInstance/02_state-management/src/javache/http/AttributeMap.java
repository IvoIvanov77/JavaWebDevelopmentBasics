package javache.http;

public interface AttributeMap {

    String get(String key);

    void add(String key, String value);

    boolean contains(String key);
}
