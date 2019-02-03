package org.softuni.domain.dto;

import org.softuni.domain.model.Tube;

public class TubeDetailsDto {
    private String title;

    private String description;

    private String youTubeLink;

    private String uploader;

    public TubeDetailsDto() {
    }

    public TubeDetailsDto(Tube tube) {
        if(tube != null){
            this.title = tube.getTitle();
            this.description = tube.getDescription();
            this.youTubeLink = tube.getYouTubeLink();
            this.uploader = tube.getUploader();
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeLink() {
        return youTubeLink;
    }

    public void setYouTubeLink(String youTubeLink) {
        this.youTubeLink = youTubeLink;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }
}
