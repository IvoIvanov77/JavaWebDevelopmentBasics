package opg.softuni.panda.repositories;

import opg.softuni.panda.domain.dto.PackageViewHomePageModel;
import opg.softuni.panda.domain.dto.PackageViewTableModel;
import opg.softuni.panda.domain.entities.Package;
import opg.softuni.panda.domain.entities.User;
import opg.softuni.panda.domain.entities.enums.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class PackageRepositoryImpl extends GenericRepository implements PackageRepository {

    @Inject
    public PackageRepositoryImpl(EntityManager em) {
        super(em);
    }


    @Override
    public Package findById(String id) {
        return (Package) execute((em) ->
                em.createQuery("select p from Package p where p.id = :id", Package.class)
                        .setParameter("id", id)
                        .getSingleResult());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Package> findAll() {
        return (List<Package>) execute(entityManager ->
                entityManager.createQuery("select e from User e", User.class)
                        .getResultList());
    }

    @Override
    public Package save(Package p) {
        return (Package) execute(entityManager ->{
            Package e = entityManager.merge(p);
            entityManager.flush();
            return e;
        });

    }

    @Override
    public Package delete(Package p) {
        return (Package) execute(entityManager ->{
            entityManager.remove(p);
            entityManager.flush();
            return p;
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PackageViewTableModel> getPackagesTableByStatus(Status status){
        return (List<PackageViewTableModel>) execute((em) ->
                em.createQuery("select new opg.softuni.panda.domain.dto.PackageViewTableModel(p) " +
                        "from Package p where p.status = :status", PackageViewTableModel.class)
                        .setParameter("status", status)
                        .getResultList());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<PackageViewHomePageModel> getPackagesDescriptionByStatus(Status status, String username){
        return (List<PackageViewHomePageModel>) execute((em) ->
                em.createQuery("select new opg.softuni.panda.domain.dto.PackageViewHomePageModel(p.id, p.description) " +
                        "from Package p where p.status = :status and p.recipient.username = :username"
                        , PackageViewHomePageModel.class)
                        .setParameter("status", status)
                        .setParameter("username", username)
                        .getResultList());
    }

}
