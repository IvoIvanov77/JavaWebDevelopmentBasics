package org.softuni.exam.web.beans;


import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.view.UserViewModel;
import org.softuni.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Named(value = "homeView")
@RequestScoped
public class HomeBean extends BaseBean {
    private  ModelMapper modelMapper;

    private  UserService userService;

    private String currentUserUsername;

    private String currentUserId;

    private List<UserViewModel> users;


    public HomeBean() {
    }

    @Inject
    public HomeBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        HttpSession session = this.getSession();
        this.currentUserId = (String) session.getAttribute("user-id");
        this.currentUserUsername = (String) session.getAttribute("username");
        this.users = this.userService.getUsersList(currentUserId)
                .stream().map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList());
    }

    public String getCurrentUserUsername() {
        return currentUserUsername;
    }

    public void setCurrentUserUsername(String currentUserUsername) {
        this.currentUserUsername = currentUserUsername;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }


}
