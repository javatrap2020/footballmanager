package com.javatrap2020.footballmanager.controller.exceptions;

public class TeamIdExceptionResponse {
    private String teamIdentifier;

    public TeamIdExceptionResponse(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public String getTeamIdentifier() {
        return teamIdentifier;
    }

    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }
}
