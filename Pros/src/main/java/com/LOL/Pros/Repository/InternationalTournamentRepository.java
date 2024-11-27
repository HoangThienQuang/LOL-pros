package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.InternationalTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternationalTournamentRepository extends JpaRepository<InternationalTournament, String> {
}
