package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tournamentId;

    private String tournamentName;
    private String competitionPlace;
    private Date startDate;//YYYY-MM-DD
    private Date endDate;//YYYY-MM-DD
    private int numberOfParticipateTeam;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Series> series = new HashSet<>();
}
