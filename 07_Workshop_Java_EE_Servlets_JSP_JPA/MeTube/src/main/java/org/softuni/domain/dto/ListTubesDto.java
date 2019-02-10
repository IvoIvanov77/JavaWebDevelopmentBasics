package org.softuni.domain.dto;

import org.softuni.domain.model.Tube;

public class ListTubesDto {
    private String id;

    private String title;

    private String author;

    private String youTubeId;

    public String getTitle() {
        return title;
    }

    public ListTubesDto() {
    }

    public ListTubesDto(String id, String title, String author, String youTubeId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.youTubeId = youTubeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }
}
