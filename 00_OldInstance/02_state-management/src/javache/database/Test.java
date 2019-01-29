package javache.database;

import javache.Constants.ServerConstants;
import javache.database.entities.User;
import javache.database.file.CsvFileManager;
import javache.database.repositories.Repository;
import javache.database.repositories.UserRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    public static void main(String[] args) {
        Path path = Paths.get(ServerConstants.RESOURCES_PATH,
                ServerConstants.DATA_FOLDER,
                "userData.csv");

        Repository<User> repository = new UserRepository(new CsvFileManager(path));

//        repository.add(new User("ivanov", "123"));
        User user = repository.getById("3ea9017f-4e67-4316-8bdf-cfcdce3816f5");
        System.out.println(user);
        user.setEmail("petko");
        repository.update(user);
        repository.remove("1ddbb38d-6f8a-4d34-b76a-aa45e9a3c45b");
        repository.flushData();
    }
}
