package com.LOL.Pros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternationalTournamentRepository extends JpaRepository<InternationalTournament, String> {
}
