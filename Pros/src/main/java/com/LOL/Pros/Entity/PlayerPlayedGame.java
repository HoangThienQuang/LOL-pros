package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PlayerPlayedGames")
public class PlayerPlayedGame {
    @EmbeddedId
    private PlayerPlayedGameId id;

    @MapsId("gameId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gameId", nullable = false)
    private SingleGame singleGame;

    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "playerId", nullable = false)
    private Player player;

}