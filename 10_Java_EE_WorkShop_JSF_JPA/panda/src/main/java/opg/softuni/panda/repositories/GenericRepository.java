package opg.softuni.panda.repositories;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.function.Function;

public abstract class GenericRepository {

    private final EntityManager em;
    private EntityTransaction transaction;

    @Inject
    protected GenericRepository(EntityManager em) {
        this.em = em;
    }

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
