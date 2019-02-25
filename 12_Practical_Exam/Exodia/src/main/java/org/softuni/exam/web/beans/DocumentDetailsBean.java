package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.domain.models.view.DocumentDetailsViewModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("documentDetails")
@RequestScoped
public class DocumentDetailsBean extends BaseBean {
    private DocumentDetailsViewModel viewModel;

    private DocumentService documentService;

    private ModelMapper modelMapper;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = getRequest().getParameter("id");
        DocumentServiceModel serviceModel = this.documentService.getOneById(id);
        this.viewModel = this.modelMapper.map(serviceModel, DocumentDetailsViewModel.class);
    }

    public DocumentDetailsViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(DocumentDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }
}