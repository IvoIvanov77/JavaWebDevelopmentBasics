package org.softuni.domain.repositories;

import org.softuni.domain.dto.TubeDetailsDto;
import org.softuni.domain.model.Tube;

import javax.persistence.NoResultException;
import java.util.List;

public class TubeRepository  extends BaseRepository {

    public void save(Tube tube){
        this.executeAction(repositoryActionResult -> this.entityManager.persist(tube));
    }

    @SuppressWarnings("unchecked")
    public List<Tube> getAll(){
        List<Tube> allTubes = (List<Tube>) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    this.entityManager.createQuery(
                            "select t from Tube t"
                    ).getResultList()
            );
        }).getResult();
        return allTubes;
    }

    public TubeDetailsDto getTubeDetails(String name){
        TubeDetailsDto detailsDto = (TubeDetailsDto) this.executeAction(repositoryActionResult -> {
            repositoryActionResult.setResult(
                    getSingleObject(name)
            );
        }).getResult();
        return detailsDto;
    }

    private Object getSingleObject(String key) {
        Object result = null;
        try {
            result = this.entityManager.createQuery(
                    "select new org.softuni.domain.dto.TubeDetailsDto(t) " +
                            "from Tube t where t.title = :name"
            ).setParameter("name", key).getSingleResult();
        }catch (NoResultException e){
            return null;
        }

        return result;
    }
}
