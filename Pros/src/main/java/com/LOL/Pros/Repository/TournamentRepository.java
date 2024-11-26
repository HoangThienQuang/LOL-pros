package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, String> {
    boolean existsByTournamentName(String tournamentName);
    Optional<Tournament> findByTournamentName(String tournamentName);
}
