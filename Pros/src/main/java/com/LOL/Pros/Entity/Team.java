package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Id
    private String teamName;
    private Set<String> sponsors;

    //tạo mối quan hệ player với team thông qua playerteam để kiểm soát start/end date
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PlayerTeam> playerTeams = new HashSet<>();

    //tạo mối quan hệ 1-1, 1 player có thể là captain của 1 team
    @OneToOne
    @JoinColumn(name = "captain_id")
    private Player captain;

    // tạo mối quan hệ n-1 nhiều team có thể thuộc 1 khu vực
    @ManyToOne
    @JoinColumn(name = "region_name")// tạo cột tên region_name trong bảng team để lưu fk của region
    private Region region;

    @OneToMany(mappedBy = "team1_name", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Series> series = new HashSet<>();

    @OneToMany(mappedBy = "team2_name",  cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Series> matches2 = new HashSet<>();
}
