package opg.softuni.panda.repositories;

import opg.softuni.panda.domain.entities.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl extends GenericRepository implements ReceiptRepository {

    @Inject
    protected ReceiptRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Receipt findById(String s) {
        return null;
    }

    @Override
    public List<Receipt> findAll() {
        return null;
    }

    @Override
    public Receipt save(Receipt receipt) {
        return null;
    }

    @Override
    public Receipt delete(Receipt receipt) {
        return null;
    }
}
