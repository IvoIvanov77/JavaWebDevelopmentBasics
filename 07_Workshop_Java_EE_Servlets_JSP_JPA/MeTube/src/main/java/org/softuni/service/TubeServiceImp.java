package org.softuni.service;

import org.softuni.domain.dto.CreateTubeDto;
import org.softuni.domain.dto.ListTubesDto;
import org.softuni.domain.dto.TubeDetailsDto;
import org.softuni.domain.model.Tube;
import org.softuni.domain.model.User;
import org.softuni.repositories.TubeRepository;
import org.softuni.repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImp implements TubeService {

    private final TubeRepository tubeRepository;

    private final UserRepository userRepository;

    @Inject
    public TubeServiceImp(TubeRepository tubeRepository, UserRepository userRepository) {
        this.tubeRepository = tubeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createTube(CreateTubeDto tubeDto, String uploaderName){
        User uploader = this.userRepository.getByUsername(uploaderName);
        Tube tube = new Tube();
        String youTubeId = tubeDto.getYouTubeLink().split("=")[1];
        tube.setTitle(tubeDto.getTitle());
        tube.setAuthor(tubeDto.getAuthor());
        tube.setDescription(tubeDto.getDescription());
        tube.setUploader(uploader);
        tube.setYouTubeId(youTubeId);
        tube.setViews(0L);
        this.tubeRepository.save(tube);
    }

    @Override
    public List<ListTubesDto> listAllTubes(){
        return this.tubeRepository.getAll();
    }

    @Override
    public List<ListTubesDto> listUserTubes(String username){
        return this.tubeRepository
                .getTubesByUploaderUsername(username)
                .stream()
                .map(t -> new ListTubesDto(t.getId(), t.getTitle(), t.getAuthor(), t.getYouTubeId()))
                .collect(Collectors.toList());
    }

    @Override
    public TubeDetailsDto tubeDetails(String id){
        if(id == null){
            return null;
        }
        Tube tube = this.tubeRepository.getTubeById(id);
        tube.setViews(tube.getViews() + 1);
        TubeDetailsDto tubeDetailsDto = new TubeDetailsDto(tube.getTitle(),
                tube.getAuthor(),
                tube.getDescription(),
                tube.getYouTubeId(),
                tube.getViews());
        this.tubeRepository.updateTube(tube);
        return tubeDetailsDto;
    }
}
