package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String matchId;
    private LocalDate matchDate;

    //tạo mối quan hệ nhiều trận đấu trong 1 tournament
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team1_name")
    private Team team1_name;

    @ManyToOne
    @JoinColumn(name = "team2_name")
    private Team team2_name;
}
