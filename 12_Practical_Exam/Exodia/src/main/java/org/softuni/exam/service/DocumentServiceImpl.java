package org.softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.models.service.DocumentServiceModel;
import org.softuni.exam.repository.DocumentRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String create(DocumentServiceModel serviceModel) {
        Document document = this.modelMapper.map(serviceModel, Document.class);
        this.documentRepository.save(document);
        return document.getId();
    }

    @Override
    public DocumentServiceModel getOneById(String id){
        Document document = this.documentRepository.findById(id);
        return this.modelMapper.map(document,
                DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments(){
        System.out.println(this.documentRepository.findAll());
        return this.documentRepository.findAll()
                .stream()
                .map(document -> this.modelMapper.map(document, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(String id){
        Document document = this.documentRepository.findById(id);
        this.documentRepository.delete(document);
    }
}

