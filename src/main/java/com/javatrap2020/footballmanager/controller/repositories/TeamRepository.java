package com.javatrap2020.footballmanager.controller.repositories;

import com.javatrap2020.footballmanager.controller.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamIdentifier(String teamIdentifier);
}
