package com.LOL.Pros.Entity_backup;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
@Table(name = "\"Group\"")
public class Group {
    @Id
    @Column(name = "groupId", nullable = false, length = 100)
    private String groupId;

    @Column(name = "groupName", length = 100)
    private String groupName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournamentId")
    private Tournament tournament;

}