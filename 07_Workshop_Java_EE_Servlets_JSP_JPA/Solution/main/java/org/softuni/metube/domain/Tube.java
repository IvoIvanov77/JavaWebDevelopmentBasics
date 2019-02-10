package org.softuni.metube.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "tubes")
public class Tube {
    private String id;

    private String title;

    private String author;

    private TubeStatus status;

    private String description;

    private String youtubeId;

    private Integer views;

    private User uploader;

    public Tube() {
        this.views = 0;
    }

    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(
            name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "author", nullable = false)
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    public TubeStatus getStatus() {
        return this.status;
    }

    public void setStatus(TubeStatus status) {
        this.status = status;
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "youtubeId", nullable = false)
    public String getYoutubeId() {
        return this.youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    @Column(name = "views", nullable = false)
    public Integer getViews() {
        return this.views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    @JoinColumn(
            name = "uploader_id",
            referencedColumnName = "id"
    )
    public User getUploader() {
        return this.uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
