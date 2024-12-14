package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.PlayerTeamHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerTeamRepository extends JpaRepository<PlayerTeamHistory, String> {
}
