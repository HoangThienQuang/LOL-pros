package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.PlayerTeamHistory;
import com.LOL.Pros.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerTeamRepository extends JpaRepository<PlayerTeamHistory, String> {
    List<PlayerTeamHistory> findByTeam(Team team);
    List<PlayerTeamHistory> findByPlayerPlayerId(String playerPlayerId);
}
