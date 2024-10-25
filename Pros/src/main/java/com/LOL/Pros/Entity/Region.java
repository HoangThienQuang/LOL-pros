package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Region {
    @Id
    private String regionName;

    // tạo mối quan hệ 1-n với team
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //mappedBy chỉ định trường trong team là chủ sở hữu mối quan hệ này
    private Set<Team> teams = new HashSet<>();//tập hợp các team thuộc khu vực này

    //tạo mối quan hệ 1-n với domestic tournament
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<DomesticTournament> domesticTournaments = new HashSet<>();
}
