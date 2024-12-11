package com.LOL.Pros.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Player {
    @Id
    @Column(name = "playerId", nullable = false, length = 100)
    private String playerId;

    @Column(name = "ingameName", nullable = false, length = 100)
    private String ingameName;

    @Column(name = "playerFirstName", length = 100)
    private String playerFirstName;

    @Column(name = "playerLastMiddleName", length = 100)
    private String playerLastMiddleName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "nationality", length = 100)
    private String nationality;

    @Column(name = "role", nullable = false, length = 100)
    private String role;

}