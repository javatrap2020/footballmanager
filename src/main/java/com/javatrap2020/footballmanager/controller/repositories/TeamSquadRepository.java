package com.javatrap2020.footballmanager.controller.repositories;

import com.javatrap2020.footballmanager.controller.model.TeamSquad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamSquadRepository extends CrudRepository<TeamSquad, Long> {

    TeamSquad findByTeamIdentifier(String teamIdentifier);
}
