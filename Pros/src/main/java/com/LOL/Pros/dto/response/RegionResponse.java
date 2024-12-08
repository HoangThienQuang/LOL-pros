package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.DomesticTournament;
import com.LOL.Pros.Entity.Team;
import com.LOL.Pros.Entity.Tournament;
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
    private Set<DomesticTournament> domesticTournaments;
}
