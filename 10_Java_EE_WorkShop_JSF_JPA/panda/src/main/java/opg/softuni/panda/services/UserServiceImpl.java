package opg.softuni.panda.services;

import opg.softuni.panda.domain.dto.UserListBindingModel;
import opg.softuni.panda.domain.dto.UserLoginBindingModel;
import opg.softuni.panda.domain.dto.UserRegisterBindingModel;
import opg.softuni.panda.domain.entities.User;
import opg.softuni.panda.domain.entities.enums.Role;
import opg.softuni.panda.repositories.UserRepository;
import opg.softuni.panda.util.ApplicationUtils;
import org.modelmapper.ModelMapper;

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
    public boolean register(UserRegisterBindingModel model) {
        if(!model.getPassword().equals(model.getConfirmPassword())){
            return false;
        }
        User user = this.modelMapper.map(model, User.class);
        user.setRole(this.userRepository.size() == 0 ? Role.ADMIN : Role.USER);
        try{
           this.userRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean login(UserLoginBindingModel model) {
        User user = null;
        try{
            user = this.userRepository.findByUsername(model.getUsername());
        }catch (Exception e){
            return false;
        }
        if(user == null || !model.getPassword().equals(user.getPassword())){
            return false;
        }
        ApplicationUtils.getSession().setAttribute("username", user.getUsername());
        ApplicationUtils.getSession().setAttribute("userRole", user.getRole());
        return true;
    }

    @Override
    public List<UserListBindingModel> listAllUsers(){
        return this.userRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, UserListBindingModel.class))
                .collect(Collectors.toList());
    }
}
