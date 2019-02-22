package org.softuni.exam.web.beans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.http.HttpRequest;

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
}
