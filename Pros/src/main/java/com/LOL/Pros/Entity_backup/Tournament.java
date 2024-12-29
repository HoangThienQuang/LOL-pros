package com.LOL.Pros.Entity_backup;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
//@Entity
public class Tournament {
    @Id
    @Column(name = "tournamentId", nullable = false, length = 100)
    private String tournamentId;

    @Column(name = "tournamentName", nullable = false, length = 100)
    private String tournamentName;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

}