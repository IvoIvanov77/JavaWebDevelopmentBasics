package org.softuni.exam.domain.models.binding;

import org.softuni.exam.domain.enums.Sector;

import java.math.BigDecimal;

public class JobCreateBindingModel {

    private Sector sector;

    private String profession;

    private BigDecimal salary;

    private String description;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
