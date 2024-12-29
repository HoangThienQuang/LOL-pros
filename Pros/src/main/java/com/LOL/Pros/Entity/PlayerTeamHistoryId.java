package com.LOL.Pros.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class PlayerTeamHistoryId implements Serializable {
    private static final long serialVersionUID = -2636786497464518721L;

    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @Column(name = "startDate", nullable = false)
    private LocalDate startDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerTeamHistoryId entity = (PlayerTeamHistoryId) o;
        return Objects.equals(this.teamId, entity.teamId) &&
                Objects.equals(this.startDate, entity.startDate) &&
                Objects.equals(this.playerId, entity.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, startDate, playerId);
    }

}