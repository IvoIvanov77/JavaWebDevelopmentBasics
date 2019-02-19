package opg.softuni.panda.web.beans;

import opg.softuni.panda.domain.entities.enums.Role;
import opg.softuni.panda.util.ApplicationUtils;

import javax.inject.Named;

@Named(value = "auth")
public class AuthorisationBean {


    public String getUsername(){
        return (String) ApplicationUtils.getSession().getAttribute("username");
    }

    public void logout() {
        ApplicationUtils.getSession().invalidate();
        ApplicationUtils.redirect("/");
    }

    public boolean isLoggedInUser(){
        return ApplicationUtils.getSession() != null
                && ApplicationUtils.getSession().getAttribute("username") != null;
    }

    public boolean isAdmin(){
        return ApplicationUtils.getSession() != null
                && ApplicationUtils.getSession().getAttribute("username") != null
                && ApplicationUtils.getSession().getAttribute("userRole") == Role.ADMIN;
    }
}
