package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.RegionalTournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomesticTournamentRepository extends JpaRepository<RegionalTournament, String> {
}
