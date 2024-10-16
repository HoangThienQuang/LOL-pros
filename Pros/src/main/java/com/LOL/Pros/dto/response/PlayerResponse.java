package com.LOL.Pros.dto.response;

import com.LOL.Pros.Entity.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponse {
    private String playerId;
    private String ingameName;
    private String playerName;
    private String dob;
    private String role;
    private String currentTeam;
    //private Set<String> playedTeam;
}
