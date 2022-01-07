package com.javatrap2020.footballmanager.controller.exceptions;

public class TeamNotFoundExceptionResponse {
    private String teamNotFound;

    public TeamNotFoundExceptionResponse(String teamNotFound) {
        this.teamNotFound = teamNotFound;
    }

    public String getTeamNotFound() {
        return teamNotFound;
    }

    public void setTeamNotFound(String teamNotFound) {
        this.teamNotFound = teamNotFound;
    }
}
