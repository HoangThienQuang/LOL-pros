package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> existsByTeamName(String teamName);
    Optional<Team> findByTeamName(String teamName);
}
