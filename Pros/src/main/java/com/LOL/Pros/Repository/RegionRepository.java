package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {
    Optional<Region> findByRegionName(String regionName);
}
