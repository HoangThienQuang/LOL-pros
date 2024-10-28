package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlayerTeam {
    @Id
    private String playerTeamName;

    //tạo cột player_id trong bảng playerTeam để liên kết khóa phụ của player
    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    //tạo cột team_name trong bảng playerTeam để liên kết khóa phụ của team
    @ManyToOne
    @JoinColumn(name = "team_name")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Team team;

    private LocalDate startDate;
    private LocalDate endDate;
}
