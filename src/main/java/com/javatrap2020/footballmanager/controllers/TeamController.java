package com.javatrap2020.footballmanager.controllers;

import com.javatrap2020.footballmanager.model.Team;
import com.javatrap2020.footballmanager.services.MapValidationErrorService;
import com.javatrap2020.footballmanager.services.PlayerService;
import com.javatrap2020.footballmanager.services.TeamService;
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
import java.util.Optional;

@RestController
@RequestMapping("/team")
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @GetMapping("/all")
    public Iterable<Team> getAll() {
        Iterable<Team> teams = teamService.getAll();
        return teams;
    }

    @GetMapping("/id={id}")
    public Optional<Team> getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.findById(id);
        return team;
    }

    @GetMapping("/{teamIdentifier}")
    public Team getTeamByTeamIdentifier(@PathVariable String teamIdentifier) {
        Team team = teamService.findByTeamIdentifier(teamIdentifier);
        return team;
    }

    @PostMapping("")
    public ResponseEntity<?> createNewTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validationService(bindingResult);
        if (errorMap != null) return  errorMap;
        Team team1 = teamService.saveOrUpdate(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @DeleteMapping("/{teamIdentifier}")
    public ResponseEntity<?> deleteTeam(@PathVariable String teamIdentifier) {
        teamService.deleteTeamByIdentifier(teamIdentifier.toUpperCase());
        return new ResponseEntity<>("Team with identifier '" + teamIdentifier + "' was deleted", HttpStatus.OK);
    }

    @PatchMapping("/{teamIdentifier}")
    public ResponseEntity<?> updateTeamData(@Valid @RequestBody Team team, BindingResult bindingResult,
                                            @PathVariable String teamIdentifier) {
        ResponseEntity<?> errorMap = mapValidationErrorService.validationService(bindingResult);
        if (errorMap != null) return errorMap;
        Team updatedTeam = teamService.updateTeamData(team, teamIdentifier);
        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }
}
