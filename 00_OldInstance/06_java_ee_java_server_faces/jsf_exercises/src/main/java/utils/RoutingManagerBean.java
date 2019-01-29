package utils;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "routingManager")
public class RoutingManagerBean {

    public void loginAndRegister(){
        if(ApplicationUtils.getSession().getAttribute("username") != null){
           ApplicationUtils.redirect("/");
        }
    }

    public void userResources(){
        if(ApplicationUtils.getSession().getAttribute("username") == null){
            ApplicationUtils.redirect("/users/login.html");
        }
    }

    public void adminResources(){
        if(ApplicationUtils.getSession().getAttribute("isAdmin") == null){
            if(ApplicationUtils.getSession().getAttribute("username") == null){
                ApplicationUtils.redirect("/users/login.html");
            }else{
                ApplicationUtils.redirect("/");
            }

        }
    }

}
