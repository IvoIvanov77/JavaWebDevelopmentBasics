package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.binding.DocumentCreateBindingModel;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("createDocument")
@RequestScoped
public class CreateDocumentBean extends BaseBean {

    private DocumentCreateBindingModel bindingModel;

    private DocumentService documentService;

    private ModelMapper modelMapper;

    public CreateDocumentBean() {
    }

    @Inject
    public CreateDocumentBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.bindingModel = new DocumentCreateBindingModel();
    }

    public DocumentCreateBindingModel getBindingModel() {
        return bindingModel;
    }

    public void setBindingModel(DocumentCreateBindingModel bindingModel) {
        this.bindingModel = bindingModel;
    }

    public void shedule() {
        DocumentServiceModel serviceModel = this.modelMapper.map(this.bindingModel, DocumentServiceModel.class);
        String documentId = this.documentService.create(serviceModel);
        redirect(String.format("/details/%s", documentId));
    }
}
