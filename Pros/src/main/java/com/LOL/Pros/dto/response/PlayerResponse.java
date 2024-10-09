package com.LOL.Pros.dto.response;

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

    private String inGameName;
    private String playerName;
    private LocalDate dob;
    private String region;
    //private Set<String> position;
    private String currentTeam;
    //private Set<String> playedTeam;
}
