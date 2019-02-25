package org.softuni.exam.web.beans;

import javax.faces.context.FacesContext;
import javax.faces.context.SessionMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class BaseBean {
    protected void redirect(String url) {
        try {
            FacesContext
                    .getCurrentInstance()
                    .getExternalContext()
                    .redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected HttpServletRequest getRequest(){
        return (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
}
