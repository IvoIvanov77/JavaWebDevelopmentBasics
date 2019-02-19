package opg.softuni.panda.repositories;

import opg.softuni.panda.domain.entities.User;

public interface UserRepository extends Repository<String, User> {

    User findByUsername(String username);

    Long size();
}
