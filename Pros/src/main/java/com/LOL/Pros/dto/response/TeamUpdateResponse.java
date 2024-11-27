package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamUpdateResponse {
    private String teamName;
    private Set<String> sponsors;
    private String captainName;
    private String region;
    private Set<String> teamPlayers;
}
