package com.LOL.Pros.Entity;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DomesticTournament extends Tournament{
    private int tournamentSeason;

    //tạo cột region_name trong bảng domesticTournament để lưu khóa ngoại của region
    @ManyToOne
    @JoinColumn(name = "region_name")
    private Region region;

//    public DomesticTournament(String tournamentId, String tournamentName, String competitionPlace, LocalDate startDate, LocalDate endDate, int numberOfParticipateTeam)
//    {
//        super(tournamentId, tournamentName,competitionPlace,startDate,endDate, numberOfParticipateTeam);
//    }
}
