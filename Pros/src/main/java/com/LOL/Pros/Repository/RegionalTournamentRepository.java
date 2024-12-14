package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.RegionalTournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RegionalTournamentRepository extends JpaRepository<RegionalTournament, String> {
    Set<RegionalTournament> findByRegionName(Region regionName);
}
