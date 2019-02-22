package org.softuni.exam.domain.entities;

import org.softuni.exam.domain.enums.Sector;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity{
    private Sector sector;

    private String profession;

    private BigDecimal salary;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector")
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
