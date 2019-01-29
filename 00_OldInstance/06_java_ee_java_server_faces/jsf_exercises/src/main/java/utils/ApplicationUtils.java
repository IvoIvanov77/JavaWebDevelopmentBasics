package utils;

import data.User;
import repositories.CatRepository;
import repositories.Repository;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public final class ApplicationUtils {

    private ApplicationUtils() {
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance()
                .getExternalContext().getResponse();
    }


    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if(session.getAttribute("username") == null){
            return null;
        }
        return session.getAttribute("username").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null)
            return (String) session.getAttribute("userid");
        else
            return null;
    }

    public static void navigate(String url){
        FacesContext.getCurrentInstance()
                .getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, url);
    }

    public static void redirect(String url){
        try {
            getResponse().sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object getExternalContextProperty(String key){
       return FacesContext.getCurrentInstance()
               .getExternalContext().getApplicationMap()
               .get(key);
    }

    @SuppressWarnings("unchecked")
    public static  <T extends Repository> T getRepository(String repoName) {
        return (T)getExternalContextProperty(repoName);
    }

    public static User getCurrentUser(){
        if(getUserName() == null){
            return null;
        }
        return  (User) getRepository("userRepo")
                .get(getUserName());
    }
}
