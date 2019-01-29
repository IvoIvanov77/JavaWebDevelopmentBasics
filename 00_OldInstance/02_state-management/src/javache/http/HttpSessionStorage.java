package javache.http;

public interface HttpSessionStorage {

    HttpSession getById(String id);

    boolean addSession(HttpSession session);

    void refreshSession();
}
