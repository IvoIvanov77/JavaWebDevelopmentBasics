package org.softuni.exam.web.beans;


import org.softuni.exam.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "friendManager")
@RequestScoped
public class FriendManagerBean extends BaseBean {

    private UserService userService;

    public FriendManagerBean() {
    }

    @Inject
    public FriendManagerBean(UserService userService) {
        this.userService = userService;
    }

    public void addFriend(String id1, String id2){
        this.userService.makeFriends(id1, id2);
        this.redirect("/");
    }
}
