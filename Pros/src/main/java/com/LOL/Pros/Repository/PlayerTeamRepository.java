package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.PlayerTeam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTeamRepository extends JpaRepository<PlayerTeam, String> {
}
