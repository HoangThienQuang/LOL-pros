package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class PlayerTeamHistory {
    @EmbeddedId
    private PlayerTeamHistoryId id;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @Column(name = "endDate")
    private LocalDate endDate;

}