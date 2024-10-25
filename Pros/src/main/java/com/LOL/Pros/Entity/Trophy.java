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
public class Trophy {
    @Id
    private String trophyName;
    private LocalDate dateAwarded;

    //tạo cột tournament_id trong bảng trophy để lưu fk của tournament
    @OneToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
