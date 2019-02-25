package org.softuni.exam.web.beans;

import org.softuni.exam.service.DocumentService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named("printBean")
@RequestScoped
public class PrintBean extends BaseBean {
    private DocumentService documentService;


    public PrintBean() {
    }


    @Inject
    public PrintBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    public void  print(){
        String id = getRequest().getParameter("id");
        this.documentService.removeById(id);
        this.redirect("/");
    }
}
