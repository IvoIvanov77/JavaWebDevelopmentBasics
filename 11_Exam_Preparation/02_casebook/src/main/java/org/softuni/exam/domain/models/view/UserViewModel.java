package org.softuni.exam.domain.models.view;

import org.softuni.exam.domain.enums.Gender;

public class UserViewModel {
    private String id;

    private String username;

    private Gender gender;

    private String imageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getImageName() {
        return this.gender.toString().toLowerCase();
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
