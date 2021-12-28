package com.javatrap2020.footballmanager.services;

import com.javatrap2020.footballmanager.exceptions.TeamIdException;
import com.javatrap2020.footballmanager.model.Team;
import com.javatrap2020.footballmanager.model.TeamSquad;
import com.javatrap2020.footballmanager.repositories.TeamRepository;
import com.javatrap2020.footballmanager.repositories.TeamSquadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamSquadRepository teamSquadRepository;

    public Iterable<Team> getAll() {
        return teamRepository.findAll();
    }

    public Team saveOrUpdate(Team teamX) {
        try {
            teamX.setTeamIdentifier(teamX.getTeamIdentifier().toUpperCase());
            if (teamX.getId() == null) {
                TeamSquad teamSquad = new TeamSquad();
                teamX.setTeamSquad(teamSquad);
                teamSquad.setTeam(teamX);
                teamSquad.setTeamIdentifier(teamX.getTeamIdentifier().toUpperCase());
            }
            if (teamX.getId() != null) {
                teamX.setTeamSquad(teamSquadRepository.findByTeamIdentifier(
                        teamX.getTeamIdentifier().toUpperCase()));
            }
            return teamRepository.save(teamX);
        } catch (Exception e) {
            throw new TeamIdException("Team identifier '" + teamX.getTeamIdentifier() + "' already exists");

        }
    }

    public Optional<Team> findById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        return team;
    }

    public Team findByTeamIdentifier(String teamIdentifier) {
        Team team = teamRepository.findByTeamIdentifier(teamIdentifier);
        if (team == null) {
            throw new TeamIdException("Team identifier '" + teamIdentifier + "' does not exist ");
        }
        return team;
    }

    public void deleteTeamByIdentifier(String teamIdentifier) {
        Team team = teamRepository.findByTeamIdentifier(teamIdentifier.toUpperCase());
        if (team == null) {
            throw new TeamIdException("Cannot Team with identifier '" + teamIdentifier + "'. This team does not exist");
        }
        teamRepository.delete(team);
    }

    public Team updateTeamData(Team updatedTeam, String teamIdentifier) {
        Team team = teamRepository.findByTeamIdentifier(teamIdentifier);
        team = updatedTeam;
        return teamRepository.save(team);
    }

}
