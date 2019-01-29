package javache.database.repositories;

import javache.database.entities.User;
import javache.database.file.FileManager;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {


    private FileManager fileManager;
    private Map<String, User> userData;

    public UserRepository(FileManager fileManager) {
        this.fileManager = fileManager;
        this.initialiseUserData(",");

    }

    @Override
    public Collection<User> getAll() {
        return Collections.unmodifiableCollection(this.userData.values());
    }

    @Override
    public User getById(String id) {
        return this.userData.get(id);
    }

    @Override
    public void add(User user) {
        this.userData.putIfAbsent(user.getId(), user);
    }

    @Override
    public boolean remove(String id) {
        if(!this.userData.containsKey(id)){
            return false;
        }
        this.userData.remove(id);
        return true;
    }

    @Override
    public boolean update(User user) {
        if(!this.userData.containsKey(user.getId())){
            return false;
        }
        this.userData.put(user.getId(), user);
        return true;
    }

    @Override
    public boolean contains(String id) {
        return this.userData.containsKey(id);
    }

    @Override
    public void flushData() {
        this.fileManager.write(this.userData.values());
    }

    private void initialiseUserData(String separator) {
        this.userData = this.fileManager.readAll()
                .stream()
                .map(line -> User.createUser(line, separator))
                .collect(Collectors.toMap(User::getId, user -> user));
    }
}
