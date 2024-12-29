package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SingleGame {
    @Id
    @Column(name = "gameId", nullable = false, length = 100)
    private String gameId;

    @Column(name = "gameNumber", length = 100)
    private String gameNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matchId", nullable = false)
    private GameMatch gameMatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamWinnerId")
    private Team teamWinner;

}