package com.javatrap2020.footballmanager.services;

import com.javatrap2020.footballmanager.exceptions.TeamNotFoundException;
import com.javatrap2020.footballmanager.model.Player;
import com.javatrap2020.footballmanager.model.Team;
import com.javatrap2020.footballmanager.repositories.PlayerRepository;
import com.javatrap2020.footballmanager.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Iterable<Player> getAll() {
        return playerRepository.findAll();
    }

    public Player addPlayer(String teamIdentifier, Player player) {
        try {
            Team team = teamRepository.findByTeamIdentifier(teamIdentifier);
            player.setTeam(team);
            player.setTeamIdentifier(teamIdentifier);
            return playerRepository.save(player);
        } catch (Exception e) {
            throw new TeamNotFoundException("Team not found or player is busy");
        }
    }

    public Iterable<Player> findByName(String name) {
        Iterable<Player> players = playerRepository.findByName(name);
        return players;
    }

    public Player findPlayerByThisId(String teamIdentifier, Long player_id) {
        Team team = teamRepository.findByTeamIdentifier(teamIdentifier);
        if (team == null) {
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

    public Player updatePlayerByThisId(Player updatePlayer, String teamIdentifier, Long player_id) {
        Player player = findPlayerByThisId(teamIdentifier, player_id);
        player = updatePlayer;
        return playerRepository.save(player);
    }

    public void deletePlayerByThisId(String teamIdentifier, Long player_id) {
        Player player = findPlayerByThisId(teamIdentifier,player_id);
        playerRepository.delete(player);
    }

}
