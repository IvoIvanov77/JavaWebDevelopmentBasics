package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.Document;
import org.softuni.exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl extends BaseRepository implements DocumentRepository {
    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Document save(Document document) {
        return execute(entityManager ->{
            entityManager.persist(document);
            entityManager.flush();
            return document;
        });
    }

    @Override
    public List<Document> findAll() {
        return execute(entityManager ->
                entityManager.createQuery("SELECT d FROM Document d", Document.class)
                        .getResultList());
    }

    @Override
    public Document findById(String id) {
        return execute((em) ->
                em.createQuery("select d from Document d where d.id = :id", Document.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    public Document delete(Document document) {
        return execute(entityManager ->{
            entityManager.remove(document);
            entityManager.flush();
            return document;
        });
    }



}
