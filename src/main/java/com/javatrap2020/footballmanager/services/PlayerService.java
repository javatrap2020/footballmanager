package com.javatrap2020.footballmanager.services;

import com.javatrap2020.footballmanager.exceptions.TeamNotFoundException;
import com.javatrap2020.footballmanager.model.Player;
import com.javatrap2020.footballmanager.model.Team;
import com.javatrap2020.footballmanager.model.TeamSquad;
import com.javatrap2020.footballmanager.repositories.PlayerRepository;
import com.javatrap2020.footballmanager.repositories.TeamRepository;
import com.javatrap2020.footballmanager.repositories.TeamSquadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamSquadRepository teamSquadRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Iterable<Player> getAll() {
        return playerRepository.findAll();
    }

    public Player addPlayer(String teamIdentifier, Player player) {
        try {
            TeamSquad teamSquad = teamSquadRepository.findByTeamIdentifier(teamIdentifier);
            player.setTeamSquad(teamSquad);
            player.setTeamIdentifier(teamIdentifier);
            return playerRepository.save(player);
        } catch (Exception e) {
            throw new TeamNotFoundException("Team not found or someone already use player number");
        }
    }

    public Iterable<Player> findTeamSquadByTeamIdentifier(String teamIdentifier) {
        Team team = teamRepository.findByTeamIdentifier(teamIdentifier);
        if (team == null) {
            throw new TeamNotFoundException("Team with identifier: '" + teamIdentifier + "' does not exist");
        }
        return playerRepository.findByTeamIdentifier(teamIdentifier);
    }

    public Iterable<Player> findByName(String name) {
        Iterable<Player> players = playerRepository.findByName(name);
        return players;
    }

    public Player findPlayerByItsId(String teamIdentifier, Long player_id) {
        TeamSquad teamSquad = teamSquadRepository.findByTeamIdentifier(teamIdentifier);
        if (teamSquad == null) {
            throw new TeamNotFoundException("Team with identifier: '" + teamIdentifier + "' does not exist");
        }
        Player player = playerRepository.findById(player_id).orElse(null);
        if (player == null) {
            throw new TeamNotFoundException("Player with ID: '" + player_id + "' not found");
        }
        if (!player.getTeamIdentifier().equals(teamIdentifier)) {
            throw new TeamNotFoundException("Player with ID " + player_id + " does not exist in team: " + teamIdentifier);
        }
        return player;
    }

    public Player updatePlayerByItsId(Player updatePlayer, String teamIdentifier, Long player_id) {
        Player player = findPlayerByItsId(teamIdentifier, player_id);
        player = updatePlayer;
        return playerRepository.save(player);
    }

    public void deletePlayerByItsId(String teamIdentifier, Long player_id) {
        Player player = findPlayerByItsId(teamIdentifier,player_id);
        playerRepository.delete(player);
    }

}
