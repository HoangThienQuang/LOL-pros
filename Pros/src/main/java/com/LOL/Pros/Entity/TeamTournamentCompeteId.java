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
public class TeamTournamentCompeteId implements Serializable {
    private static final long serialVersionUID = 2940366995188350219L;
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamTournamentCompeteId entity = (TeamTournamentCompeteId) o;
        return Objects.equals(this.teamId, entity.teamId) &&
                Objects.equals(this.tournamentId, entity.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, tournamentId);
    }

}