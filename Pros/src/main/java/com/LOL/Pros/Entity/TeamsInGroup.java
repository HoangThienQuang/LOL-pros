package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TeamsInGroup {
    @EmbeddedId
    private TeamsInGroupId id;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @MapsId("groupId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "groupId", nullable = false)
    private TournamentGroup tournamentGroup;

    @Column(name = "draw", length = 100)
    private String draw;

    @Column(name = "win", length = 100)
    private String win;

    @Column(name = "loss", length = 100)
    private String loss;

}