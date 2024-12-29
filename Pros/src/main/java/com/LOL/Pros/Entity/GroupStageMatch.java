package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GroupStageMatch {
    @Id
    @Column(name = "matchId", nullable = false, length = 100)
    private String matchId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matchId", nullable = false)
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupId", nullable = false)
    private TournamentGroup tournamentGroup;

    @Column(name = "isTieBreak")
    private Boolean isTieBreak;

}