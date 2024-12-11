package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TeamSponsors")
public class TeamSponsor {
    @EmbeddedId
    private TeamSponsorId id;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

}