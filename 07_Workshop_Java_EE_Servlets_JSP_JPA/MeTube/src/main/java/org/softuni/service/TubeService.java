package org.softuni.service;

import org.softuni.domain.dto.CreateTubeDto;
import org.softuni.domain.dto.ListTubesDto;
import org.softuni.domain.dto.TubeDetailsDto;

import java.util.List;

public interface TubeService {


    void createTube(CreateTubeDto tubeDto, String uploaderName);

    List<ListTubesDto> listAllTubes();

    List<ListTubesDto> listUserTubes(String userName);

    TubeDetailsDto tubeDetails(String id);

//    TubeDetailsDto tubeDetails(String title);
}
