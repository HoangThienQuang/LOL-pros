package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RegionalTournament {
    @Id
    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournamentId", nullable = false)
    private Tournament tournament;

    @Column(name = "season", length = 100)
    private String season;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "regionName", nullable = false)
    private Region regionName;

}