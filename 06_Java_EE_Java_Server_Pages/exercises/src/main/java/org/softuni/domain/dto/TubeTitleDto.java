package org.softuni.domain.dto;

import org.softuni.domain.model.Tube;

public class TubeTitleDto {

    private String title;

    public TubeTitleDto(Tube t) {
        this.title = t.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
