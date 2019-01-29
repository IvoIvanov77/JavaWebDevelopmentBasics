package javache.http;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpSessionStorageImpl implements HttpSessionStorage {
    private Map<String, HttpSession> allSessions;

    public HttpSessionStorageImpl() {
        this.allSessions = new HashMap<>();
    }

    @Override
    public HttpSession getById(String id) {
        return this.allSessions.get(id);
    }

    @Override
    public boolean addSession(HttpSession session) {
        if(this.allSessions.containsKey(session.getId())){
            return false;
        }
        this.allSessions.put(session.getId(),session);
        System.out.println(this.allSessions);
        return true;
    }

    @Override
    public void refreshSession() {
        this.allSessions.values()
                .stream()
                .filter(session -> !session.isValid())
                .map(HttpSession::getId)
                .collect(Collectors.toList())
                .forEach(id -> this.allSessions.remove(id));
    }
}
