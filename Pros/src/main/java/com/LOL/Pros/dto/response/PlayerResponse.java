package com.LOL.Pros.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponse {
    private String playerId;
    private String ingameName;
    private String playerName;
    private LocalDate dob;
    private String nationality;
    private String role;
    //private Set<String> playedTeam;
}
