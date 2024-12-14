package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.PlayerTeamHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamAddPlayerResponse {
    private String teamName;
    private Set<PlayerTeamHistory> playerTeam;
}
