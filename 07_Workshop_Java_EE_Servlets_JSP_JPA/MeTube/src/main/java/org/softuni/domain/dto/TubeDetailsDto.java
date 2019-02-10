package org.softuni.domain.dto;

public class TubeDetailsDto {
    private String title;

    private String author;

    private String description;

    private String youTubeId;

    private Long views;

    public TubeDetailsDto() {
    }

    public TubeDetailsDto(String title, String author, String description, String youTubeId, Long views) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.youTubeId = youTubeId;
        this.views = views;
    }

    public String getTitle() {
        return title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYouTubeId() {
        return youTubeId;
    }

    public void setYouTubeId(String youTubeId) {
        this.youTubeId = youTubeId;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }
}
