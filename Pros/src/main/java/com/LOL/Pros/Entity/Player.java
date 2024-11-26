package com.LOL.Pros.Entity;

import com.LOL.Pros.Enum.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;

    private String ingameName;
    private String playerName;
    private LocalDate dob;
    private String nationality;

    //lưu Role xuống DB là dạng string
    @Enumerated(EnumType.STRING)
    private Role role;

    //PlayerTeam để quản lý mối quan hệ giữa player và team dựa trên start/end date
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<PlayerTeam> playerTeams = new HashSet<>();
    private String currentTeam;


//    @OneToOne(mappedBy = "captain")// Chỉ một player có thể là captain của một team
//    private Team captainedTeam;
}
