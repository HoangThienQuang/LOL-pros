package com.LOL.Pros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomesticTournamentRepository extends JpaRepository<DomesticTournament, String> {
}
