package org.softuni.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.models.view.DocumentListViewModel;
import org.softuni.exam.service.DocumentService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("documentsList")
@RequestScoped
public class DocumentsListBean extends BaseBean {
    private List<DocumentListViewModel> documents;

    private DocumentService documentService;

    private ModelMapper modelMapper;

    public DocumentsListBean() {
    }

    @Inject
    public DocumentsListBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.documents = this.documentService
                .getAllDocuments()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, DocumentListViewModel.class))
                .collect(Collectors.toList());
    }

    public List<DocumentListViewModel> getDocuments() {
        return this.documents;
    }

    public void setDocuments(List<DocumentListViewModel> documents) {
        this.documents = documents;
    }
}