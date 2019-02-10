package org.softuni.repositories;

import org.softuni.domain.dto.ListTubesDto;
import org.softuni.domain.model.Tube;

import javax.persistence.NoResultException;
import java.util.List;

public class TubeRepository  extends BaseRepository {

    public void save(Tube tube){
        this.executeAction(repositoryActionResult -> this.entityManager.persist(tube));
    }

    @SuppressWarnings("unchecked")
    public List<ListTubesDto> getAll(){
        List<ListTubesDto> allTubes = (List<ListTubesDto>) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.entityManager.createQuery(
                            "select new org.softuni.domain.dto.ListTubesDto(t.id, t.title, t.author, t.youTubeId) " +
                                    " from Tube t"
                    ).getResultList()
            );
        }).getResult();
        return allTubes;
    }

    @SuppressWarnings("unchecked")
    public List<Tube> getTubesByUploaderUsername(String username){

        return (List<Tube>) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.entityManager.createQuery(
                            "select t from Tube t where t.uploader.username = :username"
                    ).setParameter("username", username)
                            .getResultList()
            );
        }).getResult();
    }


    public Tube getTubeById(String id){
        return (Tube) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    getSingleTube(id)
            );
        }).getResult();
    }

    private Object getSingleTube(String id) {
        Object result = null;
        try {
            result = this.entityManager.createQuery(
                    "select t  from Tube t where t.id = :id"
            ).setParameter("id", id).getSingleResult();
        }catch (NoResultException e){
            return null;
        }

        return result;
    }

    public void updateTube(Tube t){
        this.executeAction(repositoryActionResult -> this.entityManager.merge(t));
    }
}
