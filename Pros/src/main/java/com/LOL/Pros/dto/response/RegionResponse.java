package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.RegionalTournament;
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
public class RegionResponse {
    private String regionName;
    private Set<Team> teams;
    private Set<RegionalTournament> domesticTournaments;
}
