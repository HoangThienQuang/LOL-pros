package com.LOL.Pros.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerCreationRequest {
    private String inGameName;
    private String playerName;
    private LocalDate dob;
    private String region;
    private List<String> position;
    private String currentTeam;
    private List<String> playedTeam;
}
