package com.LOL.Pros.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;

    private String inGameName;
    private String playerName;
    private LocalDate dob;
    private String region;
    //private Set<String> position;
    private String currentTeam;
    //private Set<String> playedTeam;
}
