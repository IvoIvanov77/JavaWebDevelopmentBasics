package org.softuni.domain.service;

import org.softuni.domain.dto.TubeDetailsDto;
import org.softuni.domain.dto.TubeTitleDto;

import java.util.List;

public interface TubeService {
    void createTube(String title, String description,
                    String youTubeLink, String uploader);

    List<TubeTitleDto> listAllTubes();

    TubeDetailsDto tubeDetails(String title);
}
