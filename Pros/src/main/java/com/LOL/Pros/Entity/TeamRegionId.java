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
public class TeamRegionId implements Serializable {
    private static final long serialVersionUID = -6302972012288667682L;
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @Column(name = "regionName", nullable = false, length = 100)
    private String regionName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamRegionId entity = (TeamRegionId) o;
        return Objects.equals(this.teamId, entity.teamId) &&
                Objects.equals(this.regionName, entity.regionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, regionName);
    }

}