package javache.database.services;

import javache.Constants.ServerConstants;
import javache.database.entities.User;
import javache.database.file.CsvFileManager;
import javache.database.repositories.UserRepository;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Optional;

public class UserService {

    public static final String USER_DATA_DB_FILE = "userData.csv";
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository(new CsvFileManager(
                Paths.get(ServerConstants.RESOURCES_PATH,
                ServerConstants.DATA_FOLDER,
                        USER_DATA_DB_FILE)));
    }

    public boolean registerUser(String name, String password, String confirmPassword) {
        if(this.findByName(name) != null || !password.equals(confirmPassword)){
            return false;
        }
        User user = new User(name, password);
        this.userRepository.add(user);
        this.userRepository.flushData();
        return true;
    }

    public User findByName(String name) {
        Optional<User> user = this.userRepository
                .getAll()
                .stream()
                .filter(u -> u.getEmail().equals(name))
                .findFirst();
        return user.orElse(null);

    }

    public Collection<User> getAll() {
        return this.userRepository.getAll();
    }

    public User login(String name, String password) {
        User user = this.findByName(name);
        return user != null && user.getPassword().equals(password) ? user : null;
    }
}
