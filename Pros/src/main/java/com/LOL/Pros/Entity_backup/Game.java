package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Game {
    @Id
    @Column(name = "gameId", nullable = false, length = 100)
    private String gameId;

    @Column(name = "gameNumber", length = 100)
    private String gameNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "matchId", nullable = false)
    private Match match;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamWinnerId")
    private Team teamWinner;

}