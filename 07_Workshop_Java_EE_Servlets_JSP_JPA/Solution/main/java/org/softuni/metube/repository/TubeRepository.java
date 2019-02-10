package org.softuni.metube.repository;

import org.softuni.metube.domain.Tube;

import java.util.List;

public interface TubeRepository extends Repository<String, Tube> {
    boolean setUploader(String tubeId, String userId);

    Tube view(String tubeId);

    void updateStatus(String tubeId, String status);

    List<Tube> findUserTubes(String userId);

    List<Tube> findAllPending();

    List<Tube> findAllApproved();
}
