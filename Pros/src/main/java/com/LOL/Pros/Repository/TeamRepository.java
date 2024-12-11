package com.LOL.Pros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    Optional<Team> existsByTeamName(String teamName);
    Optional<Team> findByTeamName(String teamName);

    @Query("SELECT t FROM Team t JOIN t.captain p WHERE p.ingameName = :ingameName")
    Boolean findByCaptainIngameName(@Param("ingameName") String ingameName);
}
