package org.softuni.metube.repository;

import org.softuni.metube.domain.Tube;
import org.softuni.metube.domain.TubeStatus;
import org.softuni.metube.domain.User;

import java.util.List;

public class TubeRepositoryImpl extends GenericRepository implements TubeRepository {
    @Override
    public Tube findById(String id) {
        return (Tube) execute((em) -> em.createNativeQuery("SELECT * FROM tubes WHERE id = \'" + id + "\'", Tube.class).getSingleResult());
    }

    @Override
    public List<Tube> findAll() {
        return (List<Tube>) execute((em) -> em.createNativeQuery("SELECT * FROM tubes", Tube.class)
                .getResultList());
    }

    @Override
    public Tube save(Tube tube) {
        return (Tube) execute((em) -> {
            em.persist(tube);
            em.flush();
            return tube;
        });
    }

    @Override
    public Tube delete(Tube tube) {
        return (Tube) execute((em) -> {
            em.remove(tube);
            em.flush();
            return tube;
        });
    }

    @Override
    public boolean setUploader(String tubeId, String userId) {
        return (boolean) execute((em) -> {
            Tube tube = (Tube) em.createNativeQuery("SELECT * FROM tubes WHERE id = \'" + tubeId + "\'", Tube.class).getSingleResult();

            User user = (User) em.createNativeQuery("SELECT * FROM users WHERE id = \'" + userId + "\'", User.class).getSingleResult();

            if(tube == null || user == null) {
                return false;
            }

            tube.setUploader(user);
            em.flush();
            return true;
        });
    }

    @Override
    public Tube view(String tubeId) {
        return (Tube) execute((em) -> {
            Tube tube = (Tube) em.createNativeQuery("SELECT * FROM tubes WHERE id = \'" + tubeId + "\'", Tube.class).getSingleResult();
            tube.setViews(tube.getViews() + 1);
            em.flush();
            return tube;
        });
    }

    @Override
    public void updateStatus(String tubeId, String status) {
        execute((em) -> {
            Tube tube = (Tube) em.createNativeQuery("SELECT * FROM tubes WHERE id = \'" + tubeId + "\'", Tube.class).getSingleResult();
            tube.setStatus(TubeStatus.valueOf(status));
            em.flush();
            return tube;
        });
    }

    @Override
    public List<Tube> findUserTubes(String userId) {
        return (List<Tube>) execute((em) -> em.createNativeQuery("SELECT * FROM tubes WHERE uploader_id = \'" + userId + "\'", Tube.class)
                .getResultList());
    }

    @Override
    public List<Tube> findAllPending() {
        return (List<Tube>) execute((em) -> em.createNativeQuery("SELECT * FROM tubes WHERE status = \'Pending\'", Tube.class)
                .getResultList());
    }

    @Override
    public List<Tube> findAllApproved() {
        return (List<Tube>) execute((em) -> em.createNativeQuery("SELECT * FROM tubes WHERE status = \'Approved\'", Tube.class)
                .getResultList());
    }
}
