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
public class TeamsInGroupId implements Serializable {
    private static final long serialVersionUID = 5937186097711482975L;
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @Column(name = "groupId", nullable = false, length = 100)
    private String groupId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeamsInGroupId entity = (TeamsInGroupId) o;
        return Objects.equals(this.teamId, entity.teamId) &&
                Objects.equals(this.groupId, entity.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, groupId);
    }

}