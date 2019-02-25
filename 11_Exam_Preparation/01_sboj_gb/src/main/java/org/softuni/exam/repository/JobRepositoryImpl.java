package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl extends BaseRepository implements JobRepository {

    @Inject
    protected JobRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Job save(Job job) {
        return execute(entityManager ->{
            Job entity = entityManager.merge(job);
            entityManager.flush();
            return entity;
        });
    }

    @Override
    public List<Job> findAll() {
        return execute(entityManager ->
                entityManager.createQuery("SELECT e FROM Job e", Job.class)
                        .getResultList());
    }

    @Override
    public Job findById(String id) {
        return execute((em) ->
                em.createQuery("select j from Job j where j.id = :id", Job.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    public Job delete(Job job) {
        return execute(entityManager ->{
            entityManager.remove(job);
            entityManager.flush();
            return job;
        });
    }

    @Override
    public Long size() {
        return null;
    }
}
