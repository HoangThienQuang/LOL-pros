package com.LOL.Pros.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PlayerPlayedGameId implements Serializable {
    private static final long serialVersionUID = -4700701980593603146L;
    @Column(name = "gameId", nullable = false, length = 100)
    private String gameId;

    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerPlayedGameId entity = (PlayerPlayedGameId) o;
        return Objects.equals(this.gameId, entity.gameId) &&
                Objects.equals(this.playerId, entity.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }

}