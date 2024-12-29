package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Region;
import com.LOL.Pros.Entity.TeamRegion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TeamRegionRepository extends JpaRepository<TeamRegion, String> {
    Set<TeamRegion> findByRegionName(Region region);
}
