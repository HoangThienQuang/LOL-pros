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
public class TournamentPlaceId implements Serializable {
    private static final long serialVersionUID = 781528175026805720L;
    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @Column(name = "locationCity", nullable = false, length = 100)
    private String locationCity;

    @Column(name = "locationArena", nullable = false, length = 100)
    private String locationArena;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TournamentPlaceId entity = (TournamentPlaceId) o;
        return Objects.equals(this.locationArena, entity.locationArena) &&
                Objects.equals(this.tournamentId, entity.tournamentId) &&
                Objects.equals(this.locationCity, entity.locationCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationArena, tournamentId, locationCity);
    }

}