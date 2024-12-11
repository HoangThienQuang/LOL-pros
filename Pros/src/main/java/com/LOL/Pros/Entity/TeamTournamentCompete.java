package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TeamTournamentCompete {
    @EmbeddedId
    private TeamTournamentCompeteId id;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @MapsId("tournamentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournamentId", nullable = false)
    private Tournament tournament;

    @Column(name = "rank")
    private Integer rank;

}