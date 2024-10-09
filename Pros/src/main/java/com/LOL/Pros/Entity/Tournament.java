package com.LOL.Pros.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tournamentId;
    private String tournamentName;
    private String competitionPlace;
    private Date startDate;
    private Date endDate;
    private int numberOfParticipateTeam;
    private String region;
}
