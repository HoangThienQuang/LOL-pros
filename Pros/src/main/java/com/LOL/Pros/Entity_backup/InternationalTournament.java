package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class InternationalTournament {
    @Id
    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tournamentId", nullable = false)
    private Tournament tournament;

}