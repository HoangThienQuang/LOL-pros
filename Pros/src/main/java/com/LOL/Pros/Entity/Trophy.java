package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Trophy {
    @EmbeddedId
    private TrophyId id;

    @MapsId("tournamentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournamentId", nullable = false)
    private Tournament tournament;

    @Column(name = "dateAwarded")
    private LocalDate dateAwarded;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

}