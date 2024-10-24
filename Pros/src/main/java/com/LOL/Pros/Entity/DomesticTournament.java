package com.LOL.Pros.Entity;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomesticTournament extends Tournament{
    private String tournamentSeason;

    //tạo cột region_name trong bảng domesticTournament để lưu khóa ngoại của region
    @ManyToOne
    @JoinColumn(name = "region_name")
    private Region region;
}
