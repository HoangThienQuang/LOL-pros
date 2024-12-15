package com.LOL.Pros.Repository;

import com.LOL.Pros.Entity.Player;
import com.LOL.Pros.Entity.PlayerTeamHistory;
import com.LOL.Pros.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface PlayerTeamRepository extends JpaRepository<PlayerTeamHistory, String> {
    List<PlayerTeamHistory> findByTeam(Team team);
    List<PlayerTeamHistory> findByPlayerPlayerId(String playerPlayerId);
    List<PlayerTeamHistory> findPlayerTeamHistoriesByPlayerInAndEndDateIsNull(Collection<Player> players);

    @Procedure(procedureName = "Update_PlayerTeam")
    void updatePlayerTeam(@Param("p_playerId") String playerId, @Param("p_teamId") String teamId);
}
