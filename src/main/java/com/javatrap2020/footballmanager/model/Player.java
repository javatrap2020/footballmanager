package com.javatrap2020.footballmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Player extends DateModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Player name is required")
    private String name;

    @Column(nullable = false)
    private int playerNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_squad_id", updatable = false, nullable = false)
    @JsonIgnore
    private TeamSquad teamSquad;

    @Column(updatable = false)
    private String teamIdentifier;

    public Player() {
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

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public TeamSquad getTeam() {
        return teamSquad;
    }

    public void setTeamSquad(TeamSquad teamSquad) {
        this.teamSquad = teamSquad;
    }

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playerNumber=" + playerNumber +
                ", createdAt=" + teamSquad +
                ", updatedAt=" + teamIdentifier + '\'' +
                '}';
    }


}
