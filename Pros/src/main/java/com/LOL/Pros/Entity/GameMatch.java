package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class GameMatch {
    @Id
    @Column(name = "matchId", nullable = false, length = 100)
    private String matchId;

    @Column(name = "matchDate")
    private LocalDate matchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamAId")
    private Team teamAId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamBId")
    private Team teamBId;

    @Column(name = "resultTeamA", length = 100)
    private String resultTeamA;

    @Column(name = "resultTeamB", length = 100)
    private String resultTeamB;

    @Column(name = "bestOf", length = 100)
    private String bestOf;

}