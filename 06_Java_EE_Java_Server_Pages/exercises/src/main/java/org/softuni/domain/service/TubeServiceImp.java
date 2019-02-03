package org.softuni.domain.service;

import org.softuni.domain.dto.TubeDetailsDto;
import org.softuni.domain.dto.TubeTitleDto;
import org.softuni.domain.model.Tube;
import org.softuni.domain.repositories.TubeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImp implements TubeService {

    private final TubeRepository tubeRepository;

    @Inject
    public TubeServiceImp(TubeRepository tubeRepository) {
        this.tubeRepository = tubeRepository;
    }

    @Override
    public void createTube(String title, String description,
                           String youTubeLink, String uploader){
        Tube tube = new Tube();
        tube.setTitle(title);
        tube.setDescription(description);
        tube.setYouTubeLink(youTubeLink);
        tube.setUploader(uploader);
        this.tubeRepository.save(tube);
    }

    @Override
    public List<TubeTitleDto> listAllTubes(){
        return this.tubeRepository.getAll()
                .stream()
                .map(TubeTitleDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public TubeDetailsDto tubeDetails(String title){
        if(title == null){
            return null;
        }
        return this.tubeRepository.getTubeDetails(title);
    }
}
