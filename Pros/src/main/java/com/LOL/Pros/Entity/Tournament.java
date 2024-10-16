package com.LOL.Pros.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
    private Date startDate;//YYYY-MM-DD
    private Date endDate;//YYYY-MM-DD
    private int numberOfParticipateTeam;
    private int season;
    private String region;
}
