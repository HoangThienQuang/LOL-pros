package com.LOL.Pros.Entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String playerId;

    private String playerName;
    private String currentTeam;
    private String note;

}
