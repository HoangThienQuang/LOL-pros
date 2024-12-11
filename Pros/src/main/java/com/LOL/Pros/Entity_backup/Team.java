package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Team {
    @Id
    @Column(name = "teamId", nullable = false, length = 100)
    private String teamId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamCaptain")
    private Player teamCaptain;

    @Column(name = "teamName", nullable = false, length = 100)
    private String teamName;

    @Column(name = "teamLogo", length = 100)
    private String teamLogo;

}