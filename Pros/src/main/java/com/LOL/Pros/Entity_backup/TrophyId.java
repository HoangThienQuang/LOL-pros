package com.LOL.Pros.Entity_backup;

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
public class TrophyId implements Serializable {
    private static final long serialVersionUID = -2738491272380133314L;
    @Column(name = "trophyName", nullable = false, length = 100)
    private String trophyName;

    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TrophyId entity = (TrophyId) o;
        return Objects.equals(this.tournamentId, entity.tournamentId) &&
                Objects.equals(this.trophyName, entity.trophyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, trophyName);
    }

}