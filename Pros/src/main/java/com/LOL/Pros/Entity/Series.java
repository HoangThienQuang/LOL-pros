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
    //tạo cột tournament_id trong sersies để lưu trữ fk của tournament
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    //tạo cột team1_name trong sersies để lưu trữ fk của team_name
    @ManyToOne
    @JoinColumn(name = "team1_name")
    private Team team1_name;

    //tạo cột team2_name trong sersies để lưu trữ fk của team_name
    @ManyToOne
    @JoinColumn(name = "team2_name")
    private Team team2_name;
}
