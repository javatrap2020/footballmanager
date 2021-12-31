package com.javatrap2020.footballmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Team extends DateModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank(message = "Team name is required")
    private String name;

    @NotBlank(message = "Team identifier is required")
    @Size(min = 2, message = "Please use min 2 characters")
    @Column(updatable = false, unique = true)
    private String teamIdentifier;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "team")
    @JsonIgnore
    private TeamSquad teamSquad;

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }


    public TeamSquad getTeamSquad() {
        return teamSquad;
    }

    public void setTeamSquad(TeamSquad teamSquad) {
        this.teamSquad = teamSquad;
    }


    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamIdentifier='" + teamIdentifier + '\'' +
                ", teamSquad=" + teamSquad +
                '}';
    }
}
