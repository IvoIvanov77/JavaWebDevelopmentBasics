package org.softuni.metube.repository;

import org.softuni.metube.domain.User;

public interface UserRepository extends Repository<String, User> {
    User findByUsername(String username);
}
