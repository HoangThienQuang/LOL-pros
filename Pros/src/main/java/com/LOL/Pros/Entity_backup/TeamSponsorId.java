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
public class TeamSponsorId implements Serializable {
    private static final long serialVersionUID = 3655755796427944291L;
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @Column(name = "sponsor", nullable = false, length = 100)
    private String sponsor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamSponsorId entity = (TeamSponsorId) o;
        return Objects.equals(this.sponsor, entity.sponsor) &&
                Objects.equals(this.teamId, entity.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sponsor, teamId);
    }

}