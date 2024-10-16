package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;

    private String ingameName;
    private String playerName;
    private String dob;
    private String role;
    //tạo mối quan hệ hai bảng team và player
    @ManyToOne //nhiều players có thể chơi cho 1 team
    @JoinColumn(name = "currentTeam") //tạo cột currentTeam trong bảng player để lưu khóa ngoại của Team
    private Team currentTeam;

    //private Set<String> playedTeam = new HashSet<>();
}
