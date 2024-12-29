package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class TeamRegion {
    @EmbeddedId
    private TeamRegionId id;

    @MapsId("teamId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teamId", nullable = false)
    private Team team;

    @MapsId("regionName")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regionName", nullable = false)
    private Region regionName;

}