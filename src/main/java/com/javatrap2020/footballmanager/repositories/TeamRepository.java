package com.javatrap2020.footballmanager.repositories;

import com.javatrap2020.footballmanager.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamIdentifier(String teamIdentifier);
}
