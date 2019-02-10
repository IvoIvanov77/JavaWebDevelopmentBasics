package org.softuni.metube.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.function.Function;

public abstract class GenericRepository {
    private EntityManager em = Persistence
            .createEntityManagerFactory("MeTubeProvider")
            .createEntityManager();

    private EntityTransaction transaction;

    private void openTransaction() {
        if(this.transaction == null || !this.transaction.isActive()) {
            this.transaction = em.getTransaction();
            this.transaction.begin();
        }
    }

    private void closeTransaction() {
        if(this.transaction != null && this.transaction.isActive()) {
            this.transaction.commit();
            this.transaction = null;
        }
    }

    protected Object execute(Function<EntityManager, Object> consumer) {
        this.openTransaction();
        Object result = consumer.apply(this.em);
        this.closeTransaction();
        return result;
    }
}
