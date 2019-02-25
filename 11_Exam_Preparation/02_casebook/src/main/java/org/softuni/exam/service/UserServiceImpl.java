package org.softuni.exam.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.User;
import org.softuni.exam.domain.models.service.UserServiceModel;
import org.softuni.exam.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);
    }

    @Override
    public void createUser(UserServiceModel userServiceModel) {
        User user = new User();
        user.setUsername(userServiceModel.getUsername());
        user.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));
        user.setGender(userServiceModel.getGender());
        this.userRepository.save(user);
    }

    @Override
    public List<UserServiceModel> getUsersList(String id){
        User user = this.userRepository.findById(id);
        return this.userRepository.findAll()
                .stream()
                .filter(u -> !u.equals(user))
                .filter(u -> !user.getFriends().contains(u))
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void makeFriends(String id1, String id2){
        User user1 = this.userRepository.findById(id1);
        User user2 = this.userRepository.findById(id2);
        user1.getFriends().add(user2);
        user2.getFriends().add(user1);
        this.userRepository.update(user1);
        this.userRepository.update(user2);
    }
}
