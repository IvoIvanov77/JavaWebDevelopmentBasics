package org.softuni.exam.service;

import org.softuni.exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    String create(DocumentServiceModel serviceModel);

    DocumentServiceModel getOneById(String id);

    List<DocumentServiceModel> getAllDocuments();

    void removeById(String id);
}
