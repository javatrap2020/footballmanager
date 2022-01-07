package com.javatrap2020.footballmanager.controller.controllers;

import com.javatrap2020.footballmanager.controller.model.Player;
import com.javatrap2020.footballmanager.controller.services.MapValidationErrorService;
import com.javatrap2020.footballmanager.controller.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/squad")
@CrossOrigin
public class TeamSquadController {
    @Autowired
    private PlayerService playerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{teamIdentifier}")
    public ResponseEntity<?> addPlayerToSquad(@Valid @RequestBody Player player,
                                              BindingResult bindingResult, @PathVariable String teamIdentifier) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validationService(bindingResult);
        if (errorMap != null) {
            return errorMap;
        }
        Player player1 = playerService.addPlayer(teamIdentifier, player);
        return new ResponseEntity<>(player1, HttpStatus.CREATED);
    }

    @GetMapping("/{teamIdentifier}")
    public Iterable<Player> getTeamSquad(@PathVariable String teamIdentifier) {
        return playerService.findTeamSquadByTeamIdentifier(teamIdentifier);
    }

    @GetMapping("/all")
    public Iterable<Player> getAllPlayers() {
        Iterable<Player> players = playerService.getAll();
        return players;
    }

    @GetMapping("/name={name}")
    public Iterable<Player> getPlayerByName(@PathVariable String name) {
        Iterable<Player> players = playerService.findByName(name);
        return players;
    }

    @GetMapping("/{teamIdentifier}/{player_id}")
    public ResponseEntity<?> getPlayer(@PathVariable String teamIdentifier, @PathVariable Long player_id) {
        Player player = playerService.findPlayerByItsId(teamIdentifier, player_id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PatchMapping("/{teamIdentifier}/{player_id}")
    public ResponseEntity<?> updatePlayerData(@Valid @RequestBody Player player, BindingResult bindingResult,
                                              @PathVariable String teamIdentifier, @PathVariable Long player_id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validationService(bindingResult);
        if (errorMap != null) {
            return errorMap;
        }
        Player updatedPlayer = playerService.updatePlayerByItsId(player, teamIdentifier, player_id);
        return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
    }

    @DeleteMapping("/{teamIdentifier}/{player_id}")
    public ResponseEntity<?> deletePlayer(@PathVariable String teamIdentifier, @PathVariable Long player_id) {
        playerService.deletePlayerByItsId(teamIdentifier, player_id);
        return new ResponseEntity<>("Player with ID '" + player_id + "' was successfully deleted", HttpStatus.OK);
    }
}
