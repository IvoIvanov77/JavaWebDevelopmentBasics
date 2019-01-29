package actions;

import data.User;
import utils.ApplicationUtils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "navigationAction")
public class NavigationAction {

    private User user;

    public NavigationAction() {
        this.user = ApplicationUtils.getCurrentUser();
    }

    public boolean isGuest(){
        return this.user == null;
    }

    public boolean isRegularUser(){
        return this.user != null && !this.user.isAdmin();
    }

    public boolean isAdmin(){
        return this.user != null && this.user.isAdmin();
    }
}
